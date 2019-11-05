public class Mastercontainer {




































	public Iterator<NonUIComponent> getNonUIComponentIterator() {
		return nonUIComponents.iterator();
	}






	public void addNonUIComponent(NonUIComponent component) {
		nonUIComponents.add(component);
	}






	public void removeNonUIComponent(NonUIComponent component) {
		nonUIComponents.remove(component);
	}



















	public RegistrationEntry register(final Window window) {
		this.window.add(window);
		requestRepaint();

		return new RegistrationEntry() {
			@Override
			public void unregister() {
				MasterContainer.this.window.remove(window);
				requestRepaint();
			}

			@Override
			public boolean isRegistered() {
				return MasterContainer.this.window.contains(window);
			}
		};
	}


	public void paintContent(PaintTarget target) throws PaintException {
		paintablePropertyPainter.paintContent(target);
		super.paintContent(target);
	}





































	public void addListener(ComponentAttachListener listener) {

	}







	public void addListener(ComponentDetachListener listener) {

	}





}
