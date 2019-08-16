//package test.resources.coreFunctions;
//
//import coreFunctions.InheritanceComplexityImpl;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.io.File;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
//
//public class InheritanceComplexityImplTest {
//
//    public static final String JAVA_FILE_PATH = "C:\\Users\\Nishitha\\Desktop\\spm code complexity\\Code_Complexity_Analyser\\src\\test\\resources\\Dog.java";
//    public static final String CPLUS_FILE_PATH = "C:\\Users\\Nishitha\\Desktop\\spm code complexity\\Code_Complexity_Analyser\\src\\test\\resources\\Persons.cpp";
//
//    @Test
//    @Ignore
//    public void testFindInheritedClassesForJavaFile() {
//        File file = new File(JAVA_FILE_PATH);
//
//        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
//        Map<String, Integer> map = inheritanceComplexity.findInheritedClasses(file);
//        assertEquals(4, java.util.Optional.ofNullable(map.get("Files scanned ")));
//        assertEquals(4, java.util.Optional.ofNullable(map.get("Total number of classes with inheritance found ")));
//        assertEquals(8, java.util.Optional.ofNullable(map.get("Total file complexity of selected files ")));
//
//    }
//
//    @Test
//    public void testWordMatchesInheritanceForJavaFile() {
//        File file = new File(JAVA_FILE_PATH);
//        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
//        assertTrue(inheritanceComplexity.wordMatchesInheritance("extends", file));
//
//    }
//
//    @Test
//    public void testWordMatchesInheritanceForCPlusFile() {
//        File file = new File(CPLUS_FILE_PATH);
//        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
//        assertTrue(inheritanceComplexity.wordMatchesInheritance(":", file));
//    }
//
//
//    @Test
//    public void testWordMatchesInheritanceNegativeForCPlusFile() {
//        File file = new File(CPLUS_FILE_PATH);
//        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
//        assertFalse(inheritanceComplexity.wordMatchesInheritance("aaa", file));
//    }
//
//
//}
//
//
