public class Selectionevent {






























	public static Event.Type<SelectionChangedHandler> getType() {
		return TYPE == null ? TYPE = new Event.Type<SelectionChangedHandler>() : TYPE;
	}














	public Event.Type<SelectionChangedHandler> getAssociatedType() {
		return getType();
	}


	protected void dispatch(SelectionChangedHandler handler) {
		handler.onSelectionChanged(this);
	}






	public Record getRecord() {
		return record;
	}






	public boolean getState() {
		return state;
	}






	public ListGridRecord[] getSelection() {
		return selection;
	}









	public ListGridRecord getSelectedRecord() {
		return selectedRecord;
	}
}
