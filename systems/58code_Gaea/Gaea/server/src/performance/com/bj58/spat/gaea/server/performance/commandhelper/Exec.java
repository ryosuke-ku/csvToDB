public class Exec {









































	public Command createCommand(String commandStr) {
		if (commandStr != null && !commandStr.equalsIgnoreCase("")) {
			String[] args = commandStr.split("\\|");
			if (args[0].trim().equalsIgnoreCase("exec")) {
				String execStr = commandStr.replaceFirst("exec\\|", "");
				if (execStr.startsWith("netstat") || execStr.startsWith("top")) {
					Command entity = new Command();
					entity.setCommandType(CommandType.Exec);
					if(execStr.equalsIgnoreCase("top")){
						entity.setCommand("top -bn 1");
					} else if(execStr.equalsIgnoreCase("netstat")){
						entity.setCommand(execStr);
					}
					return entity;
				}
			}
		}
		return null;
	}


















































































































}
