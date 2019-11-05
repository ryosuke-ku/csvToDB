public class Beanadapter {


































































	public ValueModel getBeanChannel()
	{
		return beanChannel;
	}

	public Object getBean()
	{
		return beanChannel.getValue();
	}

	public void setBean(Object newBean)
	{
		beanChannel.setValue(newBean);
	}

	public Object getValue(String propertyName)
	{
		return getValueModel(propertyName).getValue();
	}

	public void setValue(String propertyName, Object newValue)
	{
		getValueModel(propertyName).setValue(newValue);
	}

	public SimplePropertyAdapter getValueModel(String propertyName)
	{
		Validate.notNull(propertyName, "The property name must not be null.");

		SimplePropertyAdapter registeredPropertyAdapter = propertyAdapters.get(propertyName);

		if (registeredPropertyAdapter == null)
		{
			registeredPropertyAdapter = new SimplePropertyAdapter(this, propertyName);
			propertyAdapters.put(propertyName, registeredPropertyAdapter);
		}

		return registeredPropertyAdapter;
	}

	public CollectionValueModel getCollectionValueModel(String propertyName)
	{
		CollectionValueModel collectionValueModel = getCollectionValueModelsCache().get(propertyName);

		if (collectionValueModel == null)
		{
			SimplePropertyAdapter propertyAdapter = getValueModel(propertyName);
			collectionValueModel = new IndexedCollectionValueModel(propertyAdapter, getChangeSupportFactory(), observableCollectionSupportFactory);
			getCollectionValueModelsCache().put(propertyName, collectionValueModel);
		}

		return collectionValueModel;
	}

	public synchronized void addBeanPropertyChangeListener(PropertyChangeListener listener)
	{
		if (listener != null)
		{
			indirectChangeSupport.addPropertyChangeListener(listener);
		}
	}

	public synchronized void removeBeanPropertyChangeListener(PropertyChangeListener listener)
	{
		if (listener != null)
		{
			indirectChangeSupport.removePropertyChangeListener(listener);
		}
	}

	public synchronized void addBeanPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		if (listener != null)
		{
			indirectChangeSupport.addPropertyChangeListener(propertyName, listener);
		}
	}

	public synchronized void removeBeanPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		if (listener != null)
		{
			indirectChangeSupport.removePropertyChangeListener(propertyName, listener);
		}
	}

	public synchronized PropertyChangeListener[] getBeanPropertyChangeListeners()
	{
		return indirectChangeSupport.getPropertyChangeListeners();
	}

	public synchronized PropertyChangeListener[] getBeanPropertyChangeListeners(String propertyName)
	{
		return indirectChangeSupport.getPropertyChangeListeners(propertyName);
	}

	public synchronized void release()
	{
		removeChangeHandlerFrom(getBean());

		// Ensure to dispose every CollectionValueModel in order
		// to release listeners on Collections on the bean itself
		for (CollectionValueModel collectionValueModel : getCollectionValueModelsCache().values())
		{
			collectionValueModel.dispose();
		}

		indirectChangeSupport.removeAll();
		removeChangeHandlerFrom(storedOldBean);
	}






































	protected Map<String, CollectionValueModel> getCollectionValueModelsCache()
	{
		return collectionValueModels;
	}



















		private void setBean(Object oldBean, Object newBean)
		{
			beanAdapter.fireIdentityPropertyChange(PROPERTYNAME_BEFORE_BEAN, oldBean, newBean);
			beanAdapter.removeChangeHandlerFrom(oldBean);
			forwardAllAdaptedValuesChanged(oldBean, newBean);
			beanAdapter.addChangeHandlerTo(newBean);
			beanAdapter.fireIdentityPropertyChange(PROPERTYNAME_BEAN, oldBean, newBean);
			beanAdapter.fireIdentityPropertyChange(PROPERTYNAME_AFTER_BEAN, oldBean, newBean);
		}









		public void dispose()
		{
			beanAdapter = null;
		}
	}






































		public void dispose()
		{
			beanAdapter = null;
		}
	}

	public void dispose()
	{
		if (!disposed)
		{
			propertyAdapters.clear();
			collectionValueModels.clear();

			if (storedOldBean instanceof BeanAdapter)
			{
				((BeanAdapter) storedOldBean).dispose();
			}

			if (propertyChangeHandler != null)
			{
				((PropertyChangeHandler) propertyChangeHandler).dispose();
				propertyChangeHandler = null;
			}

			if (beanChangeHandler != null)
			{
				beanChangeHandler.dispose();
				beanChangeHandler = null;
			}

			if (storedOldBean != null && storedOldBean instanceof BeanAdapter)
			{
				((BeanAdapter) storedOldBean).dispose();
			}
			storedOldBean = null;
			disposed = true;
		}
	}




}
