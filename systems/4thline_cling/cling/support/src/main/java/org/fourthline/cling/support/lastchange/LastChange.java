public class Lastchange {






































































    synchronized public void reset() {
        previousValue = toString();
        event.clear();
    }

    synchronized public void setEventedValue(int instanceID, EventedValue... ev) {
        setEventedValue(new UnsignedIntegerFourBytes(instanceID), ev);
    }

    synchronized public void setEventedValue(UnsignedIntegerFourBytes instanceID, EventedValue... ev) {
        for (EventedValue eventedValue : ev) {
            if (eventedValue != null)
                event.setEventedValue(instanceID, eventedValue);

        }
    }

    synchronized public UnsignedIntegerFourBytes[] getInstanceIDs() {
        List<UnsignedIntegerFourBytes> list = new ArrayList<>();
        for (InstanceID instanceID : event.getInstanceIDs()) {
            list.add(instanceID.getId());
        }
        return list.toArray(new UnsignedIntegerFourBytes[list.size()]);
    }






    synchronized public <EV extends EventedValue> EV getEventedValue(int instanceID, Class<EV> type) {
        return getEventedValue(new UnsignedIntegerFourBytes(instanceID), type);
    }

    synchronized public <EV extends EventedValue> EV getEventedValue(UnsignedIntegerFourBytes id, Class<EV> type) {
        return event.getEventedValue(id, type);
    }

    synchronized public void fire(PropertyChangeSupport propertyChangeSupport) {
        String lastChanges = toString();
        if (lastChanges != null && lastChanges.length() > 0) {
            propertyChangeSupport.firePropertyChange("LastChange", previousValue, lastChanges);
            reset();
        }
    }


    synchronized public String toString() {
        if (!event.hasChanges()) return "";
        try {
            return parser.generate(event);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
