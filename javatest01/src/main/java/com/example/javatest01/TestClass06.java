package com.example.javatest01;

public class TestClass06 {
    // 테스트 태깅 : 모듈 / 프로필 등의 조건에 따라 해당 태그가 달린 테스트만 실행하기
    // intellij에서 설정을 해주어야 하는데, 상단 구동 드롭 리스트에서 edit configurations...에서 해당 테스트 클래스의 Test kind를 Tags로 변경
    // Test kind 아래의 Tag Expession에서, 테스트 메서드에 단 @Tag("문자열") 문자열을 입력해줄것
    // 이렇게 했을 때, 상단의 드롭박스에서 TAGS를 선택하고 버튼 클릭으로 실행해주어야 함..

    //터미널에서 mvnw test 하고 싶다면 pom.xml에서 프로파일을 설정하여, 프로파일 별로 실행해줄것
}
