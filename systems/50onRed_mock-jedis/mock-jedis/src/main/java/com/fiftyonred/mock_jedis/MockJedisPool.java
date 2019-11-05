public class Mockjedispool {













	public Jedis getResource() {
		if (client == null) {
			client = new MockJedis("localhost");
		}
		return client;
	}


	public void returnResource(final Jedis resource) {

	}














}
