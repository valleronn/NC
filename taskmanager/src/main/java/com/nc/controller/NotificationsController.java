package com.nc.controller;

import com.nc.model.Task;
import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.util.*;

/**
 * NotificationsController class
 */
public class NotificationsController {
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
                                if (!task.isRepeated() && (task.nextTimeAfter(new Date()) == null)
                                        && !tasksSet.contains(task)) {
                                    Notifications.create().title(task.getTitle()).text(message).show();
                                    tasksSet.add(task);
                                } else if (task.isRepeated() && (task.nextTimeAfter(new Date()) == null)
                                                && !tasksSet.contains(task)) {
                                    Notifications.create().title(task.getTitle()).text(message).show();
                                    tasksSet.add(task);
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
