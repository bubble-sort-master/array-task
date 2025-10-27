package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.service.ArrayService;
import com.innowise.arraytask.exception.CustomArrayException;

public class ArrayServiceImpl implements ArrayService {
    @Override
    public int findMin(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        if (elements.length == 0) {
            throw new CustomArrayException("Array is empty");
        }

        int min = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] < min) {
                min = elements[i];
            }
        }
        return min;
    }

    @Override
    public int findMax(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        if (elements.length == 0) {
            throw new CustomArrayException("Array is empty");
        }

        int max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        return max;
    }

    @Override
    public CustomArray replaceValues(CustomArray array, int valueToReplace, int newValue) {
        int[] elements = array.getElements();
        int[] newElements = elements.clone();

        for (int i = 0; i < newElements.length; i++) {
            if (newElements[i] == valueToReplace) {
                newElements[i] = newValue;
            }
        }

        return CustomArray.newBuilder()
                .setElements(newElements)
                .setId(array.getId())
                .build();
    }

    @Override
    public CustomArray replaceAtIndex(CustomArray array, int index, int newValue) throws CustomArrayException {
        int[] elements = array.getElements();

        if (index < 0 || index >= elements.length) {
            throw new CustomArrayException("Index " + index + " is out of bounds for array length " + elements.length);
        }

        int[] newElements = elements.clone();
        newElements[index] = newValue;

        return CustomArray.newBuilder()
                .setElements(newElements)
                .setId(array.getId())
                .build();
    }

    @Override
    public int calculateAverage(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        if (elements.length == 0) {
            throw new CustomArrayException("Cannot calculate average of empty array");
        }

        long sum = 0;
        for (int element : elements) {
            sum += element;
        }
        return (int) (sum / elements.length);
    }

    @Override
    public int calculateSum(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        if (elements.length == 0) {
            throw new CustomArrayException("Cannot calculate sum of empty array");
        }

        int sum = 0;
        for (int element : elements) {
            sum += element;
        }
        return sum;
    }

    @Override
    public int countPositive(CustomArray array) {
        int[] elements = array.getElements();
        int count = 0;
        for (int element : elements) {
            if (element > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countNegative(CustomArray array) {
        int[] elements = array.getElements();
        int count = 0;
        for (int element : elements) {
            if (element < 0) {
                count++;
            }
        }
        return count;
    }
}