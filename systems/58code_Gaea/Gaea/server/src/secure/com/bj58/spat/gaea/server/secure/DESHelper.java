public class Deshelper {












































	public static DESHelper getInstance(String key) throws Exception {
		return new DESHelper(key);
	}
	





































	public byte[] encrypt(byte[] src) throws Exception {
		return encryptCipher.doFinal(src);
	}







	public byte[] decrypt(byte[] dest) throws Exception {
		return decryptCipher.doFinal(dest);
	}
}
