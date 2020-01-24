package com.example.javatest01;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class TestClass03Test {

    @Test
    void assertTest1() {
        TestClass03 t = new TestClass03();
        assertNotNull(t);

        // case 1 : 일반 문자열로 메시지주기
        assertEquals(TestClass03Status.DRAFT, t.getStatus()
                , "TestClass03 생성시 status는 DRAFT일 것");

        // case 2 : 서플라이어를 사용한 메시지주기
        assertEquals(TestClass03Status.DRAFT, t.getStatus()
                , new Supplier<String>() {
                    @Override
                    public String get() {
                        return "TestClass03 생성시 status는 DRAFT일 것";
                    }
                });

        // case 3 : case 2를 람다 ㄱㄱ
        assertEquals(TestClass03Status.DRAFT, t.getStatus()
                , () -> "TestClass03 생성시 status는 DRAFT일 것");
    }


    @Test
    void assertTest2() {
        TestClass03 t1 = new TestClass03(-10);
        assertFalse(t1.getLimit() > 0, "최대 수치는 0보다 커야 한다");
    }

    @Test
    void assertTest3() {
        TestClass03 t1 = new TestClass03(-10);
        TestClass03 t2 = new TestClass03();

        assertAll(
                // assert문을 하나씩 쓰면 앞의 것이 깨졌을때 뒤의 것은 실행이 안됨. 이걸 한번에 확인하기 위한 assertAll
                // excutable 여러개를 assertAll의 파라미터로 전달하여 확인 가능
                () -> assertNotNull(t1),
                () -> assertNotNull(t2),
                () -> assertFalse(t1.getLimit() > 0, () -> "리밋은 0 이상일 것"),
                () -> assertNotEquals(TestClass03Status.ENDED, t2.getStatus(), () -> "상태값이 DRAFT일것")
        );
    }

    @Test
    void assertTest4() {
        assertAll(
                () -> assertDoesNotThrow(() -> new TestClass03("")),
                () -> assertThrows(RuntimeException.class, () -> new TestClass03("에러 발생시 통과!"))
        );

        RuntimeException ex = assertThrows(RuntimeException.class, () -> new TestClass03("에러 유발시켜 보기"));
        assertEquals("TestClass03 생성 중 익셉션 발생", ex.getMessage()); //익셉션에서 set한 메시지도 확인 가능
    }

    @Test
    void assert5() {
        //실행하는 코드가 반드시 특정 시간 이내에 완료되어야 할 때에 사용
        assertTimeout(Duration.ofSeconds(3), () -> new TestClass03()); // 새 TestClass03 생성은 3초 내로 끝나야한다

        //스레드 sleep으로 실패를 시켜보자..
        assertTimeout(Duration.ofMillis(100),
                () -> {
                    new TestClass03();
                   // Thread.sleep(1000); //이렇게 하면 실패가 되겠지..
                });
    }

}