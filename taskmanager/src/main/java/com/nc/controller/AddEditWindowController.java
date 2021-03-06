package com.nc.controller;

import com.nc.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    /**
     * Specifies default values
     * during initialization
     */
    @FXML
    private void initialize() {
        timeField.setVisible(true);
        timeField.setText(DATE_FORMAT.format(new Date()));
        startTimeField.setVisible(false);
        endTimeField.setVisible(false);
        repeatIntervalField.setVisible(false);
        repeatableCheckBoxListener();
    }

    /**
     * Changes visibility of fields upon checkbox clicking
     */
    public void repeatableCheckBoxListener() {
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
     * @param task task to set
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
                repeatIntervalField.setText(Integer.toString(task.getRepeatInterval()/60000));
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
        if (isInputValid()) {
            task.setTitle(titleField.getText());
            if (!task.isRepeated()) {
                task.setTime(DATE_FORMAT.parse(timeField.getText()));
            } else {
                task.setTime(DATE_FORMAT.parse(startTimeField.getText()),
                        DATE_FORMAT.parse(endTimeField.getText()),
                        Integer.parseInt(repeatIntervalField.getText())*60000);
            }
            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Checks user's input
     * @return true if valid, otherwise false
     */
    private boolean isInputValid() {
        boolean result = true;
        if (titleField.getText().equals("")) {
            titleField.setPromptText("Title can't be empty");
            result = false;
        }
        if (!isRepeatableCheckBox.isSelected()) {
            try {
                DATE_FORMAT.parse(timeField.getText());
            } catch (ParseException e) {
                timeField.setText(DATE_FORMAT.format(new Date()));
                showWrongDateAlert();
                result = false;
            }
        } else {
            try {
                DATE_FORMAT.parse(startTimeField.getText());
                DATE_FORMAT.parse(endTimeField.getText());
            } catch (ParseException e) {
                startTimeField.setText(DATE_FORMAT.format(new Date()));
                endTimeField.setText(DATE_FORMAT.format(new Date()));
                showWrongDateAlert();
                result = false;
            }
            String wrongInterval = "Repeat interval must be in minutes";
            if (repeatIntervalField.getText() == null
                    || repeatIntervalField.getText().length() == 0) {
                repeatIntervalField.setPromptText(wrongInterval);
                result = false;
            } else {
                try {
                    Integer.parseInt(repeatIntervalField.getText());
                } catch (NumberFormatException e) {
                    repeatIntervalField.setText("");
                    repeatIntervalField.setPromptText(wrongInterval);
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Shows an alert when entered date is wrong
     */
    private void showWrongDateAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrong Date format");
        alert.setHeaderText(null);
        alert.setContentText("Correct pattern is: yyyy-MM-dd HH:mm");
        alert.showAndWait();
    }

    /**
     * Cancel button click event.
     */
    @FXML
    private void cancel() {
        dialogStage.close();
    }
}
