public class Protocolhelper {











































	public static Object fromBytes(byte[] buffer) throws Exception {
		if(buffer != null && buffer.length > 0) {
			int version = buffer[0];
			if(version == com.bj58.spat.gaea.protocol.sfp.v1.Protocol.VERSION) {
				return com.bj58.spat.gaea.protocol.sfp.v1.Protocol.fromBytes(buffer);
			}
		}
		
		throw new Exception("不完整的二进制流");
	}
	

















}
