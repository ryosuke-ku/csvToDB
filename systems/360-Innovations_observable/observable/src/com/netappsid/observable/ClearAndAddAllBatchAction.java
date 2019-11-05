public class Clearandaddallbatchaction {













	public void execute(Collection<T> collection)
	{
		collection.clear();
		
		if (this.newCollection != null)
		{
			collection.addAll(this.newCollection);
		}
	}
}
