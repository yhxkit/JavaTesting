package com.example.javatest01;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass06Test {


    @Test
    @Tag("Fast")
    void tagTest1(){
        System.out.println("태그 1 : Fast ");
    }


    @Test
    @Tag("Slow")
    void tagTest2(){
        System.out.println("태그 2 : Slow ");
    }

}