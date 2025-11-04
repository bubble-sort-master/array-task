package com.innowise.arraytask.observer.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.observer.ArrayObserver;
import com.innowise.arraytask.service.ArrayService;
import com.innowise.arraytask.service.impl.ArrayServiceImpl;
import com.innowise.arraytask.entity.ArrayParameters;
import com.innowise.arraytask.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayObserverImpl implements ArrayObserver {
    private static final Logger logger = LogManager.getLogger(ArrayObserverImpl.class);

    @Override
    public void arrayUpdate(CustomArray array) {

        ArrayService arrayService = new ArrayServiceImpl();

        try {
            int sum = arrayService.calculateSum(array);
            double average = arrayService.calculateAverage(array);
            int min = arrayService.findMin(array);
            int max = arrayService.findMax(array);
            int count = array.getElements().length;

            ArrayParameters parameters = new ArrayParameters(sum, average, min, max, count);

            ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
            warehouse.putArrayParameters(array.getId(), parameters);

        } catch (CustomArrayException e) {
            logger.error("Failed to update parameters for array id {}: {}", array.getId(), e.getMessage(), e);
        }
    }
}