package com.innowise.arrraytask.entity;

// Файл: CustomArrayTest.java
import static org.junit.jupiter.api.Assertions.*;

import com.innowise.arraytask.entity.CustomArray;
import org.junit.jupiter.api.Test;

public class CustomArrayTest {

    @Test
    public void testBuilderCreatesCustomArrayCorrectly() {//naming?
        // Исходные данные
        int[] elements = new int[0];
        long id = 123;

        // Создание объекта CustomArray через билдер
        CustomArray customArray = CustomArray.newBuilder()
                .setElements(elements)
                .setId(id)
                .build();

        assertNotNull(customArray, "CustomArray object should not be null");

        assertArrayEquals(elements, customArray.getElements(), "Elements should match the given array");

        assertEquals(id, customArray.getId(), "ID should match the given id");

        //elements[0] = 99;
        //int[] clonedElements = customArray.getElements();
        //assertNotEquals(99, clonedElements[0], "The elements array should be cloned and not be affected by external modification");
    }
}