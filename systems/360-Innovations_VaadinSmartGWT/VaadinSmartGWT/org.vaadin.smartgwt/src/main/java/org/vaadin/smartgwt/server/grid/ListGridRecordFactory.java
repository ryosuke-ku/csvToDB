public class Listgridrecordfactory {


























	public ListGridRecord newListGridRecord(JsonNode node) {
		if (!node.isNullNode()) {
			final ListGridRecord record = new ListGridRecord();
			updater.update(record, node);
			return record;
		} else {
			return null;
		}
	}







	public ListGridRecord[] newListGridRecords(List<JsonNode> nodes) {
		final ListGridRecord[] records = new ListGridRecord[nodes.size()];
		for (int i = 0; i < records.length; i++) {
			records[i] = newListGridRecord(nodes.get(i));
		}
		return records;
	}
}
