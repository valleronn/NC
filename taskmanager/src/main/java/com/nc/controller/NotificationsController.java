package com.nc.controller;

import com.nc.model.Task;
import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * NotificationsController class
 */
public class NotificationsController {
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private App app;
    private Set tasksSet;

    public NotificationsController(App app) {
        this.app = app;
        tasksSet = new HashSet();
    }

    /**
     * Shows notifications.
     */
    public void showNotifications() {
        Thread thread = new Thread(new Runnable() {
            private void postMessage(final String message, Task task) {
                Platform.runLater(() -> {
                    System.out.println(new Date() + " Showing notification");
                    Notifications.create().title(task.getTitle()).text(message).show();
                });
            }

            @Override
            public void run() {
                try {
                    final Map<Task, ScheduledExecutorService> taskExecuterMap = new HashMap<>();
                    while(true) {
                        for (Task task : app.getTaskData()) {
                            if (task.isActive()) {
                                checkCreateExecuter(taskExecuterMap, task);
                                if (!task.isRepeated()
                                        && DATE_FORMAT.format(task.getTime()).equals(DATE_FORMAT.format(new Date()))
                                        && !tasksSet.contains(task)) {
                                    postMessage("Is on your queue", task);
                                    tasksSet.add(task);
                                } else if (task.isRepeated()) {
                                    long currTime = new Date().getTime();
                                    long startTime = task.getStartTime().getTime();
                                    long endTime = task.getEndTime().getTime();
                                    if (endTime >= currTime) {
                                        if (DATE_FORMAT.format(startTime).equals(DATE_FORMAT.format(currTime))) {
                                            taskExecuterMap.get(task).scheduleWithFixedDelay(
                                                    () -> postMessage("Is on your queue", task),
                                                    0, task.getRepeatInterval(), TimeUnit.MILLISECONDS);
                                        }
                                    } else {
                                        System.out.println("Shutting down the executer " + task.getTitle());
                                        taskExecuterMap.get(task).shutdownNow();
                                        taskExecuterMap.remove(task);
                                        task.setActive(false);
                                    }
                                }
                            }
                        }
                        Thread.sleep(60000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private void checkCreateExecuter(Map<Task, ScheduledExecutorService> taskExecuterMap, Task task) {
                if (!taskExecuterMap.containsKey(task)) {
                    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                    taskExecuterMap.put(task, executorService);
                }
            }

        });
        //setting the thread as a Daemon so that it
        //could be closed upon app exit
        thread.setDaemon(true);
        thread.start();
    }
}
