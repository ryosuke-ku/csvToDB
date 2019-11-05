public class Socialfs {







































































	public void login(String password) throws CredentialsNotFoundException {
		System.out.println("searching credentials with password "+password);
		if (cred != null)
			throw new IllegalStateException("already logged in !");
		
		Key credKey = keyFactory.create(password, username);
		MessageDigest md = passwordDigestProvider.get();
		md.update(password.getBytes());
		SecretKey credSecretKey = new SecretKeySpec(md.digest(), credentialsEncryptionAlgorithm);
		
		for (Chunk c : chunkeeper.findChunk(credKey)) {
			try {
				Serializable s = c.download();
				if (!s.getClass().equals(EncryptedObject.class))
					continue;
				
				@SuppressWarnings("unchecked")
				EncryptedObject<Credentials> encCred = (EncryptedObject<Credentials>)s;
				
				cred = encCred.decrypt(credSecretKey)
							.setCredentialsKey(credSecretKey);
				
			} catch (Exception e) {
				// wrong credentials, moving on to the next
				e.printStackTrace();
			}
		}
		if (cred == null)
			throw new CredentialsNotFoundException();
		
		System.out.println("found credentials: "+cred.getCredentialsKey());
		
		cred.getProfile()
			.setTransientParams(stash, fileCache, uidFactory);
		
		// TODO set chunkeeper keys
		//chunkeeper.login(cred.getChunkeeperKeys());
		
		fileFactory.login(cred);
		stash.login(cred.getGroupsMasterKey());
		
		reinsertProfileTaskProvider.get()
			.setCredentials(cred)
			.register();
	}
	
	public Credentials getCredentials() {
		return cred;
	}
	
	public Profile findProfile(UserID uid) throws ProfileNotFoundException {
		if (cred.getProfile().getUserId().equals(uid))
			return cred.getProfile();
		
		Key k = uid.getKey();
		Profile cachedProfile = profileCache.search(k);
		if (cachedProfile != null)
			return cachedProfile;
		
		List<Serializable> profiles = profileDHT.get(k);
		
		for (Serializable s : profiles) {
			
			if (!(s instanceof Profile))
				continue;
			
			Profile p = ((Profile)s)
				.setTransientParams(stash, fileCache, uidFactory);
			
			if (p.getUserId().equals(uid)) {
				profileCache.insert(p);
				return p;
			}
		}
		
		throw new ProfileNotFoundException();
	}
	
	public SFSFileFactory getSFSFileFactory() {
		return fileFactory;
	}
	
	public Stash getStash() {
		return stash;
	}


	private String __toString(String prefix, SFSFile f) throws Exception {
		prefix += f.getName();
		int i=0;
		String $ = "";
		do {
			try {
				$ += __toString(prefix + "/", f.getSubFiles(i++));
			} catch (FileNotFoundException e) {
				return i == 1 ? prefix + "\n" : prefix + "/\n"+$;
			}
		} while (true);
	}
	










}
