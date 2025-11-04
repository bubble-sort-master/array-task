package com.innowise.arraytask.validator;

public interface ArrayValidator {
    String INTEGERS_WITH_DELIMITERS_REGEX = "^-?\\d+(?:[ ,;]+-?\\d+)*$";

    boolean isValidLine(String line);
}
