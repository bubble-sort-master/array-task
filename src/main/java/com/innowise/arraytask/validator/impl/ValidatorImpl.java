package com.innowise.arraytask.validator.impl;

import com.innowise.arraytask.validator.Validator;

//надо сильно переделать
public class ValidatorImpl implements Validator {

    @Override
    public boolean isValidLine(String line) {
        if (line == null || line.isBlank()) {
            return false;
        }

        return line.matches(INTEGERS_WITH_DELIMITERS_REGEX);
    }

}