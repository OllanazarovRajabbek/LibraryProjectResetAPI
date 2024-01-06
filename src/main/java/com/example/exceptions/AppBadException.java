package com.example.exceptions;

import org.w3c.dom.ranges.RangeException;

public class AppBadException extends RuntimeException {
    public AppBadException(String message) {
        super(message);
    }
}
