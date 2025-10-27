package com.innowise.arraytask.entity;

import java.util.Arrays;
import java.util.Objects;

public class CustomArray {

    private int[] elements;
    private long id;

    private CustomArray() {
    }

    public int[] getElements() {
        return elements.clone();
    }

    public long getId() {
        return id;
    }

    public static Builder newBuilder() {
        return new CustomArray().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setElements(int[] elements) {
            CustomArray.this.elements = elements != null ? elements.clone() : new int[0];
            return this;
        }

        public Builder setId(long id) {
            CustomArray.this.id = id;
            return this;
        }

        public CustomArray build() {
            return CustomArray.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArray that = (CustomArray) o;
        return id == that.id && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.hashCode(elements);
    }
}

