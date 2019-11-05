public class Clickevent {






















	public static Event.Type<ClickHandler> getType() {
		return TYPE == null ? TYPE = new Event.Type<ClickHandler>() : TYPE;
	}


	public Event.Type<ClickHandler> getAssociatedType() {
		return getType();
	}


	protected void dispatch(ClickHandler handler) {
		handler.onClick(this);
	}
}
