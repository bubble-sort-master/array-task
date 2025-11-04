package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.specification.Specification;

public class IdSpecification implements Specification {
    private final long targetId;

    public IdSpecification(long targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean specify(CustomArray array) {
        return array.getId() == targetId;
    }
}