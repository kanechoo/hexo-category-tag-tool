package site.konchoo.tool.components;

import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class CommonComponent {
    public List<File> initFileChooser(Label selectedFileInfoLabel) {
        // file chooser
        FileChooser fileChooser = new FileChooser();
        // file filter
        FileChooser.ExtensionFilter fileFilter =
                new FileChooser.ExtensionFilter("markdown files(.md)", "*.md");
        fileChooser.getExtensionFilters().add(fileFilter);
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        selectedFileInfoLabel.setText(" " + files.size() + " markdown files was selected");
        return files;
    }
}
