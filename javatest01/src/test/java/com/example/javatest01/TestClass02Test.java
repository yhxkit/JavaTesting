package com.example.javatest01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TestClass02Test {
    //@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // 이 클래스의 메서드들의 _ 를 치환해주게 됨
//    @DisplayNameGeneration() -> 클래스에 써도 되고, 메서드에 써도 됨. Test Results에 테스트 이름을 어떻게 보이게 할지 정함

        @Test
        @DisplayName("첫번째 테스트 메서드") //이렇게 이름 직접 지정해주기도 가능
        void create1_under(){
            TestClass01Test t = new TestClass01Test();
            assertNotNull(t);
            System.out.println("테스트1 ");
        }

        @Test
        void create2_under(){
            TestClass01Test t = new TestClass01Test();
            assertNotNull(t);
            System.out.println("테스트2 ");
        }

        @Test
        @Disabled
        void create3_under(){
            TestClass01Test t = new TestClass01Test();
            assertNotNull(t);
            System.out.println("테스트3 - @Disabled를 사용하면 해당 테스트를 제외하고 테스트 가능~");
        }

        @BeforeAll
        static void beforeOnce_under(){
            System.out.println("모든 테스트를 실행하기 전에 딱 1번만 호출되는 @BeforeAll(반드시 static void 메서드로 작성)");
        }

        @AfterAll
        static void afterOnce_under(){
            System.out.println("모든 테스트를 실행한 후에 딱 1번만 호출되는 @AfterAll(반드시 static void 메서드로 작성)");
        }

        @BeforeEach
        void beforeEach_under(){
            System.out.println("각각의 테스트들을 실행하기 전에 한번씩 호출. 굳이 static일 필요없음");
        }

        @AfterEach
        void afterEach_under(){
            System.out.println("각각의 테스트들을 실행한 후에 한번씩 호출. 굳이 static일 필요없음");
        }





}