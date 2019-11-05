public class Simplepropertyadapter {

























	public String getPropertyName()
	{
		return propertyName;
	}

	public PropertyDescriptor getPropertyDescriptor()
	{
		return getPropertyDescriptor(beanAdapter.getBean());
	}


	public Object getValue()
	{
		final PropertyDescriptor propertyDescriptor = getPropertyDescriptor();
		Object bean = beanAdapter.getBean();

		if (propertyDescriptor != null && bean != null)
		{
			return BeanUtils.getValue(bean, propertyDescriptor);
		}
		else
		{
			return null;
		}
	}


	public void setValue(Object newValue)
	{
		PropertyDescriptor propertyDescriptor = getPropertyDescriptor();

		if (beanAdapter.getBean() == null)
		{
			try
			{
				Object newInstance = beanAdapter.getBeanClass().newInstance();
				beanAdapter.setBean(newInstance);
				propertyDescriptor = getPropertyDescriptor();
			}
			catch (Exception e)
			{
				LOGGER.error(e, e);
			}
		}

		if (propertyDescriptor != null)
		{
			try
			{
				Object effectiveNewValue = newValue;

				if (effectiveNewValue == null && propertyDescriptor.getPropertyType().isPrimitive())
				{
					effectiveNewValue = Defaults.defaultValue(propertyDescriptor.getPropertyType());
				}

				BeanUtils.setValue(beanAdapter.getBean(), propertyDescriptor, effectiveNewValue);
			}
			catch (PropertyVetoException e)
			{
				LOGGER.trace(e.getMessage(), e);
			}
		}
	}
































































	private PropertyDescriptor getPropertyDescriptor(Object bean)
	{
		try
		{
			Class<? extends Object> beanClass = bean != null ? bean.getClass() : beanAdapter.getBeanClass();
			return BeanUtils.getPropertyDescriptor(beanClass, propertyName);
		}
		catch (Exception e)
		{
			LOGGER.trace(e.getMessage(), e);
		}

		return null;
	}
}
