package com.example.javatest01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//comparator를 넘겨주듯 orderer를 넘겨주면 순서를 보장하게 된다
//여기서는 order 라는 어노테이션으로 순서를 지정하도록 하는 orderer를 사용한다
//    TestMethodOrder는 TestInstance가 없어도 순서는 보장함.. 단위 테스트 간의 의존성을 보장하지 않을 뿐
class TestClass12Test {



    @Order(3) //beforeAll에는 주면 before에 한번 실행하고 order 순서일 때 또 한번 실행됨
    @Test
    @BeforeAll
    void test(){
        System.out.println("Before");
    }

    @Order(2)
    @Test
    void test1(){
        System.out.println("1");
    }

    @Order(1)
    @Test
    void test2(){
        System.out.println("2");
    }

    @Order(4)
    @Test
    void test3(){
        System.out.println("3");
    }

}