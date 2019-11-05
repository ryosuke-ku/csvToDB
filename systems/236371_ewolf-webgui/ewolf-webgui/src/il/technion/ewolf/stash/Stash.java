public class Stash {
















































	public void login(SecretKey groupMasterKey) {
		this.groupMasterKey = groupMasterKey;
	}
	
	public void put(Key key, Serializable obj, Group group) {
		chunkeeper.store(key, group.encrypt(obj));
	}
	
	public List<LazyChunkDecryptor> get(Key key) {
		Set<Chunk> chunks = chunkeeper.findChunk(key);
		
		List<LazyChunkDecryptor> $ = new ArrayList<LazyChunkDecryptor>();
		
		for (final Chunk c : chunks) {
			lazyChunkDecryptorProvider.get()
				.setChunk(c)
				.addTo($);
		}
		return $;
	}
	

	public Group createGroup() {
		Group g = groupProvider.get();
		addGroup(g);
		return g;
	}
	

	public List<Group> getAllGroups() {
		List<Group> $ = new ArrayList<Group>();
		
		for (int i=0; ; ++i) {
		
			Key groupKey = keyFactory.create(
					Base64.encodeBase64String(groupMasterKey.getEncoded()),
					"groups",
					Integer.toString(i));
			
			Set<Chunk> chnuks = chunkeeper.findChunk(groupKey);
			
			List<Group> g = new ArrayList<Group>();
			for (Chunk c : chnuks) {
				try {
					@SuppressWarnings("unchecked")
					EncryptedObject<Group> encGroup = (EncryptedObject<Group>)c.download();
					
					g.add(encGroup.decrypt(groupMasterKey));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			$.addAll(g);
			
			if (g.isEmpty()) {
				return $;
			}
		}
		
	}
	
	public void addGroup(Group g) {
		if (g.getGroupId() == null || g.getGroupSecretKey() == null)
			throw new IllegalArgumentException("group is incomplete");
		
		try {
			getGroupFromId(g.getGroupId());
			return; // group already exists
		} catch (GroupNotFoundException e1) {
		}
		
		int nrGroups = getAllGroups().size();
		Key newGroupKey = keyFactory.create(
				Base64.encodeBase64String(groupMasterKey.getEncoded()),
				"groups",
				Integer.toString(nrGroups));
		
		chunkeeper.store(newGroupKey, new EncryptedObject<Group>().encrypt(g, groupMasterKey));
		groupsCache.put(g.getGroupId(), g);
	}












}
