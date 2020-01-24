package com.example.javatest01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.*;

class TestClass05Test {

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "LOCAL")
    void testOnLocalEnv(){
        System.out.println("local에서 실행됩니다");
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = "RELEASE")
    void doNotTestOnReleaseEnv(){
        System.out.println("release에서 실행되지 않습니다");
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    void testInCaseOfWindowsOrMac(){
        System.out.println("os가 윈도우 또는 맥인 경우 실행됩니다");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void doNotTestOnWindows(){
        System.out.println("os가 윈도우면 실행되지 않습니다.");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testOnJRE8(){
        System.out.println("JRE 8일 때에만 실행됩니다");
    }


}