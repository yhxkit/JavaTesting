package com.example.javatest01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestClass13Test {
    @Test
    void create1(){
        TestClass01Test t = new TestClass01Test();
        assertNotNull(t);
        System.out.println("설정파일 테스트1 ");
    }

    @Test
    void create2(){
        TestClass01Test t = new TestClass01Test();
        assertNotNull(t);
        System.out.println("설정파일  테스트2 ");
    }

    @Test
    @Disabled
    void create3(){
        TestClass01Test t = new TestClass01Test();
        assertNotNull(t);
        System.out.println("설정파일 테스트3 - @Disabled라도 설정파일에서 실행하도록 세팅가능");
    }

    @BeforeAll
    static void beforeOnce(){
        System.out.println("모든 테스트를 실행하기 전에 딱 1번만 호출되는 @BeforeAll(반드시 static void 메서드로 작성)");
    }

    @AfterAll
    static void afterOnce(){
        System.out.println("모든 테스트를 실행한 후에 딱 1번만 호출되는 @AfterAll(반드시 static void 메서드로 작성)");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("각각의 테스트들을 실행하기 전에 한번씩 호출. 굳이 static일 필요없음");
    }

    @AfterEach
    void afterEach(){
        System.out.println("각각의 테스트들을 실행한 후에 한번씩 호출. 굳이 static일 필요없음");
    }

}