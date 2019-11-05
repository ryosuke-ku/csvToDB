public class Dynamicformbuilder {













	public static DynamicFormBuilder buildDynamicForm() {
		return new DynamicFormBuilder(new DynamicForm());
	}






	protected DynamicFormBuilder me() {
		return this;
	}




	public DynamicFormBuilder setNumCols(int numCols) {
		instance().setNumCols(numCols);
		return me();
	}




	public DynamicFormBuilder setAutoFocus(Boolean autoFocus) {
		instance().setAutoFocus(autoFocus);
		return me();
	}




	public DynamicFormBuilder setFields(FormItem... fields) {
		instance().setFields(fields);
		return me();
	}
}
