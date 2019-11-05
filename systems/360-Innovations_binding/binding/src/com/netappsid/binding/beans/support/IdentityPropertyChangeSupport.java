public class Identitypropertychangesupport {
















	public void fireIdentityPropertyChange(String propertyName, Object oldValue, Object newValue)
	{
		fireIdentityPropertyChange(new PropertyChangeEvent(source, propertyName, oldValue, newValue));
	}

	public void fireIdentityPropertyChange(PropertyChangeEvent evt)
	{
		if (evt.getOldValue() == null || evt.getOldValue() != evt.getNewValue())
		{
			for (PropertyChangeListener listener : getPropertyChangeListeners())
			{
				if (listener instanceof PropertyChangeListenerProxy)
				{
					final PropertyChangeListenerProxy proxy = (PropertyChangeListenerProxy) listener;
					
					if (proxy.getPropertyName().equals(evt.getPropertyName()))
					{
						proxy.propertyChange(evt);
					}
				}
				else
				{
					listener.propertyChange(evt);
				}
			}
		}
	}
}
