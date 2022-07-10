package com.slepnev.stockphoto;

import com.slepnev.stockphoto.temp.Test;

public class Runner {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Test.photographerSaveTest();
        }
        Test.photographerFindAllTest();
        var photographerEntity = Test.photographerFindByIdTest(1);
        Test.photographerUpdateTest(photographerEntity);
        Test.photographerDeleteTest(1);
    }
}