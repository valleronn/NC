package com.nc.controller;

import com.nc.model.ArrayTaskList;
import com.nc.model.Task;
import com.nc.model.TaskIO;
import com.nc.model.TaskList;
import com.nc.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main class
 */
public class App extends Application {
    final static Logger logger = Logger.getLogger(App.class);
    private BorderPane rootLayout;
    private Stage primaryStage;
    private ObservableList<Task> taskData = FXCollections.observableArrayList();

    public ObservableList<Task> getTaskData() {
        return taskData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        logger.info("Starting the application");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task Manager");

        initRootLayout();
        initTaskManagerWindow();
        initNotifications();

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes Root Layout.
     */
    public void initRootLayout() {
        logger.info("Initializing root layout");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            primaryStage.getIcons().add(new Image("/icon.png"));
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to initialize root layout");
        }
    }

    /**
     * Initializes Task Manager window.
     */
    public void initTaskManagerWindow() {
        logger.info("Initializing task manager window");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/TaskManagerWindow.fxml"));
            AnchorPane taskManagerWindow = (AnchorPane) loader.load();
            rootLayout.setCenter(taskManagerWindow);
            TaskManagerController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to initialize task manager window");
        }
    }

    /**
     * Initializes Add/Edit window.
     */
    public boolean showAddEditWindow(Task task) {
        logger.info("Initializing Add/Edit window");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/AddEditWindow.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image("/icon.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddEditWindowController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTask(task);

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to initialize Add/Edit window");
            return false;
        }
    }

    /**
     * Shows Calendar window.
     */
    public void showCalendarWindow() {
        logger.info("Initializing Calendar window");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/CalendarWindow.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Calendar");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image("/icon.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CalendarWindowController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to initialize Calendar window");
        }
    }

    /**
     * Saves tasks into a file.
     * @param file file where to save the data
     */
    public void saveTaskDataToFile(File file) {
        logger.info("Saving tasks into a file");
        try {
            TaskList list = new ArrayTaskList();
            for (Task task: taskData) {
                list.add(task);
            }
            TaskIO.writeBinary(list, file);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
            logger.error("Failed to save the data");
        }
    }

    /**
     * Loads the data from the file.
     * @param file file from which load the data
     */
    public void loadTaskDataFromFile(File file) {
        logger.info("Loading tasks from the file");
        TaskList list = new ArrayTaskList();
        TaskIO.readBinary(list, file);
        for (int i = 0; i < list.size(); i++) {
            taskData.add(list.getTask(i));
        }
    }

    /**
     * Initializes Notifications.
     */
    public void initNotifications() {
        NotificationsController notifications = new NotificationsController(this);
        notifications.showNotifications();
    }
}
