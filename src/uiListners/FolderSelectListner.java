package uiListners;

import java.io.File;

public interface FolderSelectListner {
    public void afterFileSelect(File file);

    public void fileSelectFail(Exception e);

}
