public class Iptable {






































	public static void init() {
		String allowIP = Global.getSingleton().getServiceConfig().getString("gaea.iptable.allow.iplist");
		String forbidIP = Global.getSingleton().getServiceConfig().getString("gaea.iptable.forbid.iplist");
		allowIP = allowIP.replaceAll("\\.", "\\\\.")
				   .replaceAll(",", "|")
				   .replaceAll("\\*", "\\.\\*");
		
		forbidIP = forbidIP.replaceAll("\\.", "\\\\.")
		  		   .replaceAll(",", "|")
		  		   .replaceAll("\\*", "\\.\\*");
		
		if(allowIP != null && !allowIP.equalsIgnoreCase("")) {
			allowPattern = Pattern.compile(allowIP);
		} else {
			allowPattern = null; //for unit test
		}
		if(forbidIP != null && !forbidIP.equalsIgnoreCase("")) {
			forbidPattern = Pattern.compile(forbidIP);
		} else {
			forbidPattern = null; //for unit test
		}
	}
	





	public static boolean isAllow(String ip) {
		if(ip != null && !ip.equalsIgnoreCase("")) {
			boolean allowMatch = true;
			boolean forbidMatch = false;
			
			if(allowPattern != null) {
				allowMatch = allowPattern.matcher(ip).find();
			}
			if(forbidPattern != null) {
				forbidMatch = forbidPattern.matcher(ip).find();
			}
			
			return (allowMatch && !forbidMatch); 
		}

		return false;
	}
	










}
