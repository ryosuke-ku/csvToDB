public class Rsahelper {

























































	public static RSAHelper getInstance() throws Exception {
		return new RSAHelper();
	}
	







	public static RSAHelper getInstance(String pubModulus, String pubExponent) throws Exception {
		return new RSAHelper(pubModulus, pubExponent);
	}
	




































































	public byte[] encryptByPublicKey(byte[] source) throws Exception {
		return pubEncryptCipher.doFinal(source);
	}









	public byte[] decryptByPublicKey(byte[] dest) throws Exception {
		return pubDecryptCipher.doFinal(dest);
	}
	








	public byte[] encryptByPrivateKey(byte[] source) throws Exception {
		if(priEncryptCipher == null) {
			throw new Exception("私钥不存在,加密失败");
		}
		return priEncryptCipher.doFinal(source);
	}









	public byte[] decryptByPrivateKey(byte[] dest) throws Exception {
		if(priDecryptCipher == null) {
			throw new Exception("私钥不存在,解密失败");
		}
		return priDecryptCipher.doFinal(dest);
	}


	public String getPubModulus() {
		return pubModulus;
	}

	public String getPubExponent() {
		return pubExponent;
	}
}
