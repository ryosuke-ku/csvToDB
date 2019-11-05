public class Componentlist {
























	public void add(E e) {
		e.setParent(parent);
		components.add(e);
		instructions.add(new Instruction<E>("add", e));
	}

	public void add(int index, E element) {
		element.setParent(parent);
		components.add(index, element);
		instructions.add(new Instruction<E>("add", index, element));
	}








	public void remove(E e) {
		final int index = components.indexOf(e);

		if (components.remove(e)) {
			instructions.add(new Instruction<E>("remove", index, e));
		}
	}






	public void clientRemoved(E element) {
		components.remove(element);
	}

	public E get(int index) {
		return components.get(index);
	}





















	public boolean contains(Object o) {
		return components.contains(o);
	}






	public void paintContent(PaintTarget target) throws PaintException {
		target.startTag(tagName);
		target.addAttribute("type", "List");

		for (Component component : components) {
			component.paint(target);
		}

		if (target.isFullRepaint()) {
			for (E component : components) {
				new Instruction<E>("add", component).paintContent(target);
			}
		} else {
			for (Instruction<E> instruction : instructions) {
				instruction.paintContent(target);
			}
		}

		instructions.clear();
		target.endTag(tagName);
	}


	public Iterator<E> iterator() {
		return new ComponentIterator(components.iterator());
	}




















		public void remove() {
			source.remove();
			instructions.add(new Instruction<E>("remove", next));
		}
	}


















		public void paintContent(PaintTarget target) throws PaintException {
			target.startTag(name);

			if (index != null) {
				target.addAttribute("index", index);
			}

			if (element != null) {
				target.addAttribute("element", element);
				detachRemovedElement();
			}

			target.endTag(name);
		}







}
