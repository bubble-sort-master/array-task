package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.specification.Specification;
import com.innowise.arraytask.warehouse.ArrayWarehouse;

public class AverageSpecification implements Specification {
    private final int targetAverage;

    public AverageSpecification(int targetAverage) {
        this.targetAverage = targetAverage;
    }

    @Override
    public boolean specify(CustomArray array) {
        ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
        var parameters = warehouse.getArrayParameters(array.getId());
        if (parameters == null) return false;

        return parameters.getAverage() == targetAverage;
    }
}
