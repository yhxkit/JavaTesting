package com.example.javatest01;

public class TestClass13 {
    // 테스트의 설정 파일 (위치 프로젝트 src > test > resources 디렉토리 생성하여 설정 파일 넣기)
    // 그리고 테스트 리소스 디렉토리를 인텔리제이에서 설정해주어야 함 : projectSetting > Modules > Test Resources를 적용

// 모든 테스트 클래스에 라이프 사이클 @TestInstance적용하기 : junit.jupiter.testinstance.lifecycle.default = per_class
// 확장팩 자동 감지 기능(디폴트는 false) : junit.jupiter.extensions.autodetection.enabled = true
// @Disable 무시하고 테스트 실행하기 : junit.jupiter.conditions.deactivate = org.junit.*DisabledCondition
//테스트명명 패턴 전략 : junit.jupiter.displayname.generator.default = org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores


}
