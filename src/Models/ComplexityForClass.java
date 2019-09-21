package Models;

public class ComplexityForClass {
    String className;
    Integer complexity;

    public ComplexityForClass(String className, Integer complexity) {
        this.className = className;
        this.complexity = complexity;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }
}
