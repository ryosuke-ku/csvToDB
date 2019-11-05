public class Selectionupdatedevent {






















	public static Type<SelectionUpdatedHandler> getType() {
		return TYPE == null ? TYPE = new Type<SelectionUpdatedHandler>() : TYPE;
	}






	public Type<SelectionUpdatedHandler> getAssociatedType() {
		return getType();
	}


	protected void dispatch(SelectionUpdatedHandler handler) {
		handler.onSelectionUpdated(this);
	}












	public int hashCode() {
		return Objects.hashCode(getSource());
	}
}
