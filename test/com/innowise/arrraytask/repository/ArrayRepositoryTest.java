package com.innowise.arrraytask.repository;

import com.innowise.arraytask.comparator.CustomArrayComparator;
import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.repository.impl.ArrayRepositoryImpl;
import com.innowise.arraytask.specification.Specification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryImplTest {
    private ArrayRepositoryImpl repository;
    private CustomArray testArray1;
    private CustomArray testArray2;
    private CustomArray testArray3;

    private static final int[] ELEMENTS_1 = {5, 2, 8, 1, 9};
    private static final int[] ELEMENTS_2 = {10, -20, 30, -40};
    private static final int[] ELEMENTS_3 = {3, 7, 1};

    @BeforeEach
    void setUp() {
        repository = ArrayRepositoryImpl.getInstance();
        testArray1 = CustomArray.newBuilder()
                .setElements(ELEMENTS_1)
                .setId(1L)
                .build();
        testArray2 = CustomArray.newBuilder()
                .setElements(ELEMENTS_2)
                .setId(2L)
                .build();
        testArray3 = CustomArray.newBuilder()
                .setElements(ELEMENTS_3)
                .setId(3L)
                .build();
    }

    @AfterEach
    void tearDown() {
        repository.getAll().forEach(repository::remove);
    }

    @Test
    void addShouldStoreCustomArray() {
        repository.add(testArray1);

        long arrayId = testArray1.getId();
        CustomArray actual = repository.getById(arrayId);

        assertEquals(testArray1, actual);
    }

    @Test
    void addShouldNotStoreNullArray() {
        repository.add(null);

        int expectedSize = 0;
        int actualSize = repository.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void removeShouldDeleteCustomArray() {
        repository.add(testArray1);
        long arrayId = testArray1.getId();
        repository.remove(testArray1);

        CustomArray actual = repository.getById(arrayId);

        assertNull(actual);
    }

    @Test
    void removeByIdShouldDeleteCustomArray() {
        repository.add(testArray1);
        long arrayId = testArray1.getId();
        repository.removeById(arrayId);

        CustomArray actual = repository.getById(arrayId);

        assertNull(actual);
    }

    @Test
    void getByIdShouldReturnCorrectCustomArray() {
        repository.add(testArray1);
        repository.add(testArray2);

        long arrayId = testArray2.getId();
        CustomArray actual = repository.getById(arrayId);

        assertEquals(testArray2, actual);
    }

    @Test
    void getByIdShouldReturnNullForNonExistentId() {
        CustomArray actual = repository.getById(999L);

        assertNull(actual);
    }

    @Test
    void getAllShouldReturnAllArrays() {
        repository.add(testArray1);
        repository.add(testArray2);

        int expectedSize = 2;
        int actualSize = repository.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void getAllShouldReturnEmptyListForEmptyRepository() {
        int expectedSize = 0;
        int actualSize = repository.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void queryShouldReturnMatchingArrays() {
        repository.add(testArray1);
        repository.add(testArray2);
        repository.add(testArray3);

        Specification alwaysTrueSpec = array -> true;
        List<CustomArray> result = repository.query(alwaysTrueSpec);

        int expectedSize = 3;
        int actualSize = result.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void queryShouldReturnEmptyListForNoMatches() {
        repository.add(testArray1);
        repository.add(testArray2);

        Specification alwaysFalseSpec = array -> false;
        List<CustomArray> result = repository.query(alwaysFalseSpec);

        int expectedSize = 0;
        int actualSize = result.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void sortShouldOrderArraysByComparator() {
        repository.add(testArray1);
        repository.add(testArray2);
        repository.add(testArray3);

        List<CustomArray> sorted = repository.sort(CustomArrayComparator.ELEMENT_COUNT);

        int expectedFirstCount = 3;
        int actualFirstCount = sorted.get(0).getElements().length;
        int expectedLastCount = 5;
        int actualLastCount = sorted.get(2).getElements().length;

        assertAll(
                () -> assertEquals(expectedFirstCount, actualFirstCount),
                () -> assertEquals(expectedLastCount, actualLastCount)
        );
    }

    @Test
    void sortShouldReturnEmptyListForEmptyRepository() {
        List<CustomArray> sorted = repository.sort(CustomArrayComparator.ID);

        int expectedSize = 0;
        int actualSize = sorted.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void getInstanceShouldReturnSameInstance() {
        ArrayRepositoryImpl firstInstance = ArrayRepositoryImpl.getInstance();
        ArrayRepositoryImpl secondInstance = ArrayRepositoryImpl.getInstance();

        assertEquals(firstInstance, secondInstance);
    }

    @Test
    void repositoryShouldBeIndependentBetweenTests() {
        repository.add(testArray1);

        int expectedSize = 1;
        int actualSize = repository.getAll().size();

        assertEquals(expectedSize, actualSize);
    }
}