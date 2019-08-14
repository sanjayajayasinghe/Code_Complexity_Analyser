/**
 *
 */
package utilities;

import Models.FindData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gisilk
 *
 */
public class FileUtilities {
    static int lineNo = 0;


    public static boolean isDirectory(File file) {
        return (file != null && !file.getName().startsWith(".") && file.isDirectory());
    }

    public static List<String> convertToLisOfStrings(File file) throws IOException {
        return Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
    }

    public static String[] convertToArrayOfStrings(File file) throws IOException {
        List<String> list = convertToLisOfStrings(file);
        return list.toArray(new String[list.size()]);
    }
    
    public static String getFileContent(File file) throws IOException {
    	 return FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
    }

    public static List<File> getClassesOfProject(File projectRoot) {

        List<File> classFiles = new ArrayList<File>();
        File srcFile = projectRoot.listFiles(new SourceFileFilter())[0];
        File[] packageFiles = srcFile.listFiles(new PackageFilter());

        for (File f : packageFiles) {
            classFiles.addAll(Arrays.asList(f.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return !file.isDirectory() && file.getName().endsWith(".java");
                }
            })));
        }

        return classFiles;
    }


    public static class PackageFilter implements FileFilter {

        @Override
        public boolean accept(File f) {
            if ("src".equals(f.getParentFile().getName())) {
                if (f.isDirectory()) {
                    return true;
                }
            }
            return false;
        }

    }

    public static class SourceFileFilter implements FileFilter {
        @Override
        public boolean accept(File f) {
            if (f != null && f.getName().startsWith(".")) {
                if (f.isDirectory() && "src".equals(f.getName())) {
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
        StringBuilder code = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                code.append(line + "\n");
                System.out.println(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return code.toString();

    }

    public synchronized static void listAllFilesInFolder(final File folder, List<File> fileList) {

        for (final File fileEntry : folder.listFiles()) {

            if (fileEntry.isDirectory()) {
                listAllFilesInFolder(fileEntry, fileList);
            } else {
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

    public static void listLeafFiles(final File folder, List<File> fileList) {
        if (folder.isDirectory()) {
            listAllFilesInFolder(folder, fileList);
        } else {
            fileList.add(folder);
        }
    }

    public static List<FindData> findFilesByWords(String key, File folder) {
        List<File> foundFileList = new ArrayList<>();
        List<File> leafFileList = new ArrayList<>();
        List<FindData> findDataList = new ArrayList<>();
        listLeafFiles(folder, leafFileList);


//		foundFileList=leafFileList.stream().filter((file)->{
//			try {
//				return convertToLisOfStrings(file).contains(keyWords);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return false;
//			}
//		}).collect(Collectors.toList());

        leafFileList.forEach(file -> {

            try {
                lineNo = 1;
                convertToLisOfStrings(file).forEach(line -> {
                    System.out.println("c" + key);
                    Pattern pattern = Pattern.compile(key);
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {

                        findDataList.add(new FindData(file, lineNo));
                    }
                    lineNo++;
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });

        return findDataList;


    }


    public static void main(String args[]) throws IOException {
        List<FindData> fileList = new ArrayList<>();
        fileList = findFilesByWords("c", new File("C:\\Users\\sanjaya jayasinghe\\Desktop\\ditributedSystem\\it17012966\\javaFX\\src\\javaFX"));
        fileList.forEach(file -> System.out.println("file:" + file.getFile().getName()));

        //	System.out.println(convertToLisOfStrings(new File("C:\\Users\\sanjaya jayasinghe\\Desktop\\ditributedSystem\\it17012966\\javaFX\\src\\javaFX\\HelloWorld.java")));
    }

    public List<String> find(String keyWord) {

        return null;
    }

    public Boolean isValidExtention(File file, String... extentions) {


        for (String extention : extentions) {
            if (FilenameUtils.getExtension("/path/to/file/foo.txt").toString().equals(extention))
                return true;
        }

        return false;

    }

}