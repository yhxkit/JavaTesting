package com.example.javatest01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(FindSlowTestExtensionFor14.class)
class TestClass14Test {
    // 시간이 오래걸리는 테스트인데 @CustomTagFor07Slow 가 안붙어 있는 단위 테스트를 실행하게 되면 붙이도록 권고하는 메시지 출력하기

    //1. 선언적인 방법
    @Test
    void testSlow1() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("클래스에 선언적으로 확장한 기능이 있으므로 @Slow~~ 사용 권고가 출력될 것 ");
    }

    @Test
    @CustomTagFor07Slow
    void testSlow2() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("slow 어노테이션이 붙어있으므로 얘는 그냥 넘어갈 것");
    }





}