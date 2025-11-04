package com.innowise.arraytask.service;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;

public interface ArraySortService {

    void bubbleSort(CustomArray array) throws CustomArrayException;

    void selectionSort(CustomArray array) throws CustomArrayException;

    void insertionSort(CustomArray array) throws CustomArrayException;
}