package com.web_dev.notebook.exceptions;

public class NotFoundAuthenticationExecution extends RuntimeException {
    public NotFoundAuthenticationExecution() {
        super("Could not find authentication.");
    }
}
