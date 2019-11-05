public class Observablecollections {






















	public static <E> ObservableSet<E> newObservableHashSet(E... elements)
	{
		return decorateSet(newHashSet(elements));
	}
















	public static <E> ObservableSet<E> newObservableHashSet(Iterator<E> iterator)
	{
		return decorateSet(newHashSet(iterator));
	}

	public static <E> ObservableList<E> newObservableArrayList(E... elements)
	{
		return decorateList(newArrayList(elements));
	}

	public static <E> ObservableList<E> newObservableArrayList(Iterator<E> iterator)
	{
		return decorateList(newArrayList(iterator));
	}











	public static <E> ObservableList<E> decorateList(List<E> source)
	{
		return new ObservableListDecorator(source);
	}















}
