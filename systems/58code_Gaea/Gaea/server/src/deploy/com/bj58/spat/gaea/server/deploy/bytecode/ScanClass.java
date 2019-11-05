public class Scanclass {

















































































	public static ContractInfo getContractInfo(String path, DynamicClassLoader classLoader) throws Exception {
		if(contractInfo == null) {
			synchronized (lockHelper) {
				if(contractInfo == null) {
					scan(path, classLoader);
				}
			}
		}
		
		return contractInfo;
	}
	







	public static List<ClassInfo> getContractClassInfos(String path, DynamicClassLoader classLoader) throws Exception {
		if(contractInfo == null) {
			synchronized (lockHelper) {
				if(contractInfo == null) {
					scan(path, classLoader);
				}
			}
		}
		
		return contractClassInfos;
	}
	







	public static List<ClassInfo> getBehaviorClassInfos(String path, DynamicClassLoader classLoader) throws Exception {
		if(contractInfo == null) {
			synchronized (lockHelper) {
				if(contractInfo == null) {
					scan(path, classLoader);
				}
			}
		}
		
		return behaviorClassInfos;
	}
	





































































	protected static ClassInfo contract(Class<?> cls, boolean ignoreAnnotation) {
		if(ignoreAnnotation) {
			ClassInfo ci = new ClassInfo();
			ci.setCls(cls);
			ci.setClassType(ClassInfo.ClassType.INTERFACE);

			Method[] methods = cls.getDeclaredMethods();
			List<ClassInfo.MethodInfo> methodInfos = new ArrayList<ClassInfo.MethodInfo>();
			
			 for(Method m : methods) {
				 if(Modifier.isPublic(m.getModifiers()) || Modifier.isProtected(m.getModifiers())) {
					 ClassInfo.MethodInfo mi = new ClassInfo.MethodInfo();
					 mi.setMethod(m);
					 methodInfos.add(mi);
				 }
			 }
			 ci.setMethodList(methodInfos);
			 
			 return ci;
		} else {
			return contract(cls);
		}
	}
	





	protected static ClassInfo contract(Class<?> cls) {
		ServiceContract contractAnn = cls.getAnnotation(ServiceContract.class);
		
		ClassInfo ci = new ClassInfo();
		ci.setCls(cls);
		ci.setClassType(ClassInfo.ClassType.INTERFACE);

		List<Class<?>> interfaceList = getInterfaces(cls);
		List<ClassInfo.MethodInfo> methodInfos = new ArrayList<ClassInfo.MethodInfo>();

		for(Class<?> interfaceCls : interfaceList) {
			Method[] methods = interfaceCls.getDeclaredMethods();
			if(contractAnn != null && contractAnn.defaultAll()) {
				 for(Method m : methods) {
					 if(Modifier.isPublic(m.getModifiers()) || Modifier.isProtected(m.getModifiers())) {
						 ClassInfo.MethodInfo mi = new ClassInfo.MethodInfo();
						 mi.setMethod(m);
						 methodInfos.add(mi);
					 }
				 }
			} else {
				 for(Method m : methods) {
					 if(Modifier.isPublic(m.getModifiers()) || Modifier.isProtected(m.getModifiers())) {
						 OperationContract oc = m.getAnnotation(OperationContract.class);
						 if(oc != null) {
							 ClassInfo.MethodInfo mi = new ClassInfo.MethodInfo();
							 mi.setMethod(m);
							 methodInfos.add(mi);
						 }
					 }
				 }
			}
		}
		
		ci.setMethodList(methodInfos);

		return ci;
		
	}
	


















































































































































}
