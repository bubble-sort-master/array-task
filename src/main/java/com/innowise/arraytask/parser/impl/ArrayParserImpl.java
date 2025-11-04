package com.innowise.arraytask.parser.impl;

import com.innowise.arraytask.parser.ArrayParser;
import com.innowise.arraytask.validator.ArrayValidator;
import com.innowise.arraytask.validator.impl.ArrayValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayParserImpl implements ArrayParser {

    private static final Logger logger = LogManager.getLogger(ArrayParserImpl.class);

    @Override
    public List<int[]> parseLinesToIntArrays(List<String> lines) {
        ArrayValidator validator = new ArrayValidatorImpl();

        List<int[]> result = new ArrayList<>();

        for (String line : lines) {
            if (validator.isValidLine(line)) {
                String[] tokens = line.split(SPLIT_REGEX);
                int[] elements = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    elements[i] = Integer.parseInt(tokens[i]);
                }
                result.add(elements);
                logger.debug("Parsed line to int array: {}", Arrays.toString(elements));
            } else {
                logger.warn("Skipping invalid line: '{}'", line);
            }
        }

        logger.info("Parsed {} valid lines into int arrays (from {} total)", result.size(), lines.size());
        return result;
    }
}