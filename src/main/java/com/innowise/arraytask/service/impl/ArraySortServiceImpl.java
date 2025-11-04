package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.service.ArraySortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArraySortServiceImpl implements ArraySortService {

    private static final Logger logger = LogManager.getLogger(ArraySortServiceImpl.class);

    @Override
    public void bubbleSort(CustomArray array) {
        logger.debug("Starting bubble sort for array id: {}", array.getId());
        int[] elements = array.getElements();
        if (elements.length == 0) {
            logger.error("Cannot sort empty array id: {}", array.getId());
            throw new RuntimeException(new CustomArrayException("Cannot sort empty array"));
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

        try {
            array.setElements(sortedArray);
            logger.info("Bubble sort completed for array id: {}", array.getId());
        } catch (CustomArrayException e) {
            logger.error("Failed to set sorted elements for array id {}: {}", array.getId(), e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectionSort(CustomArray array) {
        logger.debug("Starting selection sort for array id: {}", array.getId());
        int[] elements = array.getElements();
        if (elements.length == 0) {
            logger.error("Cannot sort empty array id: {}", array.getId());
            throw new RuntimeException(new CustomArrayException("Cannot sort empty array"));
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

        try {
            array.setElements(sortedArray);
            logger.info("Selection sort completed for array id: {}", array.getId());
        } catch (CustomArrayException e) {
            logger.error("Failed to set sorted elements for array id {}: {}", array.getId(), e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertionSort(CustomArray array) {
        logger.debug("Starting insertion sort for array id: {}", array.getId());
        int[] elements = array.getElements();
        if (elements.length == 0) {
            logger.error("Cannot sort empty array id: {}", array.getId());
            throw new RuntimeException(new CustomArrayException("Cannot sort empty array"));
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

        try {
            array.setElements(sortedArray);
            logger.info("Insertion sort completed for array id: {}", array.getId());
        } catch (CustomArrayException e) {
            logger.error("Failed to set sorted elements for array id {}: {}", array.getId(), e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
