package utilities;


import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TreeUtill {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void makeBranch(String childTitle, TreeItem<String> parent) {
        TreeItem<String> child = new TreeItem<String>(childTitle);
        parent.getChildren().add(child);
        parent.setExpanded(false);


    }

    public static String getItemFilepath(TreeItem<String> leaf, TreeItem<String> root, String path) throws NullPointerException {


        if (root.equals(leaf)) {
            return path;
        } else {
            return getItemFilepath(leaf.getParent(), root, path) + "\\" + leaf.getValue();
        }

    }

    public static List<File> getSelectedFilesList(ObservableList<TreeItem<String>> itemList, TreeItem<String> root, String path) {
        //TreeUtill.getItemFilepath(selectedItem, fileTreeView.getRoot(),
        //		new String(LocalState.getInstance().getLastProject().getPath()));
        List<String> fileNameList = new ArrayList<>();
        itemList.forEach(item -> {
            fileNameList.add(getItemFilepath(item, root, path));
            LOGGER.log(Level.INFO, "items:" + item.getValue());
        });
        List<File> fileList = fileNameList.stream().map((pathname) -> new File(pathname)).collect(Collectors.toList());
        return fileList;
    }

    public static void listFilesForTreeView(final File folder, TreeItem<String> root) {

        File[] files = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().startsWith(".")) {
                    return false;
                }

                if (pathname.getName().equalsIgnoreCase("bin")) {
                    return false;
                }

                if (pathname.getName().endsWith(".class")) {
                    return false;
                }

                return true;
            }
        });

        for (final File fileEntry : files) {

            if (fileEntry.isDirectory()) {
                TreeItem<String> children = new TreeItem<String>(fileEntry.getName());
                listFilesForTreeView(fileEntry, children);
                root.getChildren().add(children);
            } else {
                TreeUtill.makeBranch(fileEntry.getName(), root);
            }
        }

    }


}
