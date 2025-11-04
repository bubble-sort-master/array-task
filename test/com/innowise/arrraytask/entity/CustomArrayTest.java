package com.innowise.arrraytask.entity;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.observer.ArrayObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayTest {
    private CustomArray testArray;
    private CustomArray emptyArray;

    private static final int[] TEST_ELEMENTS = {1, 2, 3, 4, 5};

    @BeforeEach
    void setUp() throws CustomArrayException {
        testArray = CustomArray.newBuilder()
                .setElements(TEST_ELEMENTS)
                .setId()
                .build();
    }

    @Test
    void getElementsShouldReturnCopy() {
        int[] original = testArray.getElements();
        original[0] = 999;

        int expected = 1;
        int actual = testArray.getElements()[0];

        assertEquals(expected, actual);
    }

    @Test
    void setElementsShouldCreateCopy() throws CustomArrayException {
        int[] externalArray = {10, 20, 30};
        testArray.setElements(externalArray);
        externalArray[0] = 999;

        int expected = 10;
        int actual = testArray.getElements()[0];

        assertEquals(expected, actual);
    }

    @Test
    void setElementShouldUpdateSpecificIndex() throws CustomArrayException {
        testArray.setElement(2, 99);

        int expected = 99;
        int actual = testArray.getElements()[2];

        assertEquals(expected, actual);
    }

    @Test
    void setElementShouldThrowExceptionForNegativeIndex() {
        assertThrows(CustomArrayException.class, () -> testArray.setElement(-1, 99));
    }

    @Test
    void setElementShouldThrowExceptionForIndexOutOfBounds() {
        assertThrows(CustomArrayException.class, () -> testArray.setElement(10, 99));
    }

    @Test
    void builderSetElementsShouldCreateCopy() throws CustomArrayException {
        int[] externalArray = {7, 8, 9};
        CustomArray array = CustomArray.newBuilder()
                .setElements(externalArray)
                .setId()
                .build();
        externalArray[0] = 999;

        int expected = 7;
        int actual = array.getElements()[0];

        assertEquals(expected, actual);
    }

    @Test
    void setElementsShouldThrowExceptionForEmptyArray() {
        assertThrows(CustomArrayException.class, () -> testArray.setElements(new int[]{}));
    }

}