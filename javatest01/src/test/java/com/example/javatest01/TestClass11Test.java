package com.example.javatest01;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
        //이렇게 하면 테스트 인스턴스가 클래스 당 하나로 할당
class TestClass11Test {
//    @TestInstance를 사용하면 @BeforeAll / @afterAll 이 static일 필요가 없어짐

    int val = 0;

    @BeforeAll
    void beforeAllWithoutStatic() {
        System.out.println("Before All ");
        System.out.println(this);
    }

    @AfterAll
    void afterAllWithoutStatic() {
        System.out.println("After All");
        System.out.println(this);
    }

    @Test
    void test1() {
        val += 1;
        System.out.println("Test1 -> " + val);
        System.out.println(this);
    }

    @Test
    void test2() {
        val += 1;
        System.out.println("Test2 -> " + val);
        System.out.println(this);
    }


}