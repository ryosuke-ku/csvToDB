public class Actioninvocation {




























































































    public Action<S> getAction() {
        return action;
    }

















    public ActionArgumentValue<S>[] getOutput() {
        return output.values().toArray(new ActionArgumentValue[output.size()]);
    }

    public ActionArgumentValue<S> getOutput(String argumentName) {
        return getOutput(getOutputArgument(argumentName));
    }





    public ActionArgumentValue<S> getOutput(ActionArgument<S> argument) {
        return output.get(argument.getName());
    }

    public void setInput(String argumentName, Object value) throws InvalidValueException {
        setInput(new ActionArgumentValue(getInputArgument(argumentName), value));
    }

    public void setInput(ActionArgumentValue<S> value) {
        input.put(value.getArgument().getName(), value);
    }

    public void setInput(ActionArgumentValue<S>[] input) {
        if (input == null) return;
        for (ActionArgumentValue<S> argumentValue : input) {
            this.input.put(argumentValue.getArgument().getName(), argumentValue);
        }
    }































    public ActionException getFailure() {
        return failure;
    }













    public String toString() {
        return "(" + getClass().getSimpleName() + ") " + getAction();
    }
}
