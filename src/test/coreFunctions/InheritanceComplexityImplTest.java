package test.coreFunctions;

import coreFunctions.InheritanceComplexityImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.*;
import static utilities.TestFileConstants.*;


public class InheritanceComplexityImplTest {

    @Test
    public void testFindInheritedClassesForJavaFile() {

        File file = new File(RELATIVE_DIR_PATH.concat(DOG_JAVA_FILE_PATH));

        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
        Map<String, Integer> map = inheritanceComplexity.findInheritedClassesForFileList(file);
        assertEquals(Integer.valueOf(6), map.get("Files scanned "));
        assertEquals(Integer.valueOf(4), map.get("Total number of classes with inheritance found "));
        assertEquals(Integer.valueOf(15), map.get("Total file complexity of selected files "));

    }


}


