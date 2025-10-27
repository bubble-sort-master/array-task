package com.innowise.arraytask.parser;

public interface ArrayParser {
    String SPLIT_REGEX = "[ ,;]+";
    int[] parseLineToIntArray(String line);
}
