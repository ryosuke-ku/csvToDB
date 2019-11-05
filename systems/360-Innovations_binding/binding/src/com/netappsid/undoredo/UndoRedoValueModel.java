public class Undoredovaluemodel {













































	public void setValue(Object value)
	{
		T valueModel = getValueModel();

		Object valueOld;
		if (valueModel instanceof UndoSupport)
		{
			valueOld = ((UndoSupport) valueModel).getUndoableValue(value);
		}
		else
		{
			valueOld = valueModel.getValue();
		}

		UndoRedoValue undoRedoValue = new UndoRedoValue(valueOld, value);
		UndoRedoValueModelOperation undoRedoValueModelOperation = new UndoRedoValueModelOperation(this, undoRedoValue);
		getUndoRedoManager().push(undoRedoValueModelOperation);
		getUndoRedoManager().beginTransaction();
		getValueModel().setValue(value);
		getUndoRedoManager().endTransaction();
	}



















	public void undo(UndoRedoValue undoRedoValue)
	{
		getValueModel().setValue(undoRedoValue.getOldValue());
	}




























































}
