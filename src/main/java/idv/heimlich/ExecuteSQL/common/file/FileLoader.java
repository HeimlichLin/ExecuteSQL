package idv.heimlich.ExecuteSQL.common.file;

import java.io.File;

public class FileLoader {
	
	public static File getResourcesFile(Class<?> pClass, final String file) {
		final ClassLoader classLoader = pClass.getClassLoader();
		return new File(classLoader.getResource(file).getFile());		
	}

}
