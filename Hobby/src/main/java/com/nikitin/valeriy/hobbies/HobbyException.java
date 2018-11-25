package com.nikitin.valeriy.hobbies;

/**
 * Represents custom Hobby exception.
 * @author Valeriy Nikitin
 */
public class HobbyException extends Exception {
    public HobbyException() {
        super();
    }

    public HobbyException(String message) {
        super(message);
    }

    public HobbyException(String message, Throwable err) {
        super(message, err);
    }
}


