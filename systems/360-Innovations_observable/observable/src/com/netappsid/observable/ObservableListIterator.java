public class Observablelistiterator {




















	public void add(E e)
	{
		internal.add(e);
		final int eventIndex = internal.previousIndex() != -1 ? internal.previousIndex() : 0;
		final CollectionChangeEvent event = support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.<E> of(), ImmutableList.of(e)), eventIndex);
		support.fireCollectionChangeEvent(event);
	}














	public E next()
	{
		index = nextIndex();
		element = internal.next();
		return element;
	}


	public int nextIndex()
	{
		return internal.nextIndex();
	}
















	public void remove()
	{
		internal.remove();
		support.fireCollectionChangeEvent(support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(element), ImmutableList.<E> of()), index));
	}


	public void set(E e)
	{
		internal.set(e);
		support.fireCollectionChangeEvent(support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(element), ImmutableList.of(e)), index));
	}
}
