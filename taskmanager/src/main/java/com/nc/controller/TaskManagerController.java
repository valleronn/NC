package com.nc.controller;

import com.nc.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.SimpleDateFormat;

public class TaskManagerController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private Label titleLabel;
    @FXML
    private Label isActiveLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private Label repeatIntervalLabel;

    private App app;
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");

    public TaskManagerController() {

    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProperty());

        showTaskDetails(null);

        //Listen for selection and display data accordingly
        taskTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTaskDetails(newValue));
    }

    public void setApp(App app) {
        this.app = app;
        taskTable.setItems(app.getTaskData());
    }

    /**
     * Fills all the fields showing task details.
     * @param task task parameter or null.
     */
    private void showTaskDetails(Task task) {
        if (task != null) {
            //fill all the labels with task data
            titleLabel.setText(task.getTitle());
            isActiveLabel.setText(Boolean.toString(task.isActive()));
            if (!task.isRepeated()) {
                timeLabel.setText(DATE_FORMAT.format(task.getTime()));
                startTimeLabel.setText("");
                endTimeLabel.setText("");
                repeatIntervalLabel.setText("");
            } else {
                timeLabel.setText("");
                startTimeLabel.setText(DATE_FORMAT.format(task.getStartTime()));
                endTimeLabel.setText(DATE_FORMAT.format(task.getEndTime()));
                repeatIntervalLabel.setText(Integer.toString(task.getRepeatInterval()));
            }
        } else {
            // if Task is null remove all the text
            titleLabel.setText("");
            isActiveLabel.setText("");
            timeLabel.setText("");
            startTimeLabel.setText("");
            endTimeLabel.setText("");
            repeatIntervalLabel.setText("");
        }
    }

    /**
     * Delete task event.
     */
    @FXML
    private void deleteTask() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            taskTable.getItems().remove(selectedIndex);
        } else {
            showAlert();
        }
    }

    /**
     * Used when user hits Add... button.
     * Opens AddEditWindowController.
     */
    @FXML
    private void addNewTask() {
        Task newTask = new Task();
        boolean saveClicked = app.showAddEditWindow(newTask);
        if (saveClicked) {
            app.getTaskData().add(newTask);
        }
    }

    /**
     * Edit task event.
     */
    @FXML
    private void editTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            boolean saveClicked = app.showAddEditWindow(selectedTask);
            if (saveClicked) {
                showTaskDetails(selectedTask);
            }
        } else {
            showAlert();
        }
    }

    @FXML
    private void openCalendar() {
        app.showCalendarWindow();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(app.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Task Selected");
        alert.setContentText("Please select a task in the list.");
        alert.showAndWait();
    }
}
