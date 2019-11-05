public class Observableunsafefilteredsublist {









































































	public boolean add(E e)
	{
		final int previousSize = indexes.size();
		super.add(e);
		return indexes.size() != previousSize;
	}


	public boolean remove(Object o)
	{
		final int previousSize = indexes.size();
		super.remove(o);
		return indexes.size() != previousSize;
	}






















































	public E get(int index)
	{
		return super.get(indexes.get(index));
	}


	public E set(int index, E element)
	{
		return super.set(indexes.get(index), element);
	}


	public void add(int index, E element)
	{
		super.add(indexes.get(index), element);
	}


	public E remove(int index)
	{
		return super.remove((int) indexes.get(index));
	}




























	public List<E> subList(int fromIndex, int toIndex)
	{
		final ObservableList<E> subList = (ObservableList<E>) super.subList(indexes.get(fromIndex), indexes.get(toIndex));

		subList.addCollectionChangeListener(new CollectionChangeHandler());
		return new ObservableUnsafeFilteredSubList<E>(subList, filterPredicate);
	}



















	public void setFilter(Predicate<? super E> filterPredicate)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(this);

		if (filterPredicate != null)
		{
			this.filterPredicate = filterPredicate;
		}
		else
		{
			this.filterPredicate = Predicates.alwaysTrue();
		}

		refreshIndexesMap();

		ListDifference<E> difference = ListDifference.difference(oldList, ImmutableList.copyOf(this));
		CollectionChangeEvent<E> newCollectionChangeEvent = getSupport().newCollectionChangeEvent(difference);
		getSupport().fireCollectionChangeEvent(newCollectionChangeEvent);
	}










































	public static <E> ObservableUnsafeFilteredSubList<E> of(ObservableList<? super E> source, Predicate<? super E> filterPredicate)
	{
		return new ObservableUnsafeFilteredSubList(source, filterPredicate);
	}
}
