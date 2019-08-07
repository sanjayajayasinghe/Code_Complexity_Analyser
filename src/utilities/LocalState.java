package utilities;

import java.io.File;

import javafx.scene.control.TreeItem;

public class LocalState {
	// private instance, so that it can be
	// accessed by only by getInstance() method
	private static LocalState instance;
	private File LastProject;
	private File CurrentOpenfile;
	private TreeItem<String> currentRoot;
	
	public TreeItem<String> getCurrentRoot() {
		return currentRoot;
	}

	public void setCurrentRoot(TreeItem<String> currentRoot) {
		this.currentRoot = currentRoot;
	}

	private LocalState() {
		// private constructor
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
	

	public File getLastProject() {
		return LastProject;
	}




	public void setLastProject(File lastProject) {
		LastProject = lastProject;
	}




	




	public File getCurrentOpenfile() {
		return CurrentOpenfile;
	}




	public void setCurrentOpenfile(File currentOpenfile) {
		CurrentOpenfile = currentOpenfile;
	}
	
	
}
