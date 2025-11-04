package com.innowise.arraytask.repository.impl;

import com.innowise.arraytask.comparator.CustomArrayComparator;
import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.repository.ArrayRepository;
import com.innowise.arraytask.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayRepositoryImpl implements ArrayRepository {
    private static final Logger logger = LogManager.getLogger(ArrayRepositoryImpl.class);
    private static ArrayRepositoryImpl instance;
    private List<CustomArray> arrays;

    private ArrayRepositoryImpl() {
        arrays = new ArrayList<>();
        logger.debug("ArrayRepositoryImpl instance created");
    }

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(CustomArray array) {
        if (array != null) {
            arrays.add(array);
            logger.debug("CustomArray added to repository - ID: {}", array.getId());
        }
    }

    @Override
    public void remove(CustomArray array) {
        if (arrays.remove(array)) {
            logger.debug("CustomArray removed from repository - ID: {}", array.getId());
        }
    }

    @Override
    public void removeById(long id) {
        boolean removed = arrays.removeIf(array -> array.getId() == id);
        if (removed) {
            logger.debug("CustomArray removed from repository - ID: {}", id);
        }
    }

    @Override
    public CustomArray getById(long id) {
        CustomArray result = arrays.stream()
                .filter(array -> array.getId() == id)
                .findFirst()
                .orElse(null);

        if (result == null) {
            logger.debug("CustomArray not found in repository - ID: {}", id);
        }
        return result;
    }

    @Override
    public List<CustomArray> getAll() {
        logger.debug("Retrieving all arrays from repository - total: {}", arrays.size());
        return new ArrayList<>(arrays);
    }

    @Override
    public List<CustomArray> query(Specification specification) {
        List<CustomArray> result = arrays.stream()
                .filter(specification::specify)
                .collect(Collectors.toList());

        logger.debug("Query executed - found {} arrays matching specification", result.size());
        return result;
    }

    @Override
    public List<CustomArray> sort(CustomArrayComparator comparator) {
        List<CustomArray> result = arrays.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        logger.debug("Sorting completed using {} - {} arrays sorted", comparator.name(), result.size());
        return result;
    }
}