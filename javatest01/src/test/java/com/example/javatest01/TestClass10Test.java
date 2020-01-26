package com.example.javatest01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass10Test {
    // Junit은 기본적으로 해당 테스트 메서드 하나를 실행할 때마다 새로운 클래스를 생성한다
    // 테스트들의 순서도 보장되지 않을 뿐더러, 각 테스트마다 새로운 객체를 생성해서 쓰므로 val은 모든 테스트에서 1
    // this 를 찍어보면 단위 테스트마다 다른 인스턴스를 사용함. 이는 테스트간의 의존성을 없애기 위한 것임
    // 단위 메서드마다 새 인스턴스를 사용하지 않고 클래스 당 하나만 만들기 위한 방법 : @TestInstance >> TestClass11 로..
    int val = 0;

    @Test
    void test1() {
        val += 1;
        System.out.println(val);
        System.out.println(this);
        // val = 1
    }

    @Test
    void test2() {
        val += 1;
        System.out.println(val);
        System.out.println(this);
        // val = 1
    }


}