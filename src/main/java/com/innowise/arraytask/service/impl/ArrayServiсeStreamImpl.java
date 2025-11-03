package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.service.ArrayService;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayServiseStreamImpl implements ArrayService {

    @Override
    public int findMin(CustomArray array) throws CustomArrayException {
        return Arrays.stream(array.getElements())
                .min()
                .orElseThrow(() -> new CustomArrayException("Array is empty"));
    }

    @Override
    public int findMax(CustomArray array) throws CustomArrayException {
        return Arrays.stream(array.getElements())
                .max()
                .orElseThrow(() -> new CustomArrayException("Array is empty"));
    }

    @Override
    public void replaceValues(CustomArray array, int valueToReplace, int newValue) {
        int[] newElements = Arrays.stream(array.getElements())
                .map(element -> element == valueToReplace ? newValue : element)
                .toArray();

        array.setElements(newElements);
    }

    @Override
    public void replaceAtIndex(CustomArray array, int index, int newValue) throws CustomArrayException {
        int[] elements = array.getElements();

        if (index < 0 || index >= elements.length) {
            throw new CustomArrayException("Index " + index + " is out of bounds for array length " + elements.length);
        }
        
        int[] newElements = IntStream.range(0, elements.length)
                .map(i -> i == index ? newValue : elements[i])
                .toArray();

        array.setElements(newElements);
    }

    @Override
    public int calculateAverage(CustomArray array) throws CustomArrayException {
        return (int) Arrays.stream(array.getElements())
                .average()
                .orElseThrow(() -> new CustomArrayException("Cannot calculate average of empty array"));
    }

    @Override
    public int calculateSum(CustomArray array) throws CustomArrayException {
        int[] elements = array.getElements();
        return Arrays.stream(elements)
                .reduce(Integer::sum)
                .orElseThrow(() -> new CustomArrayException("Cannot calculate sum of empty array"));
    }

    @Override
    public int countPositive(CustomArray array) {
        int[] elements = array.getElements();
        return (int) Arrays.stream(elements)
                .filter(element -> element > 0)
                .count();
    }

    @Override
    public int countNegative(CustomArray array) {
        int[] elements = array.getElements();
        return (int) Arrays.stream(elements)
                .filter(element -> element < 0)
                .count();
    }
}
