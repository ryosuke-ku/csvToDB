public class Swingidentitypropertychangesupport {
















	public void fireIdentityPropertyChange(final PropertyChangeEvent evt)
	{
		if (EventQueue.isDispatchThread())
		{
			super.fireIdentityPropertyChange(evt);
		}
		else
		{
			try
			{
				EventQueue.invokeAndWait(new Runnable()
					{
						@Override
						public void run()
						{
							fireIdentityPropertyChange(evt);
						}
					});
			}
			catch (Exception e)
			{
				LOGGER.error(e.getMessage(), e);
			}
		}
	}


	public void firePropertyChange(final PropertyChangeEvent evt)
	{
		if (EventQueue.isDispatchThread())
		{
			super.firePropertyChange(evt);
		}
		else
		{
			try
			{
				EventQueue.invokeAndWait(new Runnable()
					{
						@Override
						public void run()
						{
							firePropertyChange(evt);
						}
					});
			}
			catch (Exception e)
			{
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
}
