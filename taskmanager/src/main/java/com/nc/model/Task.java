package com.nc.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Task class
 */
public class Task implements Comparable<Task>, Cloneable, Serializable {
    private String title;
    private Date time;
    private Date startTime;
    private Date endTime;
    private int repeatInterval;
    private boolean active;
    private boolean repeated;

    /**
     * Default constructor
     */
    public Task() {
        active = true;
    }

    /**
     * Constructor that creates non-active task,
     * which exists in a certain time without repetition
     * @param title parameter
     * @param time parameter
     */
    public Task(String title, Date time) {
            if (time.getTime() < 0) {
                throw new IllegalArgumentException();
            }
            this.title = title;
            this.time = time;
            active = true;
            repeated = false;
    }

    /**
     * Constructor that creates non-active task,
     * which executes in a certain period of time (including start and end time)
     * with specified interval
     * @param title parameter
     * @param start parameter
     * @param end parameter
     * @param interval parameter
     */
    public Task(String title, Date start, Date end, int interval) throws IllegalArgumentException {
        if (start.getTime() < 0 || end.getTime() < 0 || interval <= 0) {
            throw new IllegalArgumentException();
        }
        repeated = true;
        this.title = title;
        startTime = start;
        endTime = end;
        repeatInterval = interval*1000;
        active = true;
    }

    /**
     * title getter
     * @return title field
     */
    public String getTitle() {
        return title;
    }

    public StringProperty getTitleProperty() {
        StringProperty titleProperty = new SimpleStringProperty(getTitle());
        return titleProperty;
    }

    /**
     * title setter
     * @param title parameter
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks whether the task is active
     * @return active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the task to active
     * @param active parameter
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Time getter
     * @return time
     */
    public Date getTime() {
        Date time;
        if (isRepeated()) {
            time = this.startTime;
        } else {
            time = this.time;
        }
        return time;
    }

    public StringProperty getTimeProperty() {
        StringProperty timeProperty = new SimpleStringProperty(getTime().toString());
        return timeProperty;
    }

    /**
     * Time setter
     * @param time parameter
     */
    public void setTime(Date time) {
        if (isRepeated()) {
            repeated = false;
        }
        this.time = time;
    }

    /**
     * StartTime getter
     * @return time
     */
    public Date getStartTime() {
        Date time;
        if (!isRepeated()) {
            time = this.time;
        } else {
            time = this.startTime;
        }
        return time;
    }

    /**
     * EndTime getter
     * @return time
     */
    public Date getEndTime() {
        Date time;
        if (!isRepeated()) {
            time = this.time;
        } else {
            time = this.endTime;
        }
        return time;
    }

    /**
     * Check if the task is repeated
     * @return repeated
     */
    public boolean isRepeated() {
        return repeated;
    }

    /**
     * RepeatInterval getter
     * @return time
     */
    public int getRepeatInterval() {
        int time;
        if (!isRepeated()) {
            time = 0;
        } else {
            time = this.repeatInterval;
        }
        return time;
    }

    /**
     * Time setter
     * @param start parameter
     * @param end parameter
     * @param interval parameter
     */
    public void setTime(Date start, Date end, int interval) {
        if (interval > 0) {
            repeated = true;
        } else {
            System.out.println("Wrong interval");
            return;
        }
        startTime = start;
        endTime = end;
        repeatInterval = interval;
    }

    /**
     * Returns time of the following task execution
     * after the current time
     * @param current parameter
     * @return time
     */
    public Date nextTimeAfter(Date current) {
        Date time = new Date();
        if (isRepeated() && isActive()) {
            if (current.getTime() >= endTime.getTime()) {
                return null;
            }
            if (current.getTime() < startTime.getTime()) {
                time = startTime;
                return time;
            }
            int i = 1; // counter
            long nextRepetition = startTime.getTime() + repeatInterval;
            while (nextRepetition < endTime.getTime()) {
                nextRepetition = startTime.getTime() + repeatInterval * i;
                if (nextRepetition > current.getTime() && nextRepetition <= endTime.getTime()) {
                    time.setTime(nextRepetition);
                    return time;
                }
                i++;
            }
            if (endTime.getTime() < current.getTime() + repeatInterval) {
                    return time;
            }
        } else if (!isRepeated() && isActive()) {
            if (current.getTime() < this.time.getTime()) {
                time = this.time;
            } else if ( this.time.getTime() <= current.getTime()) {
                return null;
            }

        } else if (!isActive()) {
            return null;
        }
        return time; 
    }

    @Override
    public String toString() {
        return "******************"
                + "\nTask title: " + title
                + "\nCurrent time: " + time
                + "\nStart time: " + startTime
                + "\nEnd time: " + endTime
                + "\nIs active: " + active
                + "\nIs repeated: " + repeated
                + "\nRepeat interval: " + repeatInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(time, task.time)
                && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time); //calls hashcode for ech element, shouldn't be used if performance is critical
        //return 13*Objects.hashCode(title) + 31*time;
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task)super.clone();
    }

    @Override
    public int compareTo(Task o) {
        return title.compareTo(o.title);
    }
}