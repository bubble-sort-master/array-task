package com.innowise.arraytask.validator;

public interface Validator {
    String INTEGERS_WITH_DELIMITERS_REGEX = "^-?\\d+(?:[ ,;]+-?\\d+)*$";

    boolean isValidLine(String line);

}
