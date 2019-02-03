package com.nc.view;

import com.nc.controller.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * RootLayoutController class
 */
public class RootLayoutController {
    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Removes all tasks.
     */
    @FXML
    private void handleNew() {
        autoSave(null, false);
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
        File file = app.getFilePath();
        if (file != null) {
            app.saveTaskDataToFile(file);
            autoSave(file, true);
        } else {
            saveAs();
        }
    }

    /**
     * Autosaves data to a file
     * @param file file for saving data
     * @param working true or false to start/stop saving
     */
    public void autoSave(File file, boolean working) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        if (working) {
            executorService.scheduleWithFixedDelay(
                    () -> app.saveTaskDataToFile(file), 1, 1, TimeUnit.MINUTES);
        } else {
            executorService.shutdownNow();
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
            autoSave(file, true);
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
