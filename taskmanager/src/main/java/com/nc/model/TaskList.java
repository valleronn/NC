package com.nc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

public abstract class TaskList implements Iterable, Cloneable, Serializable {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    
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
