public class Main {





























































	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			throw new IllegalArgumentException("usage: -Dgaea.service.name=<service-name> [<other-gaea-config>]");
		}

		String userDir = System.getProperty("user.dir");
		String rootPath = userDir + "/../";
		String serviceName = null;
		Map<String, String> argsMap = new HashMap<String, String>();
		Global.getSingleton().setRootPath(rootPath);
		
		for(int i=0; i<args.length; i++) {
			if(args[i].startsWith("-D")) {
				String[] aryArg = args[i].split("=");
				if(aryArg.length == 2) {
					if(aryArg[0].equalsIgnoreCase("-Dgaea.service.name")) {
						serviceName = aryArg[1];
					}
					argsMap.put(aryArg[0].replaceFirst("-D", ""), aryArg[1]);
				}
			}
		}
		
		if(serviceName == null){
			throw new Exception("no service name please set it");
		}
		
		String serviceFolderPath = rootPath + "service/deploy/" + serviceName;
		String gaeaConfigDefaultPath = rootPath + "conf/gaea_config.xml";
		String gaeaConfigPath = serviceFolderPath + "/gaea_config.xml";
		String log4jConfigDefaultPath = rootPath + "conf/gaea_log4j.xml";
		String log4jConfigPath = serviceFolderPath + "/gaea_log4j.xml";
		
		// load log4j
		loadLog4jConfig(log4jConfigPath, log4jConfigDefaultPath);
		logger = LogFactory.getLogger(Main.class);
		
		logger.info("+++++++++++++++++++++ staring +++++++++++++++++++++\n");
		
		logger.info("user.dir: " + userDir);
		logger.info("rootPath: " + rootPath);
		logger.info("service gaea_config.xml: " + gaeaConfigPath);
		logger.info("default gaea_config.xml: " + gaeaConfigDefaultPath);
		logger.info("service gaea_log4j.xml: " + log4jConfigPath);
		logger.info("default gaea_log4j.xml: " + log4jConfigDefaultPath);
		
		// load service config
		logger.info("load service config...");
		ServiceConfig sc = loadServiceConfig(gaeaConfigDefaultPath, gaeaConfigPath);
		Set<String> keySet = argsMap.keySet();
		for(String key : keySet) {
			logger.info(key + ": " + argsMap.get(key));
			sc.set(key, argsMap.get(key));
		}
		if(sc.getString("gaea.service.name") == null || sc.getString("gaea.service.name").equalsIgnoreCase("")) {
			logger.info("gaea.service.name:" + serviceName);
			sc.set("gaea.service.name", serviceName);
		}
		Global.getSingleton().setServiceConfig(sc);

		
		// init class loader
		logger.info("-----------------loading global jars------------------");
		DynamicClassLoader classLoader = new DynamicClassLoader();
		classLoader.addFolder(
				rootPath + "service/deploy/" + sc.getString("gaea.service.name") + "/",
				rootPath + "service/lib/",
				rootPath + "lib"
				);
		
		GlobalClassLoader.addSystemClassPathFolder(
				rootPath + "service/deploy/" + sc.getString("gaea.service.name") + "/",
				rootPath + "service/lib/",
				rootPath + "lib"
				);
		logger.info("-------------------------end-------------------------\n");

		if(new File(serviceFolderPath).isDirectory() || !serviceName.equalsIgnoreCase("error_service_name_is_null")) {
			// load proxy factory
			logger.info("--------------------loading proxys-------------------");
			IProxyFactory proxyFactory = ProxyFactoryLoader.loadProxyFactory(classLoader);
			Global.getSingleton().setProxyFactory(proxyFactory);
			logger.info("-------------------------end-------------------------\n");
			
			// load init beans
			logger.info("-----------------loading init beans------------------");
			loadInitBeans(classLoader, sc);
			logger.info("-------------------------end-------------------------\n");
		}
		
		// load global request-filters
		logger.info("-----------loading global request filters------------");
		List<IFilter> requestFilters = loadFilters(classLoader, sc, "gaea.filter.global.request");
		for(IFilter filter : requestFilters) {
			Global.getSingleton().addGlobalRequestFilter(filter);
		}
		logger.info("-------------------------end-------------------------\n");
		
		// load global response-filters
		logger.info("-----------loading global response filters-----------");
		List<IFilter> responseFilters = loadFilters(classLoader, sc, "gaea.filter.global.response");
		for(IFilter filter : responseFilters) {
			Global.getSingleton().addGlobalResponseFilter(filter);
		}
		logger.info("-------------------------end-------------------------\n");
		
		// load connection filters
		logger.info("-----------loading connection filters-----------");
		List<IFilter> connFilters = loadFilters(classLoader, sc, "gaea.filter.connection");
		for(IFilter filter : connFilters) {
			Global.getSingleton().addConnectionFilter(filter);
		}
		logger.info("-------------------------end-------------------------\n");
		
		// load secureKey 当gaea.secure不为true时不启动权限认证
		logger.info("------------------load secureKey start---------------------");
		if(sc.getString("gaea.secure") != null && "true".equalsIgnoreCase(sc.getString("gaea.secure"))) {
			logger.info("gaea.secure:" + sc.getString("gaea.secure"));
			loadSecureKey(sc,serviceFolderPath);
		}
		logger.info("------------------load secureKey end----------------------\n");
		
		//注册信号 linux下支持USR2
		logger.info("------------------signal registr start---------------------");
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName != null && osName.indexOf("window") == -1){
			OperateSignal operateSignalHandler = new OperateSignal();
			Signal sig = new Signal("USR2");
			Signal.handle(sig, operateSignalHandler);
		}
		logger.info("------------------signal registr success----------------------\n");
		
		// load servers
		logger.info("------------------ starting servers -----------------");
		loadServers(classLoader, sc);
		logger.info("-------------------------end-------------------------\n");
		
		// add current service file to monitor
		if(sc.getBoolean("gaea.hotdeploy")) {
			logger.info("------------------init file monitor-----------------");
			addFileMonitor(rootPath, sc.getString("gaea.service.name"));
			logger.info("-------------------------end-------------------------\n");
		}
		
		try {
			registerExcetEven();
		} catch (Exception e) {
			logger.error("registerExcetEven error", e);
			System.exit(0);
		}
		
		logger.info("+++++++++++++++++++++ server start success!!! +++++++++++++++++++++\n");
		while (true) {
			Thread.sleep(1 * 60 * 60 * 1000);
		}
	}
	


































































































































































}
