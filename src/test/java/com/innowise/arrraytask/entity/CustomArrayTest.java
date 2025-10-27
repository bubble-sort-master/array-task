package com.innowise.arrraytask.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.innowise.arraytask.entity.CustomArray;
import org.junit.jupiter.api.Test;

class CustomArrayTest {

    @Test
    void shouldBeImmutableAndReturnDefensiveCopies() {
        int[] originalElements = {1, 2, 3};
        long id = 123L;

        CustomArray array = CustomArray.newBuilder()
                .setElements(originalElements)
                .setId(id)
                .build();

        originalElements[0] = 999;

        array.getElements()[0] = 888;

        assertArrayEquals(new int[]{1, 2, 3}, array.getElements(),
                "CustomArray should return a copy");
        assertEquals(id, array.getId());
    }
}