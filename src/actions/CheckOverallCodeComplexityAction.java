package actions;

import coreFunctions.ComplexityDueToControlStructures;
import coreFunctions.ComplexityDueToSize;
import coreFunctions.InheritanceComplexityImpl;

import java.io.File;
import java.io.IOException;

public class CheckOverallCodeComplexityAction {

    private File file;


    public CheckOverallCodeComplexityAction(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getControlStructureComplexity() throws IOException {
        return new ComplexityDueToControlStructures().calculateComplexityForControlStructuresForRecursiveConditions(this.file);
    }



    public int getSizeComplexity() throws IOException {
        return new ComplexityDueToSize(this.file).calculateComplexity();
    }


    public int getInheritanceComplexity() {
        return new InheritanceComplexityImpl().findInheritanceComplexityForFile(this.file);
    }



    //return (ctc+cnc+ci) - the total weight(TW)
    public int getTW() throws IOException {

        return getControlStructureComplexity() + getInheritanceComplexity();


    }

    //return (tw*cs)-the CPS value
    public int getCPS() throws IOException {
        return getTW() * getSizeComplexity();
    }

}
