package com.innowise.arrraytask.repository;

import com.innowise.arraytask.comparator.CustomArrayComparator;
import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.observer.impl.ArrayObserverImpl;
import com.innowise.arraytask.repository.ArrayRepository;
import com.innowise.arraytask.specification.Specification;
import com.innowise.arraytask.specification.impl.SumSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryTest {

    private ArrayRepository repository;

    private static final int[] ELEMENTS1 = {1, 2, 3};
    private static final int[] ELEMENTS2 = {10, 20};
    private static final int[] ELEMENTS3 = {5};

    private CustomArray array1;
    private CustomArray array2;
    private CustomArray array3;

    @BeforeEach
    void setUp() throws CustomArrayException {
        repository = ArrayRepository.getInstance();

        repository.sort(CustomArrayComparator.ID).forEach(a -> repository.removeById(a.getId()));

        array1 = CustomArray.newBuilder().setElements(ELEMENTS1).setId().build();
        array2 = CustomArray.newBuilder().setElements(ELEMENTS2).setId().build();
        array3 = CustomArray.newBuilder().setElements(ELEMENTS3).setId().build();

        repository.add(array1);
        repository.add(array2);
        repository.add(array3);
    }

    @Test
    void givenRepository_whenAdd_thenArrayIsStored() {
        List<CustomArray> all = repository.sort(CustomArrayComparator.ID);

        assertAll(
                () -> assertTrue(all.contains(array1)),
                () -> assertTrue(all.contains(array2)),
                () -> assertTrue(all.contains(array3))
        );
    }

    @Test
    void givenRepository_whenRemoveById_thenArrayIsRemoved() {
        repository.removeById(array2.getId());
        List<CustomArray> all = repository.sort(CustomArrayComparator.ID);

        assertFalse(all.contains(array2));
    }

    @Test
    void givenSumSpecification_whenQuery_thenReturnMatchingArrays() throws CustomArrayException {
        CustomArray array = CustomArray.newBuilder()
                .setElements(new int[]{1, 2, 3}) // сумма = 6
                .setId()
                .build();

        array.attach(new ArrayObserverImpl());

        repository.add(array);

        Specification spec = new SumSpecification(6);
        List<CustomArray> result = repository.query(spec);

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(array, result.get(0))
        );
    }


    @Test
    void givenComparator_whenSortByElementCount_thenReturnSortedList() {
        List<CustomArray> sorted = repository.sort(CustomArrayComparator.ELEMENT_COUNT);

        assertAll(
                () -> assertEquals(array3, sorted.get(0)),
                () -> assertEquals(array2, sorted.get(1)),
                () -> assertEquals(array1, sorted.get(2))
        );
    }
}
