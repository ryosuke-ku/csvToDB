public class Recorddoubleclickevent {
































	public static Type<RecordDoubleClickHandler> getType() {
		return TYPE == null ? TYPE = new Event.Type<RecordDoubleClickHandler>() : TYPE;
	}

















	public Event.Type<RecordDoubleClickHandler> getAssociatedType() {
		return getType();
	}






	public ListGrid getViewer() {
		return viewer;
	}






	public Record getRecord() {
		return record;
	}






	public int getRecordNum() {
		return recordNum;
	}






	public ListGridField getField() {
		return field;
	}
	





	public int getFieldNum() {
		return fieldNum;
    }


	protected void dispatch(RecordDoubleClickHandler handler) {
		handler.onRecordDoubleClick(this);
	}



















	public int hashCode() {
		return Objects.hashCode(getSource(), viewer, record, recordNum, field, fieldNum);
	}
}
