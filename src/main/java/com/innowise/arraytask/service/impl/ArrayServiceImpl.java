package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.service.ArrayService;
import com.innowise.arraytask.exception.CustomArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayServiceImpl implements ArrayService {
    private static final Logger logger = LogManager.getLogger(ArrayServiceImpl.class);

    @Override
    public int findMin(CustomArray array) throws CustomArrayException {
        logger.debug("Finding min value for array id: {}", array.getId());

        int[] elements = array.getElements();
        if (elements.length == 0) {
            logger.error("Cannot find min in empty array id: {}", array.getId());
            throw new CustomArrayException("Array is empty");
        }

        int min = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] < min) {
                min = elements[i];
            }
        }
        logger.info("Found min value: {} for array id: {}", min, array.getId());
        return min;
    }

    @Override
    public int findMax(CustomArray array) throws CustomArrayException {
        logger.debug("Finding max value for array id: {}", array.getId());

        int[] elements = array.getElements();
        if (elements.length == 0) {
            logger.error("Cannot find max in empty array id: {}", array.getId());
            throw new CustomArrayException("Array is empty");
        }

        int max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        logger.info("Found max value: {} for array id: {}", max, array.getId());
        return max;
    }

    @Override
    public void replaceValues(CustomArray array, int valueToReplace, int newValue) {
        logger.debug("Replacing values in array id: {} - {} -> {}",
                array.getId(), valueToReplace, newValue);

        int[] elements = array.getElements();
        int[] newElements = elements.clone();

        for (int i = 0; i < newElements.length; i++) {
            if (newElements[i] == valueToReplace) {
                newElements[i] = newValue;
            }
        }

        try {
            array.setElements(newElements);
            logger.info("Values replaced successfully in array id: {}", array.getId());
        } catch (CustomArrayException e) {
            logger.error("Failed to replace values in array id {}: {}", array.getId(), e.getMessage(), e);
        }
    }

    @Override
    public int calculateAverage(CustomArray array) throws CustomArrayException {
        logger.debug("Calculating average for array id: {}", array.getId());

        int[] elements = array.getElements();
        if (elements.length == 0) {
            logger.error("Cannot calculate average of empty array id: {}", array.getId());
            throw new CustomArrayException("Cannot calculate average of empty array");
        }

        long sum = 0;
        for (int element : elements) {
            sum += element;
        }
        int average = (int) (sum / elements.length);
        logger.info("Calculated average: {} for array id: {}", average, array.getId());
        return average;
    }

    @Override
    public int calculateSum(CustomArray array) throws CustomArrayException {
        logger.debug("Calculating sum for array id: {}", array.getId());

        int[] elements = array.getElements();
        if (elements.length == 0) {
            logger.error("Cannot calculate sum of empty array id: {}", array.getId());
            throw new CustomArrayException("Cannot calculate sum of empty array");
        }

        int sum = 0;
        for (int element : elements) {
            sum += element;
        }
        logger.info("Calculated sum: {} for array id: {}", sum, array.getId());
        return sum;
    }

    @Override
    public int countPositive(CustomArray array) {
        logger.debug("Counting positive values for array id: {}", array.getId());

        int[] elements = array.getElements();
        int count = 0;
        for (int element : elements) {
            if (element > 0) {
                count++;
            }
        }
        logger.info("Found {} positive values for array id: {}", count, array.getId());
        return count;
    }

    @Override
    public int countNegative(CustomArray array) {
        logger.debug("Counting negative values for array id: {}", array.getId());

        int[] elements = array.getElements();
        int count = 0;
        for (int element : elements) {
            if (element < 0) {
                count++;
            }
        }
        logger.info("Found {} negative values for array id: {}", count, array.getId());
        return count;
    }
}
