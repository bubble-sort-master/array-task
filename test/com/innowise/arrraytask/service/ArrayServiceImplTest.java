package com.innowise.arrraytask.service;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.service.impl.ArrayServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayServiceImplTest {

    private final ArrayServiceImpl service = new ArrayServiceImpl();

    private static final int[] ELEMENTS = {3, -1, 7, 0, -5};
    private static final CustomArray TEST_ARRAY;

    static {
        try {
            TEST_ARRAY = CustomArray.newBuilder()
                    .setElements(ELEMENTS)
                    .setId()
                    .build();
        } catch (CustomArrayException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenArray_whenFindMin_thenReturnMinValue() throws CustomArrayException {
        int actual = service.findMin(TEST_ARRAY);
        int expected = -5;
        assertEquals(expected, actual);
    }

    @Test
    void givenArray_whenFindMax_thenReturnMaxValue() throws CustomArrayException {
        int actual = service.findMax(TEST_ARRAY);
        int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    void givenArray_whenCalculateSum_thenReturnCorrectSum() throws CustomArrayException {
        int actual = service.calculateSum(TEST_ARRAY);
        int expected = 3 + (-1) + 7 + (-5);
        assertEquals(expected, actual);
    }

    @Test
    void givenArray_whenCalculateAverage_thenReturnCorrectAverage() throws CustomArrayException {
        int actual = service.calculateAverage(TEST_ARRAY);
        int expected = (3 + (-1) + 7 + 0 + (-5)) / ELEMENTS.length;
        assertEquals(expected, actual);
    }

    @Test
    void givenArray_whenCountPositive_thenReturnCorrectCount() {
        int actual = service.countPositive(TEST_ARRAY);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void givenArray_whenCountNegative_thenReturnCorrectCount() {
        int actual = service.countNegative(TEST_ARRAY);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void givenArray_whenReplaceValues_thenValuesAreReplaced() throws CustomArrayException {
        CustomArray array = CustomArray.newBuilder()
                .setElements(new int[]{1, 2, 2, 3})
                .setId()
                .build();

        service.replaceValues(array, 2, 9);

        int[] expected = {1, 9, 9, 3};
        int[] actual = array.getElements();

        assertAll(
                () -> assertEquals(expected.length, actual.length),
                () -> assertArrayEquals(expected, actual)
        );
    }
}
