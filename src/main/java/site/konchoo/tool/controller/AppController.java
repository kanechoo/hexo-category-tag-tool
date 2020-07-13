package site.konchoo.tool.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import site.konchoo.tool.components.CommonComponent;
import site.konchoo.tool.file.MarkdownFileProcessor;
import site.konchoo.tool.parameter.ComponentParameter;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AppController {
    @FXML
    private Button selectFileButton;
    @FXML
    private TextField categoriesTextField;
    @FXML
    private TextField tagsTextField;
    @FXML
    private Button submitButton;
    @FXML
    private Label fileInfoLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea logInfoTextArea;
    @FXML
    private CheckBox createdTimeCheckBox;
    @FXML
    private CheckBox lastModifyTimeCheckBox;

    @FXML
    private void initialize() {
        CommonComponent commonComponent = new CommonComponent();
        AtomicReference<List<File>> selectedFiles = new AtomicReference<>(new ArrayList<>());
        // click select file button event
        selectFileButton.setOnMouseClicked(event -> selectedFiles.set(commonComponent.initFileChooser(fileInfoLabel)));
        // click submit button event
        submitButton.setOnMouseClicked(event -> {
            String categories = categoriesTextField.getText();
            String tags = tagsTextField.getText();
            String date = datePicker.getValue() == null
                    ? null
                    : datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            final ComponentParameter componentParameter = new ComponentParameter(null,
                    Arrays.toString(categories.split("#")),
                    Arrays.toString(tags.split("#")),
                    date,
                    createdTimeCheckBox.isSelected(),
                    lastModifyTimeCheckBox.isSelected());
            MarkdownFileProcessor.exportMarkdownFile(logInfoTextArea, selectedFiles.get(), componentParameter);
        });
        // date picker component event
        datePicker.setOnAction(event -> {
            createdTimeCheckBox.setDisable(true);
            lastModifyTimeCheckBox.setDisable(true);
        });
        // checkbox event
        createdTimeCheckBox.setOnAction(event -> {
            lastModifyTimeCheckBox.setDisable(createdTimeCheckBox.isSelected());
            datePicker.setDisable(createdTimeCheckBox.isSelected());
        });
        lastModifyTimeCheckBox.setOnAction(event -> {
            createdTimeCheckBox.setDisable(lastModifyTimeCheckBox.isSelected());
            datePicker.setDisable(lastModifyTimeCheckBox.isSelected());
        });
    }
}
