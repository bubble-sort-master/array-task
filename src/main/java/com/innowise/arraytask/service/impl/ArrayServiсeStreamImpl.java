package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.service.ArrayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayServiсeStreamImpl implements ArrayService {
    private static final Logger logger = LogManager.getLogger(ArrayServiсeStreamImpl.class);

    @Override
    public int findMin(CustomArray array) throws CustomArrayException {
        logger.debug("Finding min value using streams for array id: {}", array.getId());
        try {
            int min = Arrays.stream(array.getElements())
                    .min()
                    .orElseThrow(() -> new CustomArrayException("Array is empty"));
            logger.info("Found min value: {} for array id: {}", min, array.getId());
            return min;
        } catch (CustomArrayException e) {
            logger.error("Cannot find min in empty array id: {}", array.getId());
            throw e;
        }
    }

    @Override
    public int findMax(CustomArray array) throws CustomArrayException {
        logger.debug("Finding max value using streams for array id: {}", array.getId());
        try {
            int max = Arrays.stream(array.getElements())
                    .max()
                    .orElseThrow(() -> new CustomArrayException("Array is empty"));
            logger.info("Found max value: {} for array id: {}", max, array.getId());
            return max;
        } catch (CustomArrayException e) {
            logger.error("Cannot find max in empty array id: {}", array.getId());
            throw e;
        }
    }

    @Override
    public void replaceValues(CustomArray array, int valueToReplace, int newValue) {
        logger.debug("Replacing values using streams in array id: {} - {} -> {}",
                array.getId(), valueToReplace, newValue);

        int[] newElements = Arrays.stream(array.getElements())
                .map(element -> element == valueToReplace ? newValue : element)
                .toArray();

        try {
            array.setElements(newElements);
            logger.info("Values replaced successfully in array id: {}", array.getId());
        } catch (CustomArrayException e) {
            logger.error("Failed to replace values in array id {}: {}", array.getId(), e.getMessage(), e);
        }
    }

    @Override
    public int calculateAverage(CustomArray array) throws CustomArrayException {
        logger.debug("Calculating average using streams for array id: {}", array.getId());
        try {
            int average = (int) Arrays.stream(array.getElements())
                    .average()
                    .orElseThrow(() -> new CustomArrayException("Cannot calculate average of empty array"));
            logger.info("Calculated average: {} for array id: {}", average, array.getId());
            return average;
        } catch (CustomArrayException e) {
            logger.error("Cannot calculate average of empty array id: {}", array.getId());
            throw e;
        }
    }

    @Override
    public int calculateSum(CustomArray array) throws CustomArrayException {
        logger.debug("Calculating sum using streams for array id: {}", array.getId());
        try {
            int sum = Arrays.stream(array.getElements())
                    .reduce(Integer::sum)
                    .orElseThrow(() -> new CustomArrayException("Cannot calculate sum of empty array"));
            logger.info("Calculated sum: {} for array id: {}", sum, array.getId());
            return sum;
        } catch (CustomArrayException e) {
            logger.error("Cannot calculate sum of empty array id: {}", array.getId());
            throw e;
        }
    }

    @Override
    public int countPositive(CustomArray array) {
        logger.debug("Counting positive elements using streams for array id: {}", array.getId());
        int count = (int) Arrays.stream(array.getElements())
                .filter(element -> element > 0)
                .count();
        logger.info("Counted {} positive elements using streams for array id: {}", count, array.getId());
        return count;
    }

    @Override
    public int countNegative(CustomArray array) {
        logger.debug("Counting negative elements using streams for array id: {}", array.getId());
        int count = (int) Arrays.stream(array.getElements())
                .filter(element -> element < 0)
                .count();
        logger.info("Counted {} negative elements using streams for array id: {}", count, array.getId());
        return count;
    }
}