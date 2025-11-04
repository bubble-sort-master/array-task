package com.innowise.arraytask.validator.impl;

import com.innowise.arraytask.validator.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatorImpl implements Validator {
    private static final Logger logger = LogManager.getLogger(ValidatorImpl.class);

    @Override
    public boolean isValidLine(String line) {
        if (line == null || line.isBlank()) {
            logger.warn("Validation failed: line is null or blank");
            return false;
        }
        boolean isValid = line.matches(INTEGERS_WITH_DELIMITERS_REGEX);

        if (isValid) {
            logger.debug("Line validation successful for: '{}'", line);
        } else {
            logger.warn("Line validation failed for: '{}'", line);
        }

        return isValid;
    }

}