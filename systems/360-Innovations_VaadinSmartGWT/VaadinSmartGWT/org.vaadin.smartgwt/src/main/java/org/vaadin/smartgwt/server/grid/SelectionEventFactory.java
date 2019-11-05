public class Selectioneventfactory {




























	public SelectionEvent newSelectionEvent(JsonNode node) {
		final Record record = recordFactory.newRecord(node.getNode("record"));
		final boolean state = node.getBooleanValue("state");
		final ListGridRecord[] selection = listGridRecordFactory.newListGridRecords(node.getArrayNode("selection"));
		final ListGridRecord selectedRecord = listGridRecordFactory.newListGridRecord(node.getNode("selectedRecord"));
		return new SelectionEvent(record, state, selection, selectedRecord);
	}
}
