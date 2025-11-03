package com.innowise.arrraytask.entitytest;

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
    private static final int[] EMPTY_ELEMENTS = {};

    @BeforeEach
    void setUp() {
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
    void getElementsShouldReturnCopy() {
        int[] original = testArray.getElements();
        original[0] = 999;

        int expected = 1;
        int actual = testArray.getElements()[0];

        assertEquals(expected, actual);
    }

    @Test
    void setElementsShouldCreateCopy() {
        int[] externalArray = {10, 20, 30};
        testArray.setElements(externalArray);
        externalArray[0] = 999;

        int expected = 10;
        int actual = testArray.getElements()[0];

        assertEquals(expected, actual);
    }

    @Test
    void setElementsWithNullShouldCreateEmptyArray() {
        testArray.setElements(null);

        int expectedLength = 0;
        int actualLength = testArray.getElements().length;

        assertEquals(expectedLength, actualLength);
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
    void builderSetElementsShouldCreateCopy() {
        int[] externalArray = {7, 8, 9};
        CustomArray array = CustomArray.newBuilder()
                .setElements(externalArray)
                .setId(3L)
                .build();
        externalArray[0] = 999;

        int expected = 7;
        int actual = array.getElements()[0];

        assertEquals(expected, actual);
    }

    @Test
    void builderSetElementsWithNullShouldCreateEmptyArray() {
        CustomArray array = CustomArray.newBuilder()
                .setElements(null)
                .setId(4L)
                .build();

        int expectedLength = 0;
        int actualLength = array.getElements().length;

        assertEquals(expectedLength, actualLength);
    }

    @Test
    void equalsShouldReturnTrueForSameReference() {
        boolean actual = testArray.equals(testArray);
        assertTrue(actual);
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        boolean actual = testArray.equals(null);
        assertFalse(actual);
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        boolean actual = testArray.equals("not a CustomArray");
        assertFalse(actual);
    }

    @Test
    void attachShouldSetObserverAndNotify() {
        TestObserver testObserver = new TestObserver();

        testArray.attach(testObserver);

        assertTrue(testObserver.wasNotified);
    }

    @Test
    void detachShouldRemoveObserver() {
        TestObserver testObserver = new TestObserver();
        testArray.attach(testObserver);

        testArray.detach(testObserver);

        assertDoesNotThrow(() -> {
            testArray.setElement(0, 99);
        });
    }

    @Test
    void toStringShouldContainElements() {
        String actual = testArray.toString();

        assertTrue(actual.contains("elements=[1, 2, 3, 4, 5]"));
    }

    @Test
    void hashCodeShouldBeConsistent() {
        int firstHash = testArray.hashCode();
        int secondHash = testArray.hashCode();

        assertEquals(firstHash, secondHash);
    }

    private static class TestObserver implements ArrayObserver {
        boolean wasNotified = false;

        @Override
        public void arrayUpdate(CustomArray array) {
            wasNotified = true;
        }
    }
}