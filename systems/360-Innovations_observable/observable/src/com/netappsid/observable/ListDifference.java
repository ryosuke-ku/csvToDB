public class Listdifference {






























	public static <E> ListDifference<E> difference(ImmutableList<E> oldList, ImmutableList<E> newList)
	{
		final List<E> oldListCopy = newArrayList(oldList);
		final List<E> newListCopy = newArrayList(newList);
		boolean hasMovedIndexes = false;

		for (ListIterator<E> oldListIterator = oldListCopy.listIterator(); oldListIterator.hasNext();)
		{
			final E leftElement = oldListIterator.next();

			for (ListIterator<E> newListIterator = newListCopy.listIterator(); newListIterator.hasNext();)
			{
				if (Objects.equal(leftElement, newListIterator.next()))
				{
					Integer leftIndex = oldListIterator.nextIndex() - 1;
					Integer rightIndex = newListIterator.nextIndex() - 1;

					oldListIterator.remove();
					newListIterator.remove();

					if (!hasMovedIndexes && !leftIndex.equals(rightIndex))
					{
						hasMovedIndexes = true;
					}

					break;
				}
			}

		}

		return new ListDifference<E>(ImmutableList.copyOf(oldListCopy), ImmutableList.copyOf(newListCopy), hasMovedIndexes);
	}













}
