package com.nc.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

/** ArrayTaskList implementation
*
*/
public class ArrayTaskList extends TaskList {
    private Task[] tasks;
    private int capacity = 5;
    private int size;

    /**
     * ArrayTaksList default constructor
     */
    public ArrayTaskList() {
        tasks = new Task[capacity];
    }

    /**
     * Adds new task
     * @param task parameter
     */
    @Override
    public void add(Task task) {
        if (size == capacity) {
            int addCapacity = 5;
            capacity += addCapacity;
            increaseArray(tasks, capacity);
        }

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                size++;
                break;
            }
        }
    }

    /**
     * Increase existing array if default capacity isn't enough
     * @param tasks array to increase
     * @param capacity capacity to increase
     * @return existing array after increasing
     */
    private Task[] increaseArray(Task[] tasks, int capacity) {
        Task[] newTasks = new Task[capacity];
        System.arraycopy(tasks, 0, newTasks, 0, tasks.length);
        this.tasks = new Task[capacity];
        this.tasks = newTasks;
        return this.tasks;
    }

    /**
     * Removes a task
     * @param task parameter
     * @return result
     */
    @Override
    public boolean remove(Task task) {
        boolean result = false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].equals(task)) {
                trim(i);
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean remove(int index) {
        boolean result = false;
        for (int i = 0; i < tasks.length; i++) {
            if (index >=0 && i == index) {
                trim(i);
                result = true;
                break;
            }
        }
        return result;
    }

    public void trim(int i) {
        Task[] newTasks = new Task[capacity];
        if (i == 0) { // first element must be deleted
            System.arraycopy(tasks, i + 1, newTasks, 0,
                    tasks.length - 1);
        } else {
            System.arraycopy(tasks, 0, newTasks, 0, i);
            System.arraycopy(tasks, i + 1, newTasks, i,
                    tasks.length - i - 1);
        }
        tasks = newTasks;
        size--;
    }

    /**
     * Get ArrayTaksList size
     * @return index
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Get specific task by an index
     * @param index parameter
     * @return task
     */
    @Override
    public Task getTask(int index) {
        return tasks[index];
    }
    
    @Override
    public TaskList customListIncoming(Date from, Date to) {
        TaskList list = new ArrayTaskList();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].isActive()) {
                if (tasks[i].nextTimeAfter(from).getTime() <= to.getTime()
                        && tasks[i].nextTimeAfter(from).getTime() != new Date().getTime()) {
                    list.add(tasks[i]);
                }
            }
        }
        return list;
    }

    /**
     * Prints ArrayTaskList
     */
    public void printArrayTaskList() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                System.out.println(i + 1 + " task is: " + tasks[i].getTitle());
            } else {
                break;
            }

        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size && tasks[index] != null;
            }

            @Override
            public Task next() {
                return tasks[index++];
            }

            @Override
            public void remove() {
                if (index <= 0) {
                    throw new IllegalStateException("You can't remove task before first next() method call");
                }
                ArrayTaskList.this.remove(--index);
            }

        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList taskList = (ArrayTaskList) o;
        return size == taskList.size &&
                Arrays.equals(tasks, taskList.tasks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(tasks);
        return result;
    }
    
    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList cloned = (ArrayTaskList) super.clone();
        cloned.tasks = (Task[])tasks.clone();
        return cloned;
    }

}