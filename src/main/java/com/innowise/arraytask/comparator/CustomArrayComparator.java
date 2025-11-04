package com.innowise.arraytask.comparator;

import com.innowise.arraytask.entity.CustomArray;
import com.innowise.arraytask.warehouse.ArrayWarehouse;

import java.util.Comparator;

public enum CustomArrayComparator implements Comparator<CustomArray> {
    ID {
        @Override
        public int compare(CustomArray arr1, CustomArray arr2) {
            return Long.compare(arr1.getId(), arr2.getId());
        }
    },

    ELEMENT_COUNT {
        @Override
        public int compare(CustomArray arr1, CustomArray arr2) {
            return Integer.compare(arr1.getElements().length, arr2.getElements().length);
        }
    },

    SUM {
        @Override
        public int compare(CustomArray arr1, CustomArray arr2) {
            ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
            var params1 = warehouse.getArrayParameters(arr1.getId());
            var params2 = warehouse.getArrayParameters(arr2.getId());

            if (params1 == null && params2 == null) return 0;
            if (params1 == null) return -1;
            if (params2 == null) return 1;

            return Integer.compare(params1.getSum(), params2.getSum());
        }
    };
}