public class Borderlayoutbuilder {













	public static BorderLayoutBuilder buildBorderLayout() {
		return new BorderLayoutBuilder(new BorderLayout());
	}








	public BorderLayoutBuilder setNorthMember(Canvas canvas) {
		instance().setNorthMember(canvas);
		return me();
	}




	public BorderLayoutBuilder setSouthMember(Canvas canvas) {
		instance().setSouthMember(canvas);
		return me();
	}




	public BorderLayoutBuilder setWestMember(Canvas canvas) {
		instance().setWestMember(canvas);
		return me();
	}




	public BorderLayoutBuilder setEastMember(Canvas canvas) {
		instance().setEastMember(canvas);
		return me();
	}




	public BorderLayoutBuilder setCenterMember(Canvas canvas) {
		instance().setCenterMember(canvas);
		return me();
	}


	protected BorderLayoutBuilder me() {
		return this;
	}
}
