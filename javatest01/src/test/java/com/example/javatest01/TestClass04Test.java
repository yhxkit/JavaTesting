package com.example.javatest01;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class TestClass04Test {

    @Test
    void assumeTest1() {
        //우선 환경변수를 가져와보자..
     /*   Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s = %s%n", envName, env.get(envName));
        }*/
        //시스템 환경변수에 ENV = LOCAL 이라고 설정해보자(인텔리제이도 재구동 필요)


        // env가 local 일때에만 이 테스트를 실행시키기로 하면
        assumeTrue("local".equalsIgnoreCase(System.getenv("ENV")));
        System.out.println("ENV가 LOCAL입니다.");
        assertTrue(1 > 0, () -> "이 코드는 조건에 만족하므로 실행(true라 메시지가 출력되지 않을 뿐)");

        //만일 조건을 충족하지 못한다면
        assumeTrue("release".equalsIgnoreCase(System.getenv("ENV")), () -> "local 환경이 아닙니다");
        System.out.println("ENV가 LOCAL이 아닙니다.");
        assertTrue(1 == 0, () -> "이 코드는 조건에 만족하지 않으므로 실행되지 않음");

    }

    @Test
    void assumeTest2() {
        // env가 local 일때에만 이 테스트를 실행시키기로 하면
        assumingThat("local".equalsIgnoreCase(System.getenv("ENV")),
                () -> {
                    System.out.println("ENV가 LOCAL입니다.");
                    TestClass03 t = new TestClass03(10);
                    assertTrue(t.getLimit() > 0);
                });
        // env가 release 일떄에만 이 테스트를 실행시키기로 하면
        assumingThat("release".equalsIgnoreCase(System.getenv("ENV")),
                () -> {
                    System.out.println("ENV가 Release입니다.");
                    TestClass03 t = new TestClass03(10);
                    assertTrue(t.getLimit() > 0);
                });


    }

}