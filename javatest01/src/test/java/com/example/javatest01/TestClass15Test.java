package com.example.javatest01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

class TestClass15Test {
    // 시간이 오래걸리는 테스트인데 @CustomTagFor07Slow 가 안붙어 있는 단위 테스트를 실행하게 되면 붙이도록 권고하는 메시지 출력하기

    // 2. 프로그래밍 등록(필드로 등록)
    @RegisterExtension
    static FindSlowTestExtensionFor15 findSlowTestExtensionFor15 = new FindSlowTestExtensionFor15(1000L);

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