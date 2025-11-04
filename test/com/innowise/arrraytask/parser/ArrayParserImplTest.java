package com.innowise.arrraytask.parser;

import com.innowise.arraytask.parser.ArrayParser;
import com.innowise.arraytask.parser.impl.ArrayParserImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayParserImplTest {

    private final ArrayParser parser = new ArrayParserImpl();

    private static final String VALID_LINE_WITH_DIFFERENT_DELIMITERS = "1; 2, -3 4";
    private static final String INVALID_LINE_WITH_LETTER = "1, a, 3";
    private static final String INVALID_LINE_WITH_WRONG_DELIMITERS = "1/26/3";

    @Test
    void givenValidLine_whenParse_thenReturnIntArray() {
        List<String> input = List.of(VALID_LINE_WITH_DIFFERENT_DELIMITERS);
        List<int[]> actual = parser.parseLinesToIntArrays(input);
        int[] expected = {1, 2, -3, 4};

        assertAll(
                () -> assertEquals(1, actual.size()),
                () -> assertArrayEquals(expected, actual.get(0))
        );
    }

    @Test
    void givenInvalidLines_whenParse_thenReturnEmptyList() {
        List<String> input = List.of(INVALID_LINE_WITH_LETTER, INVALID_LINE_WITH_WRONG_DELIMITERS);
        List<int[]> actual = parser.parseLinesToIntArrays(input);

        assertTrue(actual.isEmpty());
    }

    @Test
    void givenMixedValidAndInvalidLines_whenParse_thenReturnOnlyValidArrays() {
        List<String> input = List.of(VALID_LINE_WITH_DIFFERENT_DELIMITERS, INVALID_LINE_WITH_LETTER, INVALID_LINE_WITH_WRONG_DELIMITERS);
        List<int[]> actual = parser.parseLinesToIntArrays(input);
        int[] expected = {1, 2, -3, 4};

        assertAll(
                () -> assertEquals(1, actual.size()),
                () -> assertArrayEquals(expected, actual.get(0))
        );
    }
}
