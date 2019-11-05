public class Observablemapdecorator {




































































































	public V get(Object key)
	{
		return internal.get(key);
	}








	public V put(K key, V value)
	{
		getSupport().createSnapshot();
		V result = internal.put(key, value);
		getSupport().fireCollectionChangeEvent(key);
		return result;
	}







	public V remove(Object key)
	{
		getSupport().createSnapshot();
		V result = internal.remove(key);
		getSupport().fireCollectionChangeEvent(key);
		return result;
	}


















	public void clear()
	{
		getSupport().createSnapshot();
		internal.clear();
		getSupport().fireCollectionChangeEvent();
	}






	public Set<K> keySet()
	{
		return new ObservableSetDecorator<K>(internal.keySet(), (InternalObservableCollectionSupport) getSupport());
	}






	public Collection<V> values()
	{
		return new ObservableCollectionDecorator(internal.values(), getSupport());
	}






	public Set<java.util.Map.Entry<K, V>> entrySet()
	{
		return new ObservableSetDecorator<Map.Entry<K, V>>(internal.entrySet(), getSupport());
	}










































	public void addCollectionChangeListener(CollectionChangeListener<Map.Entry<K, V>> listener)
	{
		getSupport().addCollectionChangeListener(listener);
	}














	public Map<K, V> copyInternal()
	{
		return Maps.newHashMap(internal);
	}


	public void apply(CollectionDifference<java.util.Map.Entry<K, V>> difference)
	{
		for (java.util.Map.Entry<K, V> added : difference.getAdded())
		{
			put(added.getKey(), added.getValue());
		}

		for (java.util.Map.Entry<K, V> removed : difference.getRemoved())
		{
			remove(removed.getKey());
		}
	}


	public void unapply(CollectionDifference<java.util.Map.Entry<K, V>> difference)
	{
		for (java.util.Map.Entry<K, V> added : difference.getAdded())
		{
			remove(added.getKey());
		}

		for (java.util.Map.Entry<K, V> removed : difference.getRemoved())
		{
			put(removed.getKey(), removed.getValue());
		}
	}












}
