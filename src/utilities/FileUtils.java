/**
 * 
 */
package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
}