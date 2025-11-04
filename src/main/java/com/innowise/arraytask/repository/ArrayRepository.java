package com.innowise.arraytask.repository;

import com.innowise.arraytask.comparator.CustomArrayComparator;
import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayRepository{
    private static final Logger logger = LogManager.getLogger(ArrayRepository.class);
    private static ArrayRepository instance;
    private List<CustomArray> arrays;

    private ArrayRepository() {
        arrays = new ArrayList<>();
        logger.debug("ArrayRepository instance created");
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    public void add(CustomArray array) {
        if (array != null) {
            arrays.add(array);
            logger.debug("CustomArray added to repository - ID: {}", array.getId());
        }
    }

    public void removeById(long id) {
        boolean removed = arrays.removeIf(array -> array.getId() == id);
        if (removed) {
            logger.debug("CustomArray removed from repository - ID: {}", id);
        }
    }

    public List<CustomArray> query(Specification specification) {
        List<CustomArray> result = arrays.stream()
                .filter(specification::specify)
                .collect(Collectors.toList());

        logger.debug("Query executed - found {} arrays matching specification", result.size());
        return result;
    }

    public List<CustomArray> sort(CustomArrayComparator comparator) {
        List<CustomArray> result = arrays.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        logger.debug("Sorting completed using {} - {} arrays sorted", comparator.name(), result.size());
        return result;
    }
}