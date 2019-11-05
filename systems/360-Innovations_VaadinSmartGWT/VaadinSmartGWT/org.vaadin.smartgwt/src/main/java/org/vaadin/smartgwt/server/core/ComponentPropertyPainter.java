public class Componentpropertypainter {




























	public Set<ComponentProperty> getComponentProperties() {
		return new HashSet<ComponentProperty>(properties);
	}













	public <T extends Component> ComponentArray<T> addComponentArray(String propertyName) {
		final ComponentArray<T> array = new ComponentArray<T>(parent, "$" + propertyName);
		properties.add(array);
		return array;
	}
























}
