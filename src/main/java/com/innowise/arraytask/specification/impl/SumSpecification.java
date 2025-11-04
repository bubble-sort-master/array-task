package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.specification.Specification;
import com.innowise.arraytask.warehouse.ArrayWarehouse;

public class SumSpecification implements Specification {
    private final int targetSum;

    public SumSpecification(int targetSum) {
        this.targetSum = targetSum;
    }

    @Override
    public boolean specify(CustomArray array) {
        ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
        var parameters = warehouse.getArrayParameters(array.getId());
        if (parameters == null) return false;

        return parameters.getSum() == targetSum;
    }
}