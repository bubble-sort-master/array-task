package com.innowise.arrraytask.warehouse;

import com.innowise.arraytask.entity.ArrayParameters;
import com.innowise.arraytask.warehouse.ArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayWarehouseTest {

    private ArrayWarehouse warehouse;
    private static final long ARRAY_ID = 1L;
    private static final ArrayParameters PARAMETERS =
            new ArrayParameters(10, 2.5, 0, 5, 4);

    @BeforeEach
    void setUp() {
        warehouse = ArrayWarehouse.getInstance();
        warehouse.removeArrayParameters(ARRAY_ID);
    }

    @Test
    void givenParameters_whenPut_thenCanRetrieve() {
        warehouse.putArrayParameters(ARRAY_ID, PARAMETERS);
        ArrayParameters actual = warehouse.getArrayParameters(ARRAY_ID);
        ArrayParameters expected = PARAMETERS;
        assertEquals(expected, actual);
    }

    @Test
    void givenNoParameters_whenGet_thenReturnNull() {
        ArrayParameters actual = warehouse.getArrayParameters(ARRAY_ID);
        assertNull(actual);
    }

    @Test
    void givenParameters_whenRemove_thenNoLongerExists() {
        warehouse.putArrayParameters(ARRAY_ID, PARAMETERS);
        warehouse.removeArrayParameters(ARRAY_ID);
        ArrayParameters actual = warehouse.getArrayParameters(ARRAY_ID);
        assertNull(actual);
    }
}
