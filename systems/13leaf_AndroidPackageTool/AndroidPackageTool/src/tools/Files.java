public class Files {




































	public static boolean deleteDir(File dir) {
		if (null == dir || !dir.exists())
			return false;
		if (!dir.isDirectory())
			throw new RuntimeException("\"" + dir.getAbsolutePath()
					+ "\" should be a directory!");
		File[] files = dir.listFiles();
		boolean re = false;
		if (null != files) {
			if (files.length == 0)
				return dir.delete();
			for (File f : files) {
				if (f.isDirectory())
					re |= deleteDir(f);
				else
					re |= f.delete();
			}
			re |= dir.delete();
		}
		return re;
	}
	

































































































































































































	public static String toRelativePath(File original, File target)
			throws IOException {
		String relativePath = "";
		String originalPath = original.getCanonicalPath();
		String targetPath = target.getCanonicalPath();
		String publicDir = getParentDir(originalPath, targetPath);
		
		if(null==publicDir) throw new IOException(String.format("original:%s\ntarget:%s have no public directory!", originalPath,targetPath));
		if(originalPath.equals(publicDir)) return "."+targetPath.substring(publicDir.length(), targetPath.length());
		relativePath=toParentRelative(originalPath,publicDir);
		return "."+relativePath+targetPath.substring(publicDir.length(),targetPath.length());
	}
	













	public static String getParentDir(String path1, String path2)
			throws IOException {
		if (path1.contains(".") || path2.contains(".")) {// not a canonicalPath
			path1 = new File(path1).getCanonicalPath();
			path2 = new File(path2).getCanonicalPath();
		}
		char[] originalChars = path1.toCharArray();
		char[] targetChars = path2.toCharArray();
		int i = -1;
		while (++i < originalChars.length && i < targetChars.length) {// to get the latest both public start
			if (originalChars[i] != targetChars[i]) {
				--i;// back
				break;
			}
		}
		if(i==-1) return null;
		else return path2.substring(0, i);
	}









































}
