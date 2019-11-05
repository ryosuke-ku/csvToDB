public class Undoredocollectionvaluemodel {














































	protected CollectionChangeListener getUndoRedoManagerPushHandler()
	{
		return collectionChangeHandler;
	}


	public void addCollectionChangeListener(CollectionChangeListener listener)
	{
		observableCollectionSupport.addCollectionChangeListener(listener);
	}


	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{
		observableCollectionSupport.removeCollectionChangeListener(listener);
	}

	public void undo(CollectionChangeEvent event)
	{
		try
		{
			getValueModel().removeCollectionChangeListener(this.collectionChangeHandler);
			getUndoRedoManager().beginTransaction();
			CollectionDifference difference = event.getDifference();

			ObservableCollection source = getValueModel();
			source.unapply(difference);

		}
		finally
		{
			getValueModel().addCollectionChangeListener(this.collectionChangeHandler);
			getUndoRedoManager().endTransaction();
		}
	}

	public void redo(CollectionChangeEvent event)
	{
		try
		{
			getValueModel().removeCollectionChangeListener(this.collectionChangeHandler);
			getUndoRedoManager().beginTransaction();

			CollectionDifference difference = event.getDifference();

			// Always use the ValueModel's value since when an entity is reloaded, a new collection
			// containing the same objects is recreated
			ObservableCollection source = getValueModel();

			source.apply(difference);

		}
		finally
		{
			getValueModel().addCollectionChangeListener(this.collectionChangeHandler);
			getUndoRedoManager().endTransaction();
		}
	}


	public E get(int index)
	{
		return getValueModel().get(index);
	}


	public E set(int index, E newValue)
	{
		return getValueModel().set(index, newValue);
	}


	public boolean add(E e)
	{
		return getValueModel().add(e);
	}


	public boolean addAll(Collection c)
	{
		return getValueModel().addAll(c);
	}


	public void clear()
	{
		getValueModel().clear();
	}


	public boolean contains(Object e)
	{
		return getValueModel().contains(e);
	}


	public boolean containsAll(Collection c)
	{
		return getValueModel().containsAll(c);
	}


	public boolean isEmpty()
	{
		return getValueModel().isEmpty();
	}


	public Iterator iterator()
	{
		return getValueModel().iterator();
	}


	public boolean remove(Object e)
	{
		return getValueModel().remove(e);
	}


	public boolean removeAll(Collection c)
	{
		return getValueModel().removeAll(c);
	}


	public boolean retainAll(Collection c)
	{
		return getValueModel().retainAll(c);
	}


	public int size()
	{
		return getValueModel().size();
	}


	public void executeBatchAction(BatchAction action)
	{
		getValueModel().executeBatchAction(action);
	}


	public Object[] toArray()
	{
		return getValueModel().toArray();
	}


	public Object[] toArray(Object[] a)
	{
		return getValueModel().toArray(a);
	}


	public void add(int index, E element)
	{
		getValueModel().add(index, element);
	}


	public boolean addAll(int index, Collection<? extends E> c)
	{
		return getValueModel().addAll(index, c);
	}


























	public E remove(int index)
	{
		return getValueModel().remove(index);
	}








	public void apply(CollectionDifference<E> difference)
	{
		getValueModel().apply(difference);
	}


	public void unapply(CollectionDifference<E> difference)
	{
		getValueModel().unapply(difference);
	}


	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return observableCollectionSupport.getCollectionChangeListeners();
	}


	public void dispose()
	{
		T valueModel = getValueModel();

		if (valueModel != null)
		{
			valueModel.removeCollectionChangeListener(collectionChangeHandler);
		}
	}














}
