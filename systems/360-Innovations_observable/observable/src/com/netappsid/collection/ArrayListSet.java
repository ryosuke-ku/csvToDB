public class Arraylistset {
































































	public boolean add(E e)
	{
		assertPossibleDuplication(e);
		return internal.add(e);
	}














	public boolean addAll(Collection<? extends E> c)
	{
		assertPossibleDuplication(c);
		return internal.addAll(c);
	}


	public boolean addAll(int index, Collection<? extends E> c)
	{
		assertPossibleDuplication(c);
		return internal.addAll(index, c);
	}


























	public E set(int index, E element)
	{
		assertPossibleDuplication(element);
		return internal.set(index, element);
	}


	public void add(int index, E element)
	{
		assertPossibleDuplication(element);
		internal.add(index, element);
	}




















	public ListIterator<E> listIterator()
	{
		return new ListSetIterator<E>(this, internal.listIterator());
	}


	public ListIterator<E> listIterator(int index)
	{
		return new ListSetIterator<E>(this, internal.listIterator(index));
	}


	public List<E> subList(int fromIndex, int toIndex)
	{
		return new ArrayListSet<E>(internal.subList(fromIndex, toIndex));
	}










































	public static <E> ArrayListSet<E> of(E... elements)
	{
		final ArrayListSet<E> arrayListSet = new ArrayListSet<E>();

		Collections.addAll(arrayListSet, elements);
		return arrayListSet;
	}








}
