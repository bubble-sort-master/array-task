package com.innowise.arraytask.service;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.exception.CustomArrayException;

public interface ArrayService {
    int findMin(CustomArray array) throws CustomArrayException;
    int findMax(CustomArray array) throws CustomArrayException;
    CustomArray replaceValues(CustomArray array, int valueToReplace, int newValue) ;
    CustomArray replaceAtIndex(CustomArray array, int index, int newValue) throws CustomArrayException;
    int calculateAverage(CustomArray array) throws CustomArrayException;
    int calculateSum(CustomArray array) throws CustomArrayException;
    int countPositive(CustomArray array);
    int countNegative(CustomArray array);
}
