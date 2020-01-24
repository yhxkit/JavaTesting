package com.example.javatest01;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TestClass03 {
//    assertion 사용하기


    private TestClass03Status status = TestClass03Status.DRAFT;
    private int limit;
    private String expectEmptyString;

    public TestClass03(int limit){
        this.limit = limit;
    }

    public TestClass03(String expectEmptyString){
        if(!expectEmptyString.equals("")){
            throw new RuntimeException("TestClass03 생성 중 익셉션 발생");
        }
        this.expectEmptyString = expectEmptyString;
    }



}
