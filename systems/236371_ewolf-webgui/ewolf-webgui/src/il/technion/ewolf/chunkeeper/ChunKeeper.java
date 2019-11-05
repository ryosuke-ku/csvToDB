public class Chunkeeper {
























































	public void bind() {
		for (AbstractHandler h : handlers) {
			h.register(connector);
		}
	}
	














	public void store(Key key, Serializable data) {
		ByteArrayOutputStream bout = null;
		ObjectOutputStream oout = null;
		try {
			bout = new ByteArrayOutputStream();
			oout = new ObjectOutputStream(bout);
			oout.writeObject(data);
			oout.flush();
			chunkStore.store(key, bout.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException("could not serialize chunk", e);
		} finally {
			try { bout.close(); } catch (Exception e) {}
			try { oout.close(); } catch (Exception e) {}
		}
	}
	





	public Set<Chunk> findChunk(Key key) {
		Chunk localResults = chunkStore.get(key);
		if (localResults != null) {
			return Collections.singleton(localResults);
		}
		List<Serializable> searchResults = chucksDHT.get(key);
		Set<Chunk> $ = new HashSet<Chunk>();
		for (Serializable s : searchResults) {
			
			if (!ChunkLocator.class.equals(s.getClass()))
				continue;
			
			ChunkLocator locator = (ChunkLocator)s;
			
			boolean wasAdded = false;
			for (Chunk c : $) {
				if (c.addLocator(locator)) {
					wasAdded = true;
					break;
				}
			}
			if (!wasAdded) {
				Chunk c = chunkProvider.get();
				c.addLocator(locator);
				$.add(c);
			}
		}
		return $;
	}
	














































}
