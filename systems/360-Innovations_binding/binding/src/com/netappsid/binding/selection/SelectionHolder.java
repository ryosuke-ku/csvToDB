public class Selectionholder {




















	public SortedSet<Integer> getSelection()
	{
		return indexes != null ? new TreeSet<Integer>(indexes) : new TreeSet<Integer>();
	}








	public void setSelectedItem(Object selection)
	{
		if (selection == null)
		{
			setSelection();
		}
		else
		{
			setSelection(origin.indexOf(selection));
		}
	}






















































}
