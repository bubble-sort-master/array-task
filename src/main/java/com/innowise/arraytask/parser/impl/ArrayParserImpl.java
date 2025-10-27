package com.innowise.arraytask.parser.impl;

import com.innowise.arraytask.parser.ArrayParser;

public class ArrayParserImpl implements ArrayParser {

    @Override
    public int[] parseLineToIntArray(String line) {
        String[] tokens = line.split(SPLIT_REGEX);
        int[] elements = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            elements[i] = Integer.parseInt(tokens[i]);
        }
        return elements;
    }
}