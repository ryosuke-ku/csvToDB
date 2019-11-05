public class Defaultmapbeanchangehandler {


















	public void propertyChange(PropertyChangeEvent evt)
	{
		if (evt.getNewValue() instanceof Map)
		{
			Map newMap = (Map) evt.getNewValue();

			for (Entry<String, ValueModel> entry : dynamicPresentationModel.getNamesToValueModels().entrySet())
			{
				String key = entry.getKey();
				ValueModel valueModel = entry.getValue();

				if (newMap.containsKey(key))
				{
					valueModel.setValue(newMap.get(key));
				}
				else
				{
					newMap.put(key, valueModel.getValue());
				}
			}
		}
	}
}
