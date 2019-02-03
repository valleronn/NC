package com.nc.controller;

import com.nc.model.ArrayTaskList;
import com.nc.model.Task;
import com.nc.model.TaskList;
import com.nc.model.Tasks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * CalendarWindowController class
 */
public class CalendarWindowController {
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ListView<Date> dateList;
    @FXML
    private TableView<Task> calendarTaskTable;
    @FXML
    private TableColumn<Task, String> titleColumn;

    private Stage dialogStage;
    private App app;
    private Map<Date, Set<Task>> taskMap;

    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Initializes DatePickers values
     * and listens for date selection to display proper task
     */
    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProperty());
        dateList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldDate, newDate) -> showTasksByDate(newDate)
        );
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Shows tasks by specified date
     * @param date date parameter
     */
    private void showTasksByDate(Date date) {
        ObservableList<Task> tasks = FXCollections.observableArrayList(taskMap.get(date));
        calendarTaskTable.setItems(tasks);
    }

    @FXML
    /**
     * Update task list event
     */
    private void updateTaskList() {
        setCalendar();
    }

    /**
     * Sets the calendar with required dates
     */
    public void setCalendar() {
        TaskList list = new ArrayTaskList();
        for (Task task: app.getTaskData()) {
            list.add(task);
        }
        Date startDate = java.sql.Date.valueOf(startDatePicker.getValue());
        Date endDate = java.sql.Date.valueOf(endDatePicker.getValue());
        int dayInMSec = 86399000; //24 hours in milliseconds
        Date fullEndDate = new Date(endDate.getTime() + dayInMSec);
        taskMap = Tasks.calendar(list, startDate, fullEndDate);
        ObservableList<Date> taskDates = FXCollections.observableArrayList(taskMap.keySet());
        dateList.setItems(taskDates);
    }

}
