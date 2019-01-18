package com.nc.controller;

import com.nc.model.ArrayTaskList;
import com.nc.model.Task;
import com.nc.model.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CalendarWindowController {
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TableView<Task> calendarTaskTable;
    @FXML
    private TableColumn<Task, String> dateColumn;
    @FXML
    private TableColumn<Task, String> titleColumn;

    private Stage dialogStage;
    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(
                cellData -> cellData.getValue().getTimeProperty());
        titleColumn.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProperty());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void updateTaskList() {
        setCalendar();
    }

    public void setCalendar() {
        TaskList list = new ArrayTaskList();
        for (Task task: app.getTaskData()) {
            list.add(task);
        }
        //Date startDate = java.sql.Date.valueOf(startDatePicker.getValue());
        //Date endDate = java.sql.Date.valueOf(endDatePicker.getValue());
        //Tasks.calendar(list, startDate, endDate);

        calendarTaskTable.setItems(app.getTaskData());

    }

}
