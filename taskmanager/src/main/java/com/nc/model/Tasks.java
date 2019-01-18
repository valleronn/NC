package com.nc.model;

import java.util.*;

public class Tasks {

    /**
     * Returns tasks which occur in specified interval
     * @param from start time
     * @param to end time
     * @return TaskList collection
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date from, Date to) {
        TaskList tasksToReturn = null;
        if (tasks instanceof ArrayTaskList) {
            tasksToReturn = new ArrayTaskList();
        } else {
            tasksToReturn = new LinkedTaskList();
        }

        for (Task task: tasks) {
            if (task != null && task.isActive() && task.nextTimeAfter(from) != null) {
                if (task.nextTimeAfter(from).getTime() <= to.getTime()
                        && task.nextTimeAfter(from).getTime() != new Date().getTime()) {
                    tasksToReturn.add(task);
                }
            }
        }
        return tasksToReturn;
    }

    /**
     * Returns calendar of tasks which occur in specified interval
     * @param start start time
     * @param end end time
     * @return TaskList collection
     */
    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {
        SortedMap<Date, Set<Task>> tasksMap = new TreeMap<>();
        for (Task task : tasks) {
            Date next = task.nextTimeAfter(start);
            while (next != null && (next.getTime() <= end.getTime())) {
                if (tasksMap.containsKey(next)) {
                    Set<Task> set = tasksMap.get(next);
                    set.add(task);
                } else {
                    Set<Task> set = new HashSet<>();
                    set.add(task);
                    tasksMap.put(next, set);
                }
                next = task.nextTimeAfter(next);
            }
        }

        /*for (Map.Entry<Date, Set<Task>> entry: tasksMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
        }*/

        return tasksMap;
    }
}
