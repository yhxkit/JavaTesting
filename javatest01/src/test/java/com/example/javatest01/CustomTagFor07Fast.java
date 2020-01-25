package com.example.javatest01;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //이 annotation은 메서드에서 사용
@Retention(RetentionPolicy.RUNTIME) //전역 : 런타임. 이 annotation을 사용하는 코드는 이 annotation의 정보를 runtime까지 유지해야 함
//이 아래로 커스텀 어노테이션이 사용할 junit의 메타 어노테이션들..
@Test
@Tag("Fast")
public @interface CustomTagFor07Fast {
}
