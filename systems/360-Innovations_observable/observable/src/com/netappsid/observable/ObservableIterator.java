public class Observableiterator {
























	public E next()
	{
		next = internal.next();
		return next;
	}


	public void remove()
	{
		internal.remove();
		observableSupport.fireCollectionChangeEvent(observableSupport.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(next), ImmutableList
				.<E> of())));
	}
}
