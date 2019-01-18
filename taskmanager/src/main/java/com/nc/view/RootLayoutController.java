package com.nc.view;

import com.nc.controller.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {
    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Creates empty Task Manager.
     */
    @FXML
    private void handleNew() {
        app.getTaskData().clear();
    }

    /**
     * Opens FileChooser to load task data
     */
    @FXML
    private void open() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "DAT files (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        // Shows file load dialog
        File file = fileChooser.showOpenDialog(app.getPrimaryStage());

        if (file != null) {
            app.loadTaskDataFromFile(file);
        }
    }

    /**
     * Saves task data
     */
    @FXML
    private void save() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "DAT files (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        // show save file dialog
        File file = fileChooser.showSaveDialog(app.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".dat")) {
                file = new File(file.getPath() + ".dat");
            }
            app.saveTaskDataToFile(file);
        }
    }

    /**
     * About dialog event.
     */
    @FXML
    private void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task Manager");
        alert.setHeaderText("About");
        alert.setContentText("Task Manager application");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
