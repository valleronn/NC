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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class App extends Application {
    private BorderPane rootLayout;
    private Stage primaryStage;
    private ObservableList<Task> taskData = FXCollections.observableArrayList();

    public App() {
        taskData.add(new Task("Task 1", new Date()));
        taskData.add(new Task("Task 2", new Date(0)));
        taskData.add(new Task("Task 3", new Date(), new Date(), 3));
    }

    public ObservableList<Task> getTaskData() {
        return taskData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task Manager");

        initRootLayout();
        initTaskManagerWindow();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTaskManagerWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/TaskManagerWindow.fxml"));
            AnchorPane taskManagerWindow = (AnchorPane) loader.load();
            rootLayout.setCenter(taskManagerWindow);
            TaskManagerController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean showAddEditWindow(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/AddEditWindow.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            AddEditWindowController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTask(task);

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showCalendarWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("../view/CalendarWindow.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Calendar");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            CalendarWindowController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTaskDataToFile(File file) {
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
        }
    }

    public void loadTaskDataFromFile(File file) {
        TaskList list = new ArrayTaskList();
        TaskIO.readBinary(list, file);
        for (int i = 0; i < list.size(); i++) {
            taskData.add(list.getTask(i));
        }
    }
}
