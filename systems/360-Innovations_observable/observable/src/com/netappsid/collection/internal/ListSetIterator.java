public class Listsetiterator {
















	public boolean hasNext()
	{
		return internal.hasNext();
	}

	public E next()
	{
		current = internal.next(); 
		return current;
	}



























	public void set(E e)
	{
		if (source.contains(e) && (current != null && !current.equals(e)))
		{
			throw new IllegalArgumentException("element " + e.toString() + " already present.");
		}

		internal.set(e);
	}










}
