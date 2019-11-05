public class Observablefilteredlistiterator {


















	public boolean hasNext()
	{
		if (source.hasNext())
		{
			try
			{
				return predicate.apply(source.next()) ? true : hasNext();
			}
			finally
			{
				source.previous();
			}
		}
		else
		{
			return false;
		}
	}


	public E next()
	{
		index++;
		return recursiveNext();
	}


	public boolean hasPrevious()
	{
		if (source.hasPrevious())
		{
			try
			{
				return predicate.apply(source.previous()) ? true : hasPrevious();
			}
			finally
			{
				source.next();
			}
		}
		else
		{
			return false;
		}
	}


	public E previous()
	{
		index--;
		return recursivePrevious();
	}
	

	public int nextIndex()
	{
		return index + 1;
	}


	public int previousIndex()
	{
		return index;
	}


	public void remove()
	{
		source.remove();
		index--;
	}


	public void set(E e)
	{
		source.set(e);
		
		if (!predicate.apply(e))
		{
			index--;
		}
	}


	public void add(E e)
	{
		source.add(e);
		
		if (predicate.apply(e))
		{
			index++;
		}
	}
	











}
