package com.pluralsight;

import static org.junit.jupiter.api.Assertions.*;

class DealershipFileManagerTest {

    @org.junit.jupiter.api.Test
    void getDealership() {
        //arrange
        //act
        Dealership actual = DealershipFileManager.getDealership();
        //assert
        assertNotNull(actual);
    }
}