package Models;

import java.io.File;

public class FindData {
    private File file;
    private Integer line;


    public FindData(File file, Integer line) {
        super();
        this.file = file;
        this.line = line;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }


}
