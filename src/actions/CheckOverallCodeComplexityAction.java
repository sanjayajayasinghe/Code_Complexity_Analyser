package actions;

import Models.ScoreObject;
import coreFunctions.*;
import utilities.FileUtilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CheckOverallCodeComplexityAction {

    private File file;
    private Map<Integer, ScoreObject> scoreMap = new HashMap<>();

    public CheckOverallCodeComplexityAction(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getSizeComplexity() throws IOException {
        return new ComplexityDueToSize(this.file).calculateComplexity();
    }


    public int getInheritanceComplexity() throws IOException {
        return new InheritanceComplexityImpl().findInheritanceComplexityForFile(this.file);
    }


    public Map<Integer, ScoreObject> getScoreMap() throws IOException {



        ComplexityDueToControlStructures complexityDueToControlStructures = new ComplexityDueToControlStructures();
        final Map<Integer, Integer> controlStructures = complexityDueToControlStructures.calculateComplexityForControlStructures(file);

        ComplexityDueToSize complexityDueToSize = new ComplexityDueToSize(getFile());
        final Map<Integer, Integer> sizeComplexityMap = complexityDueToSize.getSizeComplexityMap();


        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
        final Map<Integer, Integer> inheritanceCompMap = inheritanceComplexity.getCreatedScoreMap(getFile());

        ComplexityByRecursion complexityByRecursion = new ComplexityDueToRecursion();
        Map<Integer, Boolean> recursionLinesMap = complexityByRecursion.getRecursionLines(getFile());

        initializeMap();

        for (Integer line : controlStructures.keySet()) {
            ScoreObject scoreObject = scoreMap.get(line);
            scoreObject.setCNC(controlStructures.get(line));
            scoreMap.put(line, scoreObject);
        }

        for (Integer line : sizeComplexityMap.keySet()) {
            ScoreObject scoreObject = scoreMap.get(line);
            scoreObject.setCS(sizeComplexityMap.get(line));
            scoreMap.put(line, scoreObject);
        }

        for (Integer line : inheritanceCompMap.keySet()) {
            ScoreObject scoreObject = scoreMap.get(line);
            scoreObject.setCI(inheritanceCompMap.get(line));
            scoreMap.put(line, scoreObject);
        }
        for (Integer line : scoreMap.keySet()) {
            ScoreObject scoreObject = scoreMap.get(line);
            scoreObject.setTW(scoreObject.getCI() + scoreObject.getCNC());
            scoreObject.setCPS(scoreObject.getCS() * scoreObject.getTW());
            scoreMap.put(line, scoreObject);
        }
        for (Integer line : recursionLinesMap.keySet()) {
            if (recursionLinesMap.get(line)) {
                ScoreObject scoreObject = scoreMap.get(line);
                scoreObject.setCR(scoreObject.getCPS() * 2);
                scoreMap.put(line, scoreObject);
            }
        }

        return scoreMap;
    }

    private void initializeMap() throws IOException {
        List<String> strings = FileUtilities.convertToLisOfStrings(getFile());
        int i = 1;
        for (String s : strings) {
            ScoreObject scoreObject = new ScoreObject();
            scoreObject.setLine(s);
            scoreMap.put(i, scoreObject);
            i++;
        }
    }
}
