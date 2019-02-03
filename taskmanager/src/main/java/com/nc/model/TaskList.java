package com.nc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents TaskList class
 */
public abstract class TaskList implements Iterable, Cloneable, Serializable {
    /**
     * Adds new task
     * @param task parameter
     */
    public abstract void add(Task task);

    /**
     * Removes a task
     * @param task parameter
     */
    public abstract boolean remove(Task task);

    /**
     * Returns list size
     * @return
     */
    public abstract int size();

    /**
     * Get specific task by index
     * @param index
     * @return
     */
    public abstract Task getTask(int index);

    /**
     * Incoming method
     * @param from date
     * @param to date
     * @return TaskList
     */
    public abstract TaskList customListIncoming(Date from, Date to);

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
