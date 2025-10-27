package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.service.ArraySortService;

public class ArraySortServiceImpl implements ArraySortService {

    @Override
    public CustomArray bubbleSort(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        if (elements.length == 0) {
            throw new CustomArrayException("Cannot sort empty array");
        }

        int[] sortedArray = elements.clone();
        int n = sortedArray.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }

        return createSortedArray(array, sortedArray);
    }

    @Override
    public CustomArray selectionSort(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        if (elements.length == 0) {
            throw new CustomArrayException("Cannot sort empty array");
        }

        int[] sortedArray = elements.clone();
        int n = sortedArray.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortedArray[j] < sortedArray[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = sortedArray[minIndex];
            sortedArray[minIndex] = sortedArray[i];
            sortedArray[i] = temp;
        }

        return createSortedArray(array, sortedArray);
    }

    @Override
    public CustomArray insertionSort(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        if (elements.length == 0) {
            throw new CustomArrayException("Cannot sort empty array");
        }

        int[] sortedArray = elements.clone();
        int n = sortedArray.length;

        for (int i = 1; i < n; i++) {
            int key = sortedArray[i];
            int j = i - 1;

            while (j >= 0 && sortedArray[j] > key) {
                sortedArray[j + 1] = sortedArray[j];
                j = j - 1;
            }
            sortedArray[j + 1] = key;
        }

        return createSortedArray(array, sortedArray);
    }


    private CustomArray createSortedArray(CustomArray originalArray, int[] sortedElements) {
        return CustomArray.newBuilder()
                .setElements(sortedElements)
                .setId(originalArray.getId())
                .build();
    }
}