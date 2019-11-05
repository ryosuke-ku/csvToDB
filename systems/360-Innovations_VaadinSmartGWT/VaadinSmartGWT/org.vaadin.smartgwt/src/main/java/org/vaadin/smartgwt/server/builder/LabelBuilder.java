public class Labelbuilder {













	public static LabelBuilder buildLabel(String contents) {
		return new LabelBuilder(new Label(contents));
	}






	protected LabelBuilder me() {
		return this;
	}
}
