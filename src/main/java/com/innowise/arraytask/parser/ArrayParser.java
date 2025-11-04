package com.innowise.arraytask.parser;

import java.util.List;

public interface ArrayParser {
    String SPLIT_REGEX = "[ ,;]+";

    List<int[]> parseLinesToIntArrays(List<String> lines);
}
