package test.coreFunctions;

import coreFunctions.InheritanceComplexityImpl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InheritanceComplexityImplTest {

    @Test
    public void testThis() {
        File file = new File("C:\\Users\\Nishitha\\Desktop\\spm code complexity\\Code_Complexity_Analyser\\src\\test\\resources\\Dog.java");

        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
        Map<String, Integer> map = inheritanceComplexity.findInheritedClasses(file);
        assertEquals(4, map.get("Files_scanned"));
        assertEquals(4, map.get("Inheritance_classes"));

    }


}


