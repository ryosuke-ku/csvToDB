public class Command {













































































































	public static Command create(String command) {
		Command entity = null;
		command = command.trim();
		for(ICommandHelper cc : helperList) {
			entity = cc.createCommand(command);
			if(entity != null) {
				break;
			}
		}
		if(entity == null) {
			entity = new Command();
			entity.setCommandType(CommandType.Illegal);
		}
		return entity;
	}
	






	public void exec(MessageEvent event) throws Exception {
		for(ICommandHelper cc : helperList) {
			cc.execCommand(this, event);
		}
	}







































	public CommandType getCommandType() {
		return commandType;
	}





	public String getCommand() {
		return command;
	}





	public List<ShowColumn> getColumnList() {
		return columnList;
	}





	public List<String> getGrep() {
		return grep;
	}









	public int getGroup() {
		return group;
	}
	
	public int getSecond() {
		return second;
	}





	public String getMethod() {
		return method;
	}




}
