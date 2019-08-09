/**
 * 
 */
package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;

import utilities.TreeUtill;
import javafx.scene.control.TreeItem;

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
	
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	    	//System.out.println(fileEntry.getParent());
            System.out.println(fileEntry.getPath());
	    	if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	
	        }
	    }
	}
	
	
	
	
	
	public static String filesToString(File file) {
		StringBuilder code=new StringBuilder("");
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line;
	        while ((line = reader.readLine()) != null) {
	           code.append(line+"\n"); 
	       System.out.println(line);
	        }
	        
	        
	    	

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		return code.toString();
		
	}
	
	public synchronized static void  listAllFilesInFolder(final File folder,List<File> fileList) {
		   
		for (final File fileEntry : folder.listFiles()) {
           
	    	if (fileEntry.isDirectory()) {
	    		listAllFilesInFolder(fileEntry, fileList);
	        }else {
	        	fileList.add(fileEntry);
	        }
	    	
	    }
		
		
		
//		try (Stream<Path> walk = Files.walk(Paths.get("C:\\projects"))) {
//
//			List<File> result = walk.filter(Files::isRegularFile).map((path)->new File(path.toString()))
//					.collect(Collectors.toList());
//
//			result.forEach(System.out::println);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	    
	}
	
	public static void listLeafFiles(final File folder,List<File> fileList) {
		if (folder.isDirectory()) {
			listAllFilesInFolder(folder, fileList);
        }else {
        	fileList.add(folder);
        }
	}
	
	
	public static void main(String args[]) {
		List<File> fileList=new ArrayList<>();
		listLeafFiles(new File("C:\\Users\\sanjaya jayasinghe\\Desktop\\ditributedSystem\\it17012966\\javaFX\\src\\javaFX"), fileList);
		fileList.forEach(file->System.out.println("file:"+file.getName()));
		
	}
	
	public Boolean isValidExtention(File file,String ...extentions) {

		
		for(String extention:extentions) {
		if(FilenameUtils.getExtension("/path/to/file/foo.txt").toString().equals(extention))
			return true;
		}
		
		return false;
		
	}
	
}