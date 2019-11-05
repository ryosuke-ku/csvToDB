public class Createmanager {








































	public IProxyFactory careteProxy(String serviceRootPath, DynamicClassLoader classLoader) throws Exception {
		
		String configPath = serviceRootPath + "/" + Constant.SERVICE_CONTRACT;
		File file = new File(configPath);
		ContractInfo serviceContract = null;
		
		if (file != null && file.exists()) {
			serviceContract = ContractConfig.loadContractInfo(configPath, classLoader);
		} else {
			serviceContract = ScanClass.getContractInfo(serviceRootPath + "/", classLoader);
		}
		
		
		long time = System.currentTimeMillis();
		List<ClassFile> localProxyList = new ProxyClassCreater().createProxy(classLoader, serviceContract, time);
		logger.info("proxy class buffer creater finish!!!");
		ClassFile cfProxyFactory = new ProxyFactoryCreater().createProxy(classLoader, serviceContract, time);
		logger.info("proxy factory buffer creater finish!!!");
		
		List<IProxyStub> localProxyAry = new ArrayList<IProxyStub>();
		for(ClassFile cf : localProxyList) {
			Class<?> cls = classLoader.findClass(cf.getClsName(), cf.getClsByte(), null);
			logger.info("dynamic load class:" + cls.getName());
			localProxyAry.add((IProxyStub)cls.newInstance());
		}
		
		Class<?> proxyFactoryCls = classLoader.findClass(cfProxyFactory.getClsName(), cfProxyFactory.getClsByte(), null);
		Constructor<?> constructor = proxyFactoryCls.getConstructor(List.class);
		IProxyFactory pfInstance = (IProxyFactory)constructor.newInstance(localProxyAry);
		logger.info("crate ProxyFactory instance!!!");
		return pfInstance;
	}
}
