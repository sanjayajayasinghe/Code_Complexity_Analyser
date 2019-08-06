/**
 * 
 */
package utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gisilk
 *
 */
public class FileUtils {

		
	public static boolean isDirectory(File file) {		
		return (file != null && !file.getName().startsWith(".") && file.isDirectory());		
	}
	
	public static List<String> convertToLisOfStrings(File file) throws IOException{		
		return Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);		
	}
	
	public static String[] convertToArrayOfStrings(File file) throws IOException {
		List<String> list = convertToLisOfStrings(file);
		return list.toArray(new String[list.size()]);
	}
	
	public static List<File> getClassesOfProject(File projectRoot){
		
		List<File> classFiles = new ArrayList<File>();
		File srcFile = projectRoot.listFiles(new SourceFileFilter())[0];
		File[] packageFiles = srcFile.listFiles(new PackageFilter());
		
		for(File f : packageFiles) {
			classFiles.addAll(Arrays.asList(f.listFiles(new FileFilter() {				
				@Override
				public boolean accept(File file) {
					return !file.isDirectory() && file.getName().endsWith(".java");
				}
			})));
		}
		
		return classFiles;
	}
	

	
	public static class PackageFilter implements FileFilter{

		@Override
		public boolean accept(File f) {
			if("src".equals(f.getParentFile().getName())) {
				if(f.isDirectory()) {
					return true;
				}
			}
			return false;
		}
		
	}
	
	public static class SourceFileFilter implements FileFilter{
		@Override
		public boolean accept(File f) {
			if(f != null && f.getName().startsWith(".")) {
				if(f.isDirectory() && "src".equals(f.getName())) {
					return true;
				}
			}			
			return false;
		}
	
	}
}