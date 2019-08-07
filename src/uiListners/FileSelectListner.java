package uiListners;

import java.io.File;

public interface FileSelectListner {
	public void afterFileSelect(File file);
	public void fileSelectFail(Exception e);

}
