public class Mockjedis {
































































































	public byte[] get(final byte[] key) {
		return pipeline.get(key).get();
	}






















	public Long del(final byte[]... keys) {
		return pipeline.del(keys).get();
	}







	public Set<byte[]> keys(final byte[] pattern) {
		return pipeline.keys(pattern).get();
	}



























	public Long incrBy(final byte[] key, final long integer) {
		return pipeline.incrBy(key, integer).get();
	}


	public Double incrByFloat(final String key, final double integer) {
		return pipeline.incrByFloat(key, integer).get();
	}


	public Double incrByFloat(final byte[] key, final double integer) {
		return pipeline.incrByFloat(key, integer).get();
	}












	public Long incr(final byte[] key) {
		return pipeline.incr(key).get();
	}












	public List<String> sort(final String key) {
		return pipeline.sort(key).get();
	}


	public List<byte[]> sort(final byte[] key) {
		return pipeline.sort(key).get();
	}


	public Long sort(final String key, final String dstkey) {
		return pipeline.sort(key, dstkey).get();
	}


	public Long sort(final byte[] key, final byte[] dstkey) {
		return pipeline.sort(key, dstkey).get();
	}


	public List<String> sort(final String key, final SortingParams sortingParameters) {
		return pipeline.sort(key, sortingParameters).get();
	}


	public List<byte[]> sort(final byte[] key, final SortingParams sortingParameters) {
		return pipeline.sort(key, sortingParameters).get();
	}


	public Long sort(final String key, final SortingParams sortingParameters, final String dstkey) {
		return pipeline.sort(key, sortingParameters, dstkey).get();
	}


	public Long sort(final byte[] key, final SortingParams sortingParameters, final byte[] dstkey) {
		return pipeline.sort(key, sortingParameters, dstkey).get();
	}


	public Long hset(final byte[] key, final byte[] field, final byte[] value) {
		return pipeline.hset(key, field, value).get();
	}


	public byte[] hget(final byte[] key, final byte[] field) {
		return pipeline.hget(key, field).get();
	}












	public Long hincrBy(final byte[] key, final byte[] field, final long value) {
		return pipeline.hincrBy(key, field, value).get();
	}


	public Boolean hexists(final byte[] key, final byte[] field) {
		return pipeline.hexists(key, field).get();
	}


	public Long hdel(final byte[] key, final byte[]... fields) {
		return pipeline.hdel(key, fields).get();
	}


	public Long hlen(final byte[] key) {
		return pipeline.hlen(key).get();
	}


	public Set<byte[]> hkeys(final byte[] key) {
		return pipeline.hkeys(key).get();
	}


	public List<byte[]> hvals(final byte[] key) {
		return pipeline.hvals(key).get();
	}


	public Map<byte[], byte[]> hgetAll(final byte[] key) {
		return pipeline.hgetAll(key).get();
	}







	public Long dbSize() {
		return pipeline.dbSize().get();
	}












	public Long move(final String key, final int dbIndex) {
		return pipeline.move(key, dbIndex).get();
	}


	public Long move(final byte[] key, final int dbIndex) {
		return pipeline.move(key, dbIndex).get();
	}


	public String select(final int dbIndex) {
		return pipeline.select(dbIndex).get();
	}


	public String set(final String key, final String value) {
		return pipeline.set(key, value).get();
	}


	public String set(final byte[] key, final byte[] value) {
		return pipeline.set(key, value).get();
	}












	public String get(final String key) {
		return pipeline.get(key).get();
	}















































	public String set(final String key, final String value, final String nxxx) {
		return pipeline.set(key, value, nxxx).get();
	}


	public String set(final String key, final String value, final String nxxx, final String expx, final int time) {
		return pipeline.set(key, value, nxxx, expx, time).get();
	}

























































	public Boolean exists(final String key) {
		return pipeline.exists(key).get();
	}


	public Boolean exists(final byte[] key) {
		return pipeline.exists(key).get();
	}


	public Long incr(final String key) {
		return pipeline.incr(key).get();
	}


	public Long incrBy(final String key, final long integer) {
		return pipeline.incrBy(key, integer).get();
	}












	public Long del(final String... keys) {
		return pipeline.del(keys).get();
	}


	public Long del(final String key) {
		return pipeline.del(key).get();
	}


	public Long del(final byte[] key) {
		return pipeline.del(key).get();
	}


	public Long hset(final String key, final String field, final String value) {
		return pipeline.hset(key, field, value).get();
	}












	public String hget(final String key, final String field) {
		return pipeline.hget(key, field).get();
	}


	public Map<String, String> hgetAll(final String key) {
		return pipeline.hgetAll(key).get();
	}


	public Set<String> hkeys(final String key) {
		return pipeline.hkeys(key).get();
	}


	public List<String> hvals(final String key) {
		return pipeline.hvals(key).get();
	}












	public Long hincrBy(final String key, final String field, final long value) {
		return pipeline.hincrBy(key, field, value).get();
	}


	public Double hincrByFloat(final String key, final String field, final double value) {
		return pipeline.hincrByFloat(key, field, value).get();
	}


	public Long hdel(final String key, final String... fields) {
		return pipeline.hdel(key, fields).get();
	}


	public Boolean hexists(final String key, final String field) {
		return pipeline.hexists(key, field).get();
	}


	public Long hlen(final String key) {
		return pipeline.hlen(key).get();
	}


	public Long lpush(final String key, final String... strings) {
		return pipeline.lpush(key, strings).get();
	}


	public Long lpush(final byte[] key, final byte[]... strings) {
		return pipeline.lpush(key, strings).get();
	}


	public Long sadd(final String key, final String... members) {
		return pipeline.sadd(key, members).get();
	}


	public Long sadd(final byte[] key, final byte[]... members) {
		return pipeline.sadd(key, members).get();
	}




















































	public Boolean sismember(final String key, final String member) {
		return pipeline.sismember(key, member).get();
	}


	public Boolean sismember(final byte[] key, final byte[] member) {
		return pipeline.sismember(key, member).get();
	}


	public Set<String> smembers(final String key) {
		return pipeline.smembers(key).get();
	}


	public Set<byte[]> smembers(final byte[] key) {
		return pipeline.smembers(key).get();
	}
































	public Long srem(final String key, final String... members) {
		return pipeline.srem(key, members).get();
	}


	public Long srem(final byte[] key, final byte[]... members) {
		return pipeline.srem(key, members).get();
	}






















	public String lpop(final String key) {
		return pipeline.lpop(key).get();
	}


	public byte[] lpop(final byte[] key) {
		return pipeline.lpop(key).get();
	}


	public Long llen(final String key) {
		return pipeline.llen(key).get();
	}


	public Long llen(final byte[] key) {
		return pipeline.llen(key).get();
	}


	public List<String> lrange(final String key, final long start, final long end) {
		return pipeline.lrange(key, start, end).get();
	}


	public List<byte[]> lrange(final byte[] key, final long start, final long end) {
		return pipeline.lrange(key, start, end).get();
	}







	public Set<String> keys(final String pattern) {
		return pipeline.keys(pattern).get();
	}


























	public String set(String key, String value, String nxxx, String expx, long time) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
	}






























































































































































































































































































































































































































































































































































































































































































































	public String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, long time) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
	}







	public Double hincrByFloat(byte[] key, byte[] field, double value) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
	}


















































































































































































































































































































































































































































































































































































	public String set(byte[] key, byte[] value, byte[] nxxx) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
	}


	public String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, int time) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
	}


























































































}
