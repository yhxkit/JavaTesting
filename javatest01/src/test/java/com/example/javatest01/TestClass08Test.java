package com.example.javatest01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.*;

class TestClass08Test {

    @RepeatedTest(10)
        //10번 반복하겠다
    void testRepeat() {
        System.out.println("10번 도는 테스트");
    }

    @RepeatedTest(10)
        //10번 반복하겠다
    void testRepeatWithIndex(RepetitionInfo repeatInfo) {
        System.out.println("테스트 인덱스 : " + repeatInfo);
        System.out.println(repeatInfo.getCurrentRepetition() + "/" + repeatInfo.getTotalRepetitions());
    }


    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{displayName} = {currentRepetition} / {totalRepetitions}")
        //리터럴처럼 test results 이름주기 가능

    void testRepeatWithName(RepetitionInfo repeatInfo) {
        System.out.println(repeatInfo.getCurrentRepetition() + "/" + repeatInfo.getTotalRepetitions());
    }


}