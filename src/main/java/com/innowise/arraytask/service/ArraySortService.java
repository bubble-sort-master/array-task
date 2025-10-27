package com.innowise.arraytask.service;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;

public interface ArraySortService {

    CustomArray bubbleSort(CustomArray array) throws CustomArrayException;

    CustomArray selectionSort(CustomArray array) throws CustomArrayException;

    CustomArray insertionSort(CustomArray array) throws CustomArrayException;
}