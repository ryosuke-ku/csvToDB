public class Refresher {



































	public int getInterval() {
		return interval;
	}






	public void setInterval(int interval) {
		assert interval > 0 : "interval must be a positive number";
		this.interval = interval;
	}







	public HandlerRegistration addListener(final RefreshListener listener) {
		assert listener != null : "listener must not be null";
		listeners.add(listener);
		requestRepaint();
		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				listeners.remove(listener);
				requestRepaint();
			}
		};
	}


	public void paintContent(PaintTarget target) throws PaintException {
		if (!listeners.isEmpty()) {
			target.addAttribute("interval", interval);
		}
	}


	public void changeVariables(Object source, Map<String, Object> variables) {
		for (RefreshListener listener : ImmutableList.copyOf(listeners)) {
			listener.refresh(this);
		}
	}
}
