package com.nc.controller;

import com.nc.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * TaskManagerController class
 */
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
            new SimpleDateFormat("yyyy-MM-dd HH:mm");

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
        //refreshes table to display task title correctly
        //after renaming
        taskTable.getColumns().get(0).setVisible(false);
        taskTable.getColumns().get(0).setVisible(true);

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
                repeatIntervalLabel.setText(Integer.toString(task.getRepeatInterval()/60000));
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
        Task task = taskTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            if (showRemoveAlert(task)) {
                taskTable.getItems().remove(selectedIndex);
            }
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

    /**
     * Opens Calendar window
     */
    @FXML
    private void openCalendar() {
        app.showCalendarWindow();
    }

    /**
     * Shows Alert window
     */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(app.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Task Selected");
        alert.setContentText("Please select a task in the list.");
        alert.showAndWait();
    }

    /**
     * Shows remove Alert window
     * @param task task that could be removed
     * @return true or false
     */
    private boolean showRemoveAlert(Task task) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(app.getPrimaryStage());
        alert.setTitle("Removing " + task.getTitle());
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove "
                + task.getTitle() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}
