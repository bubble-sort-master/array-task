package com.innowise.arraytask.warehouse;

import com.innowise.arraytask.entity.ArrayParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ArrayWarehouse {
    private static final Logger logger = LogManager.getLogger(ArrayWarehouse.class);
    private static ArrayWarehouse instance;
    private Map<Long, ArrayParameters> parametersMap;

    private ArrayWarehouse() {
        parametersMap = new HashMap<>();
        logger.debug("ArrayWarehouse instance created");
    }

    public static ArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayWarehouse();
        }
        return instance;
    }

    public void putArrayParameters(long arrayId, ArrayParameters parameters) {
        parametersMap.put(arrayId, parameters);
        logger.debug("ArrayParameters stored for array ID: {}", arrayId);
    }

    public ArrayParameters getArrayParameters(long arrayId) {
        ArrayParameters params = parametersMap.get(arrayId);
        if (params == null) {
            logger.debug("ArrayParameters not found for array ID: {}", arrayId);
        }
        return params;
    }

    public void removeArrayParameters(long arrayId) {
        if (parametersMap.remove(arrayId) != null) {
            logger.debug("ArrayParameters removed for array ID: {}", arrayId);
        }
    }

}