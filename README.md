# JavaTesting

## Junit 5 
### In project javatest01 

#### TestClass01 
 - 기본적인 테스트 알아보기
 
  1. Annotation
  + @Test
  + @BeforeAll
  + @AfterAll
  + @BeforeEach
  + @AfterEach
  + @Disabled
  
##### junit 5 사용시, 메인 클래스가 없어도 테스트 가능 > junit5 플랫폼의 실행 런처가 @Test가 붙은 메서드를 실행
##### TEST 클래스 생성 단축키 : ctrl shift t
##### TEST 메서드 실행 단축키 : ctrl shift f10
##### junit5 부터는 굳이 클래스나 테스트가 public일 필요가 없어짐 = 자바의 리플렉션을 사용하기 때문