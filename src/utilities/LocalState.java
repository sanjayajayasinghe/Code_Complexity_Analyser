package utilities;

import java.io.File;

public class LocalState {
	// private instance, so that it can be
	// accessed by only by getInstance() method
	private static LocalState instance;
	private File LastProject;
	
	private LocalState() {
		// private constructor
	}
	
	
	

	public File getLastProject() {
		return LastProject;
	}




	public void setLastProject(File lastProject) {
		LastProject = lastProject;
	}




	public static LocalState getInstance() {
		if (instance == null) {
			// synchronized block to remove overhead
			synchronized (LocalState.class) {
				if (instance == null) {
					// if instance is null, initialize
					instance = new LocalState();
				}

			}
		}
		return instance;
	}
}
