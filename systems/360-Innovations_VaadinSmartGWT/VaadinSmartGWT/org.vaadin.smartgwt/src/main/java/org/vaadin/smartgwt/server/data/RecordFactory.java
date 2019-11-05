public class Recordfactory {
























	public Record newRecord(JsonNode node) {
		if (!node.isNullNode()) {
			final Record record = new Record();
			updater.update(record, node);
			return record;
		} else {
			return null;
		}
	}







	public Record[] newRecords(List<JsonNode> nodes) {
		final Record[] records = new Record[nodes.size()];
		for (int i = 0; i < records.length; i++) {
			records[i] = newRecord(nodes.get(i));
		}
		return records;
	}
}
