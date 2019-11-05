public class Count {















































	public Command createCommand(String commandStr) {
		if(commandStr != null && !commandStr.equalsIgnoreCase("")) {
			String[] args = commandStr.split("\\|");
			if(args[0].trim().equalsIgnoreCase("count")) {
				Command entity = new Command();
				entity.setCommandType(CommandType.Count);
				entity.setSecond(1);
				entity.setMethod("#all#");
				if(args.length > 1) {
					for(int i=1; i<args.length; i++) {
						if(args[i].trim().startsWith("second")) {
							entity.setSecond(Integer.parseInt(args[i].trim().replaceFirst("second ", "").trim()));
						} else if(args[i].trim().startsWith("method")) {
							entity.setMethod(args[i].trim().replaceFirst("method ", "").trim());
						}
					}
				}
				return entity;
			}
		}
		return null;
	}



















































































}
