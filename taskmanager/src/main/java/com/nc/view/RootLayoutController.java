package com.nc.view;

import com.nc.controller.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * RootLayoutController class
 */
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
        app.setFilePath(null);
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

        // opens previous file location
        if (app.getFilePath() != null) {
            configureInitialDirectory(fileChooser);
        }
        fileChooser.getInitialDirectory();
        // Shows file load dialog
        File file = fileChooser.showOpenDialog(app.getPrimaryStage());

        if (file != null) {
            handleNew(); //clears previous selection from the task list
            app.loadTaskDataFromFile(file);
        }
    }

    /**
     * Saves task data
     */
    @FXML
    private void save() {
        File personFile = app.getFilePath();
        if (personFile != null) {
            app.saveTaskDataToFile(personFile);
        } else {
            saveAs();
        }
    }

    /**
     * SavesAs task data
     */
    @FXML
    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "DAT files (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        // opens previous file location
        if (app.getFilePath() != null) {
            configureInitialDirectory(fileChooser);
        }
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
     * Sets initial directory to be the last viewed directory
     * @param fileChooser - current fileChooser
     */
    private void configureInitialDirectory(final FileChooser fileChooser){
        Path currDirPath = Paths.get(app.getFilePath().toString());
        String currDir = currDirPath.getParent().toString();
        fileChooser.setInitialDirectory(new File(currDir));
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
