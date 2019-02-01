package com.nc.controller;

import com.nc.model.Task;
import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * NotificationsController class
 */
public class NotificationsController {
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private App app;
    Set tasksSet;

    public NotificationsController(App app) {
        this.app = app;
        tasksSet = new HashSet();
    }

    /**
     * Shows notifications.
     */
    public void showNotifications() {
        Thread thread = new Thread(new Runnable() {
            private void postMessage(final String message) {
                Platform.runLater(() -> {
                        for (Task task : app.getTaskData()) {
                            if (task != null) {
                                if (!task.isRepeated()
                                        && DATE_FORMAT.format(task.getTime()).equals(DATE_FORMAT.format(new Date()))
                                        && !tasksSet.contains(task)) {
                                    Notifications.create().title(task.getTitle()).text(message).show();
                                    tasksSet.add(task);
                                } else if (task.isRepeated() && !tasksSet.contains(task)) {
                                    int interval = 0;
                                    long currTime = new Date().getTime();
                                    long startTime = task.getStartTime().getTime();
                                    long endTime = task.getEndTime().getTime();
                                    long timeToShow = startTime;
                                    while (currTime <= endTime) {
                                        System.out.println(currTime);
                                        if (DATE_FORMAT.format(timeToShow).equals(DATE_FORMAT.format(currTime))) {
                                            System.out.println("Show notification");
                                            Notifications.create().title(task.getTitle()).text(message).show();
                                            interval = interval + task.getRepeatInterval();
                                            timeToShow = startTime + interval;
                                        }
                                        currTime = new Date().getTime();
                                    }
                                    //tasksSet.add(task);
                                }
                            }
                        }
                    });
            }

            @Override
            public void run() {
                try {
                    while(true) {
                        postMessage("Is on your queue");
                        Thread.sleep(600);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        //setting the thread as a Daemon so that it
        //could be closed upon app exit
        thread.setDaemon(true);
        thread.start();
    }
}
