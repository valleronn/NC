package com.nc.controller;

import com.nc.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * AddEditWindowController class
 */
public class AddEditWindowController {
    private Task task;
    @FXML
    private TextField titleField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private TextField repeatIntervalField;
    @FXML
    private CheckBox isRepeatableCheckBox;

    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Stage dialogStage;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        timeField.setVisible(true);
        timeField.setText(DATE_FORMAT.format(new Date()));
        startTimeField.setVisible(false);
        endTimeField.setVisible(false);
        repeatIntervalField.setVisible(false);
        addEditListener();
    }

    public void addEditListener() {
        isRepeatableCheckBox.selectedProperty().addListener((o, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                timeField.setVisible(false);
                startTimeField.setVisible(true);
                startTimeField.setText(DATE_FORMAT.format(new Date()));
                endTimeField.setVisible(true);
                endTimeField.setText(DATE_FORMAT.format(new Date()));
                repeatIntervalField.setVisible(true);
                task.setRepeated(true);
            } else {
                timeField.setVisible(true);
                timeField.setText(DATE_FORMAT.format(new Date()));
                startTimeField.setVisible(false);
                endTimeField.setVisible(false);
                repeatIntervalField.setVisible(false);
                task.setRepeated(false);
            }
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets task info that will be changed.
     *
     * @param task
     */
    public void setTask(Task task) {
        this.task = task;

        if (task.getTitle() != null) {
            titleField.setText(task.getTitle());
            if (!task.isRepeated()) {
                timeField.setText(DATE_FORMAT.format(task.getTime()));
                timeField.setPromptText("yyyy-MM-dd HH:mm");
            } else {
                isRepeatableCheckBox.setSelected(true);
                startTimeField.setText(DATE_FORMAT.format(task.getStartTime()));
                startTimeField.setPromptText("yyyy-MM-dd HH:mm");
                endTimeField.setText(DATE_FORMAT.format(task.getEndTime()));
                endTimeField.setPromptText("yyyy-MM-dd HH:mm");
                repeatIntervalField.setText(Integer.toString(task.getRepeatInterval()/1000));
            }
        }
    }

    /**
     * Returns true if clicked Save, otherwise false.
     *
     * @return
     */
    public boolean isSaveClicked() {
        return saveClicked;
    }

    /**
     * Save button click event.
     */
    @FXML
    private void saveTask() throws ParseException {
        //if (isInputValid()) {
            task.setTitle(titleField.getText());
            if (!task.isRepeated()) {
                task.setTime(DATE_FORMAT.parse(timeField.getText()));
            } else {
                task.setTime(DATE_FORMAT.parse(startTimeField.getText()),
                        DATE_FORMAT.parse(endTimeField.getText()),
                        Integer.parseInt(repeatIntervalField.getText())*1000);
            }
            saveClicked = true;
            dialogStage.close();
        //}
    }

    /**
     * Cancel button click event.
     */
    @FXML
    private void cancel() {
        dialogStage.close();
    }
}
