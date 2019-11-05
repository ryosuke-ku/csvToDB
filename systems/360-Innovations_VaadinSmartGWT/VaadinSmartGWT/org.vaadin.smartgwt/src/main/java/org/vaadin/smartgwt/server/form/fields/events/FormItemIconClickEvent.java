public class Formitemiconclickevent {



































	public static Event.Type<FormItemClickHandler> getType()
	{
		return TYPE == null ? TYPE = new Event.Type<FormItemClickHandler>() : TYPE;
	}









	public Event.Type<FormItemClickHandler> getAssociatedType()
	{
		return getType();
	}






	public DynamicForm getForm()
	{
		return form;
	}






	public FormItem getItem()
	{
		return item;
	}






	public FormItemIcon getIcon()
	{
		return icon;
	}


	protected void dispatch(FormItemClickHandler handler)
	{
		handler.onFormItemClick(this);
	}
}
