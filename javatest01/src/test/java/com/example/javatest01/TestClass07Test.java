package com.example.javatest01;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass07Test {

    //커스텀 어노테이션으로 TestClas06Test와 동일한 기능을 하게 된다

    @CustomTagFor07Fast
    void tagTest1(){
        System.out.println("커스텀 태그 1 : Fast ");
    }

    @CustomTagFor07Slow
    void tagTest2(){
        System.out.println("커스텀 태그 2 : Slow ");
    }


}