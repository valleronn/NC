package com.nc.model;

import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

/** LinkedTaskList implementation
*
*/
public class LinkedTaskList extends TaskList {
    private Node head;
    private int size;

    /**
     * Adds new task
     * @param task parameter
     */
    @Override
    public void add(Task task) {
        Node node = new Node(task);
        size++;
        if (head == null) {
            head = node;
        } else {
            Node curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(node);
        }
    }

    /**
     * Removes a task
     * @param task parameter
     * @return result
     */
    @Override
    public boolean remove(Task task) {
        Node curr = head;
        Node prev = null;

        while (curr != null) {
            if (curr.getTask().equals(task)) {
                if (prev != null) {
                    prev.setNext(curr.getNext());
                } else {
                    head = curr.getNext();
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return false;
    }

    public boolean remove(int index) {
        Node curr = head;
        Node prev = null;
        int counter = 0;

        while (curr != null) {
            if (counter == index) {
                if (prev != null) {
                    prev.setNext(curr.getNext());
                } else {
                    head = curr.getNext();
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.getNext();
            counter++;
        }
        return false;
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
        Task task = null;
        if (index < 0) {
            return null;
        }
        Node curr = null;
        if (head != null) {
            curr = head;
            for (int i = 0; i < index; i++) {
                if (curr.getNext() == null) {
                    return null;
                }
                curr = curr.getNext();
            }
            return curr.getTask();
        }
        if (task != null)
            task = curr.getTask();
        return task;

    }

    @Override
    public TaskList customListIncoming(Date from, Date to) {
        TaskList list = new LinkedTaskList();
        Node curr = head;
        while (curr != null) {
            if (curr.getTask().isActive() && curr.getTask().nextTimeAfter(from).getTime() <= to.getTime()
                    && curr.getTask().nextTimeAfter(from).getTime() != new Date().getTime()) {
                list.add(curr.getTask());
            }
            curr = curr.getNext();
        }
        return list;
    }

    public void printLinkedTaskList() {
        Node curr = head;
        while (curr.getNext() != null) {
            System.out.print(curr + "\n");
            curr = curr.getNext();
        }
        System.out.print(curr + "\n");
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private Node curr = null;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (curr == null && head != null) {
                    return true;
                } else if (curr != null) {
                    return curr.getNext() != null;
                }
                return false;
            }

            @Override
            public Task next() {
                index++;
                if (curr == null && head != null) {
                    curr = head;
                    return head.getTask();
                } else {
                    curr = curr.getNext();
                    return curr.getTask();
                }
            }

            @Override
            public void remove() {
                if (index <= 0) {
                    throw new IllegalStateException("You can't remove task before first next() method call");
                }
                LinkedTaskList.this.remove(--index);
            }

        };
    }

    private class Node {
        Node next;
        Task task;

       Node(Task task) {
            this.task = task;
        }

        Node(Node next, Task task) {
            this.next = next;
            this.task = task;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Task getTask() {
            return task;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(next, node.next) &&
                    Objects.equals(task, node.task);
        }

        @Override
        public int hashCode() {
            return Objects.hash(next, task);
        }

        @Override
        public String toString() {
            return "" + task;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        return size == that.size &&
                Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }
    
    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList cloned = (LinkedTaskList)super.clone();
        cloned.head = null;
        cloned.size = 0;
        for (Node curr = head; curr != null; curr = curr.getNext())
            cloned.add(curr.getTask());
        return cloned;
    }
}