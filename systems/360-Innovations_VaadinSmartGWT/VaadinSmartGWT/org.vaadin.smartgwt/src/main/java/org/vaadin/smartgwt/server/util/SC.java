public class Sc {






























		public ServerSideProxy newServerSideProxy(ServerSideHandler handler) {
			return new ServerSideProxy(handler);
		}
	}
















































	public void confirm(String message, BooleanCallback callback) {
		confirm(null, message, callback);
	}










	public void confirm(String title, String message, BooleanCallback callback) {
		final int key = incrementor.increment();
		client.call("confirm", key, message, title);
		callbacks.put(key, callback);
	}








	public void ask(String message, BooleanCallback callback) {
		ask(null, message, callback);
	}









	public void ask(String title, String message, BooleanCallback callback) {
		final int key = incrementor.increment();
		client.call("ask", key, title, message);
		callbacks.put(key, callback);
	}


	public void changeVariables(final Object source, final Map variables) {
		client.changeVariables(source, variables);
		if (callBacks.size() > 0) {
			BooleanCallback bcp = (BooleanCallback) callBacks.pop();
			bcp.execute((Boolean) variables.get("callback"));
		}

		if (variables.containsKey("callbackKey")) {
			final BooleanCallback callback = callbacks.get(variables.get("callbackKey"));
			callback.execute("null".equals(variables.get("callback")) ? null : (Boolean) variables.get("callback"));
		}
	}























}
