public class Htmlpane {





















































	public void setIFrameResource(Resource resource) {
		setContentsResource(resource);
		if (!isDrawn()) {
			setContentsType(ContentsType.PAGE);
		} else {
			assert getContentsType() == ContentsType.PAGE : "This method cannot be called on a HTMLPane that has a contentsType other than PAGE";
		}
	}
}
