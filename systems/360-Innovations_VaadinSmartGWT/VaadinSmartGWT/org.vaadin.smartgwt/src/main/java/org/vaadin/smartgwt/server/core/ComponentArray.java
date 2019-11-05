public class Componentarray {
















	public Object getTagName() {
		return tagName;
	}

	public T[] get() {
		return components;
	}

	public void set(T[] components) {
		if (this.components != null) {
			for (T component : this.components) {
				component.setParent(null);
			}
		}

		this.components = components;

		if (this.components != null) {
			for (T component : this.components) {
				component.setParent(parent);
			}
		}
	}


	public void paintContent(PaintTarget target) throws PaintException {
		target.startTag(tagName);
		target.addAttribute("type", "Array");

		if (components != null) {
			for (Paintable paintable : components) {
				paintable.paint(target);
			}
		}

		target.endTag(tagName);
	}
}
