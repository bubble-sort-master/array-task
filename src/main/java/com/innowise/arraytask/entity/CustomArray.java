package com.innowise.arraytask.entity;

import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.observer.ArrayObservable;
import com.innowise.arraytask.observer.ArrayObserver;
import com.innowise.arraytask.util.IdGenerator;

import java.util.Arrays;

public class CustomArray implements ArrayObservable {

    private long id;

    private int[] elements;

    private ArrayObserver observer;

    private CustomArray() {}

    public int[] getElements() {
        return elements.clone();
    }

    public long getId() {
        return id;
    }

    public void setElements(int[] elements) throws CustomArrayException {
        if (elements == null || elements.length == 0) {
            throw new CustomArrayException("Elements array cannot be null or empty");
        }
        this.elements = elements.clone();
        notifyObservers();
    }

    public void setElement(int index, int value) throws CustomArrayException {
        if (index < 0 || index >= elements.length) {
            throw new CustomArrayException("Index out of bounds: " + index);
        }
        elements[index] = value;
        notifyObservers();
    }

    public static Builder newBuilder() {
        return new CustomArray().new Builder();
    }

    @Override
    public void attach(ArrayObserver observer) {
        this.observer = observer;
        observer.arrayUpdate(CustomArray.this);
    }

    @Override
    public void detach(ArrayObserver observer) {
        observer = null;
    }

    @Override
    public void notifyObservers() {
        if(observer!=null){
            observer.arrayUpdate(CustomArray.this);
        }
    }

    public class Builder {

        private Builder() {}

        public Builder setElements(int[] elements) throws CustomArrayException {
            if (elements == null || elements.length == 0) {
                throw new CustomArrayException("Elements array cannot be null or empty");
            }
            CustomArray.this.elements = elements.clone();
            return this;
        }

        public Builder setId() {
            CustomArray.this.id = IdGenerator.generateId();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomArray{");
        sb.append("id=").append(id)
                .append(", elements=").append(Arrays.toString(elements))
                .append('}');
        return sb.toString();
    }

}

