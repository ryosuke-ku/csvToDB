public class Collectionvalueholder {














































	public void setValue(ObservableList<E> newValue)
	{
		setValue((Object) newValue);
	}


	public void setValue(Object newValue)
	{
		Preconditions.checkArgument(newValue instanceof ObservableList || newValue == null, "can only set an ObservableList or null.");
		getObservableList().removeCollectionChangeListener(collectionChangeHandler);
		super.setValue(newValue == null ? ObservableCollections.newObservableArrayList() : newValue);
		getObservableList().addCollectionChangeListener(collectionChangeHandler);
	}


	public void addCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		collectionChangeSupport.addCollectionChangeListener(listener);
	}








	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return collectionChangeSupport.getCollectionChangeListeners();
	}








	public boolean add(E e)
	{
		return getObservableList().add(e);
	}


























	public boolean isEmpty()
	{
		return getObservableList().isEmpty();
	}


	public Iterator<E> iterator()
	{
		return getObservableList().iterator();
	}


	public boolean remove(Object o)
	{
		return getObservableList().remove(o);
	}














	public int size()
	{
		return getObservableList().size();
	}














	public void add(int index, E element)
	{
		getObservableList().add(index, element);
	}








	public E get(int index)
	{
		return getObservableList().get(index);
	}


























	public E remove(int index)
	{
		return getObservableList().remove(index);
	}


	public E set(int index, E element)
	{
		return getObservableList().set(index, element);
	}


	public List<E> subList(int fromIndex, int toIndex)
	{
		final ObservableList<E> subList = (ObservableList<E>) getObservableList().subList(fromIndex, toIndex);
		subList.addCollectionChangeListener(collectionChangeHandler);
		return subList;
	}





















		public void onCollectionChange(CollectionChangeEvent<E> event)
		{
			collectionChangeSupport.fireCollectionChangeEvent(new CollectionChangeEvent<E>(CollectionValueHolder.this, event.getDifference()));
		}
	}


	public void dispose()
	{
		ObservableList<E> observableList = getObservableList();
		if (observableList != null)
		{
			observableList.removeCollectionChangeListener(collectionChangeHandler);
		}
	}
}
