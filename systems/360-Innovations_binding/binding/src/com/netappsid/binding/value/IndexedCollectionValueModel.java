public class Indexedcollectionvaluemodel {


























	public void add(int index, T element)
	{
		validateNotNull(getValue()).add(index, element);
	}


	public boolean add(T e)
	{
		return validateNotNull(getValue()).add(e);
	}


	public boolean addAll(Collection<? extends T> c)
	{
		return validateNotNull(getValue()).addAll(c);
	}


	public boolean addAll(int index, Collection<? extends T> c)
	{
		return validateNotNull(getValue()).addAll(index, c);
	}


	public void clear()
	{
		validateNotNull(getValue()).clear();
	}
























	public boolean contains(Object o)
	{
		final ObservableList<T> value = getValue();
		return (value == null) ? false : value.contains(o);
	}


	public boolean containsAll(Collection<?> c)
	{
		final ObservableList<T> value = getValue();
		return (value == null) ? false : value.containsAll(c);
	}








	public void executeBatchAction(BatchAction action)
	{
		validateNotNull(getValue()).executeBatchAction(action);
	}


	public T get(int index)
	{
		return validateNotNull(getValue()).get(index);
	}








	public int indexOf(Object o)
	{
		final ObservableList<T> value = getValue();
		return (value == null) ? -1 : value.indexOf(o);
	}


	public boolean isEmpty()
	{
		final ObservableList<T> value = getValue();
		return (value == null) ? true : value.isEmpty();
	}


	public Iterator<T> iterator()
	{
		final ObservableList<T> value = getValue();
		return (value == null) ? Collections.EMPTY_LIST.iterator() : value.iterator();
	}


	public int lastIndexOf(Object o)
	{
		final ObservableList<T> value = getValue();
		return (value == null) ? -1 : value.lastIndexOf(o);
	}














	public T remove(int index)
	{
		return validateNotNull(getValue()).remove(index);
	}


	public boolean remove(Object o)
	{
		return validateNotNull(getValue()).remove(o);
	}


	public boolean removeAll(Collection<?> c)
	{
		return validateNotNull(getValue()).removeAll(c);
	}


	public boolean retainAll(Collection<?> c)
	{
		return validateNotNull(getValue()).retainAll(c);
	}


	public T set(int index, T element)
	{
		return validateNotNull(getValue()).set(index, element);
	}


	public int size()
	{
		final ObservableList<T> value = getValue();
		return (value == null) ? 0 : value.size();
	}








	public Object[] toArray()
	{
		return validateNotNull(getValue()).toArray();
	}


	public <AT> AT[] toArray(AT[] a)
	{
		return validateNotNull(getValue()).toArray(a);
	}












	public ImmutableList<CollectionChangeListener<T>> getCollectionChangeListeners()
	{
		return ImmutableList.copyOf(getSupport().getCollectionChangeListeners());
	}
}
