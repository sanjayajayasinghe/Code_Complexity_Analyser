package actions;

import coreFunctions.ComplexityDueToControlStructures;
import coreFunctions.ComplexityDueToSize;
import coreFunctions.InheritanceComplexityImpl;

import java.io.File;
import java.io.IOException;

public class CheckOverallCodeComplexityAction {

    private File file;
    private int controlStructureComplexity;
    private int sizeComplexity;
    private int inheritanceComplexity;

    public CheckOverallCodeComplexityAction(File file) {
        this.file = file;
        this.controlStructureComplexity = 0;
        this.sizeComplexity = 0;
        this.inheritanceComplexity = 0;

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getControlStructureComplexity() {
        return controlStructureComplexity;
    }

    public void setControlStructureComplexity(int controlStructureComplexity) {
        this.controlStructureComplexity = controlStructureComplexity;
    }

    public int getSizeComplexity() {
        return sizeComplexity;
    }

    public void setSizeComplexity(int sizeComplexity) {
        this.sizeComplexity = sizeComplexity;
    }

    public int getInnhertanceComplexity() {
        return inheritanceComplexity;
    }

    public void setInnhertanceComplexity(int innheritanceComplexity) {
        this.inheritanceComplexity = innheritanceComplexity;
    }

    //return (ctc+cnc+ci) - the total weight(TW)
    public int getTW() throws IOException {
        ComplexityDueToSize complexityDueToSize = new ComplexityDueToSize(this.file);
        ComplexityDueToControlStructures complexityDueToControlStructures = new ComplexityDueToControlStructures();
        InheritanceComplexityImpl inheritanceComplexityimpl = new InheritanceComplexityImpl();


        controlStructureComplexity = complexityDueToControlStructures.calculateComplexityForControlStructuresForRecursiveConditions(this.file);
        sizeComplexity = complexityDueToSize.calculateComplexity();
        inheritanceComplexity = inheritanceComplexityimpl.findInheritanceComplexityForFile(this.file);

        return controlStructureComplexity + inheritanceComplexity;


    }

    //return (tw*cs)-the CPS value
    public int getCPS() throws IOException {
        return getTW() * getSizeComplexity();
    }

}
