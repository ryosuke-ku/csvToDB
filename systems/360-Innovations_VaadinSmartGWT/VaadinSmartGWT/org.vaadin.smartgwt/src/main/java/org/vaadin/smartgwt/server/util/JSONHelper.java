public class Jsonhelper {












	public static String getJsonString(Record[] records) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

		StringBuffer buffer = new StringBuffer();

		buffer.append('[');

		for (Iterator<Record> iterator = Arrays.asList(records).iterator(); iterator.hasNext();) {
			buffer.append(objectMapper.writeValueAsString(toMap(iterator.next())));

			if (iterator.hasNext()) {
				buffer.append(',');
			}
		}

		buffer.append(']');

		return buffer.toString();
	}






















}
