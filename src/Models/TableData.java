package Models;

public class TableData {

    private String ClassName;
    int lineNo;
    private int CS = 0;
    private int CNC = 0;
    private int CI = 0 ;
    private int TW = 0;
    private int CPS = 0;
    private int CR = 0;

    public TableData(String className, int lineNo, int CS, int CNC, int CI, int TW, int CPS, int CR) {
        ClassName = className;
        this.lineNo = lineNo;
        this.CS = CS;
        this.CNC = CNC;
        this.CI = CI;
        this.TW = TW;
        this.CPS = CPS;
        this.CR = CR;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public int getCS() {
        return CS;
    }

    public void setCS(int CS) {
        this.CS = CS;
    }

    public int getCNC() {
        return CNC;
    }

    public void setCNC(int CNC) {
        this.CNC = CNC;
    }

    public int getCI() {
        return CI;
    }

    public void setCI(int CI) {
        this.CI = CI;
    }

    public int getTW() {
        return TW;
    }

    public void setTW(int TW) {
        this.TW = TW;
    }

    public int getCPS() {
        return CPS;
    }

    public void setCPS(int CPS) {
        this.CPS = CPS;
    }

    public int getCR() {
        return CR;
    }

    public void setCR(int CR) {
        this.CR = CR;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }
}
