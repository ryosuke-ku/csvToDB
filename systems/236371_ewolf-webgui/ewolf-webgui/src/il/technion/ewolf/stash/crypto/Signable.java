public class Signable {






































	public PrivateKey getPrvSigKey() {
		return prvSigKey;
	}
	
	public Signable setPubSigKey(PublicKey pubSigKey) {
		this.pubSigKey = pubSigKey;
		return this;
	}
	
	public PublicKey getPubSigKey() {
		return pubSigKey;
	}
	
	public Signable setPrvSigKey(PrivateKey privSigKey) {
		this.prvSigKey = privSigKey;
		return this;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
		
		if (signature != null) {
			// simply serialize this object, since the sig already exists
			// I dont need to generate it
			out.defaultWriteObject();
			return;
		}
		
		// no signature was found, generating one !
		
		// missing pubSigKey  ==> other side will not be able to verify
		// missing privSigKey ==> cannot sign
		if (pubSigKey == null || prvSigKey == null)
			throw new NotSerializableException("missing sig keys");
		
		Signature s = Signature.getInstance(prvSigKey.getAlgorithm());
		s.initSign(prvSigKey);
		
		s.update(pubSigKey.getEncoded());
		updateSignature(s);
		
		signature = s.sign();
		
		out.defaultWriteObject();
	}
	


	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.registerValidation(new ObjectInputValidation() {
			
			@Override
			public void validateObject() throws InvalidObjectException {
				try {
					Signable.this.verifySignature();
				} catch (Exception e) {
					throw new InvalidObjectException(e.getMessage());
				}
			}
		}, 0);
		
		in.defaultReadObject();
	}
	




















	byte[] getSignature() {
		return signature;
	}
	
}
