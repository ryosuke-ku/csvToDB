public class Time {
















































	public Command createCommand(String commandStr) {
		if(commandStr != null && !commandStr.equalsIgnoreCase("")) {
			String[] args = commandStr.split("\\|");
			if(args[0].trim().equalsIgnoreCase("time")) {
				List<String> grepList = new ArrayList<String>();
				List<ShowColumn> scList = new ArrayList<ShowColumn>();
				
				Command entity = new Command();
				scList.add(ShowColumn.All);
				entity.setCommandType(CommandType.Time);
				
				for(int i=1; i<args.length; i++) {
					if(args[i].trim().startsWith("grep")) {
						grepList.add(args[i].trim().replaceFirst("grep ", "").trim());
					} else if(args[i].trim().startsWith("group")) {
						entity.setGroup(Integer.parseInt(args[i].trim().replaceFirst("group ", "").trim()));
					} else if(args[i].trim().startsWith("column")) {
						scList.clear();
						String cs = args[i].trim().replaceFirst("column -", "");
						if(cs.indexOf("a") >= 0) {
							if(!scList.contains(ShowColumn.All)) {
								scList.add(ShowColumn.All);
							}
						} else {
							String[] csAry = cs.split("");
							for(String item : csAry) {
								if(item.equalsIgnoreCase("t")) {
									if(!scList.contains(ShowColumn.Time)) {
										scList.add(ShowColumn.Time);
									}
								} else if(item.equalsIgnoreCase("k")) {
									if(!scList.contains(ShowColumn.Key)) {
										scList.add(ShowColumn.Key);
									}
								} else if(item.equalsIgnoreCase("d")) {
									if(!scList.contains(ShowColumn.Description)) {
										scList.add(ShowColumn.Description);
									}
								}
							}
						}
					}
				}
				entity.setGrep(grepList);
				entity.setColumnList(scList);
				return entity;
			}
		}
		return null;
	}
	





































































































































}
