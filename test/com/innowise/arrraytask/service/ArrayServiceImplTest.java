package com.innowise.arrraytask.service;


import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.service.impl.ArrayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayServiceImplTest {
    private ArrayServiceImpl arrayService;
    private CustomArray testArray;
    private CustomArray emptyArray;

    private static final int[] TEST_ELEMENTS = {5, -2, 8, 0, -1, 3};
    private static final int[] EMPTY_ELEMENTS = {};

    @BeforeEach
    void setUp() {
        arrayService = new ArrayServiceImpl();
        testArray = CustomArray.newBuilder()
                .setElements(TEST_ELEMENTS)
                .setId(1L)
                .build();
        emptyArray = CustomArray.newBuilder()
                .setElements(EMPTY_ELEMENTS)
                .setId(2L)
                .build();
    }

    @Test
    void findMinShouldReturnMinimumValue() throws CustomArrayException {
        int expected = -2;
        int actual = arrayService.findMin(testArray);
        assertEquals(expected, actual);
    }

    @Test
    void findMinShouldThrowExceptionForEmptyArray() {
        assertThrows(CustomArrayException.class, () -> arrayService.findMin(emptyArray));
    }

    @Test
    void findMaxShouldReturnMaximumValue() throws CustomArrayException {
        int expected = 8;
        int actual = arrayService.findMax(testArray);
        assertEquals(expected, actual);
    }

    @Test
    void findMaxShouldThrowExceptionForEmptyArray() {
        assertThrows(CustomArrayException.class, () -> arrayService.findMax(emptyArray));
    }

    @Test
    void replaceValuesShouldReplaceAllOccurrences() throws CustomArrayException {
        CustomArray array = CustomArray.newBuilder()
                .setElements(new int[]{1, 2, 3, 2, 4})
                .setId(3L)
                .build();

        arrayService.replaceValues(array, 2, 9);

        int[] expectedElements = {1, 9, 3, 9, 4};
        int[] actualElements = array.getElements();
        assertArrayEquals(expectedElements, actualElements);
    }

    @Test
    void replaceValuesShouldNotChangeArrayWhenValueNotFound() throws CustomArrayException {
        CustomArray array = CustomArray.newBuilder()
                .setElements(new int[]{1, 2, 3})
                .setId(4L)
                .build();
        int[] originalElements = array.getElements().clone();

        arrayService.replaceValues(array, 5, 9);

        assertArrayEquals(originalElements, array.getElements());
    }

    @Test
    void replaceAtIndexShouldReplaceElementAtSpecificIndex() throws CustomArrayException {
        CustomArray array = CustomArray.newBuilder()
                .setElements(new int[]{1, 2, 3})
                .setId(5L)
                .build();

        arrayService.replaceAtIndex(array, 1, 9);

        int[] expectedElements = {1, 9, 3};
        int[] actualElements = array.getElements();
        assertArrayEquals(expectedElements, actualElements);
    }

    @Test
    void replaceAtIndexShouldThrowExceptionForInvalidIndex() {
        CustomArray array = CustomArray.newBuilder()
                .setElements(new int[]{1, 2, 3})
                .setId(6L)
                .build();

        assertThrows(CustomArrayException.class, () -> arrayService.replaceAtIndex(array, 5, 9));
    }

    @Test
    void calculateAverageShouldReturnCorrectAverage() throws CustomArrayException {
        int expected = 2;
        int actual = arrayService.calculateAverage(testArray);
        assertEquals(expected, actual);
    }

    @Test
    void calculateAverageShouldThrowExceptionForEmptyArray() {
        assertThrows(CustomArrayException.class, () -> arrayService.calculateAverage(emptyArray));
    }

    @Test
    void calculateSumShouldReturnCorrectSum() throws CustomArrayException {
        int expected = 13;
        int actual = arrayService.calculateSum(testArray);
        assertEquals(expected, actual);
    }

    @Test
    void calculateSumShouldThrowExceptionForEmptyArray() {
        assertThrows(CustomArrayException.class, () -> arrayService.calculateSum(emptyArray));
    }

    @Test
    void countPositiveShouldReturnCorrectCount() {
        int expected = 3;
        int actual = arrayService.countPositive(testArray);
        assertEquals(expected, actual);
    }

    @Test
    void countPositiveShouldReturnZeroForEmptyArray() {
        int expected = 0;
        int actual = arrayService.countPositive(emptyArray);
        assertEquals(expected, actual);
    }

    @Test
    void countNegativeShouldReturnCorrectCount() {
        int expected = 2;
        int actual = arrayService.countNegative(testArray);
        assertEquals(expected, actual);
    }

    @Test
    void countNegativeShouldReturnZeroForEmptyArray() {
        int expected = 0;
        int actual = arrayService.countNegative(emptyArray);
        assertEquals(expected, actual);
    }

    @Test
    void testAllMethodsWithSingleElementArray() throws CustomArrayException {
        CustomArray singleElementArray = CustomArray.newBuilder()
                .setElements(new int[]{7})
                .setId(7L)
                .build();

        assertAll(
                () -> assertEquals(7, arrayService.findMin(singleElementArray)),
                () -> assertEquals(7, arrayService.findMax(singleElementArray)),
                () -> assertEquals(7, arrayService.calculateSum(singleElementArray)),
                () -> assertEquals(7, arrayService.calculateAverage(singleElementArray)),
                () -> assertEquals(1, arrayService.countPositive(singleElementArray)),
                () -> assertEquals(0, arrayService.countNegative(singleElementArray))
        );
    }
}