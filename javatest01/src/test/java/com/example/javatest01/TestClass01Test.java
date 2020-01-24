package com.example.javatest01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestClass01Test {
//junit 5 사용시, 메인 클래스가 없어도 테스트 가능 > junit5 플랫폼의 실행 런처가 @Test가 붙은 메서드를 실행
//    TEST 클래스 생성 단축키 : ctrl shift t
//    TEST 메서드 실행 단축키 : ctrl shift f10
// junit5 부터는 굳이 클래스나 테스트가 public일 필요가 없어짐 = 자바의 리플렉션을 사용하기 때문
    @Test
    void create1(){
        TestClass01Test t = new TestClass01Test();
        assertNotNull(t);
        System.out.println("테스트1 ");
    }

    @Test
    void create2(){
        TestClass01Test t = new TestClass01Test();
        assertNotNull(t);
        System.out.println("테스트2 ");
    }

    @Test
    @Disabled
    void create3(){
        TestClass01Test t = new TestClass01Test();
        assertNotNull(t);
        System.out.println("테스트3 - @Disabled를 사용하면 해당 테스트를 제외하고 테스트 가능~");
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