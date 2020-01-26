# JavaTesting
## Junit 5 

### In project javatest01 

#### TestClass01 
 - 기본적인 테스트 알아보기
     * Annotation
        + @Test: 이 메서드를 테스트함
        + @BeforeAll: 전체 테스트를 실행하기 전에 한번만 실행. @TestInstance 사용하지 않을 경우엔 static일것.
         + @AfterAll: 전체 테스트 후에 한번만 실행. @TestInstance 사용하지 않을 경우엔 static일것.
         + @BeforeEach: 모든 테스트 전에 한번씩 실행
         + @AfterEach: 모든 테스트 후에 한번씩 실행
         + @Disabled: 이 테스트 실행하지 않음
  
###### junit 5 사용시, 메인 클래스가 없어도 테스트 가능 > junit5 플랫폼의 실행 런처가 @Test가 붙은 메서드를 실행
###### TEST 클래스 생성 단축키 : ctrl shift t
###### TEST 메서드 실행 단축키 : ctrl shift f10
###### junit5 부터는 굳이 클래스나 테스트가 public일 필요가 없어짐 = 자바의 리플렉션을 사용하기 때문


***


#### TestClass02
 - 테스트 메서드 명명하기
    * Annotation
        + @DisplayName
        + @DisplayNameGeneration

###### @DisplayName("테스트 이름 직접 지정하기") : 테스트 메서드에 사용
###### @DisplayNameGeneration() -> 테스트 클래스에 써도 되고, 테스트 메서드에 써도 됨. Test Results에 테스트 이름을 어떻게 보이게 할지 정함 
###### @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) : 이 클래스의 메서드들의 _ 를 치환해주게 됨


***


#### TestClass03
 - assertion 사용하기
      * assert*
        + assertEquals, assertNotNull, assertTrue, assertTimeout, assertThrow ...
        + assertAll : 내부의 모든 assert의 결과 보여줌 (원래 한 assert 실패시 다음 assert로 넘어가지 않음)
  

***


#### TestClass04
 - 특정 조건에서만 테스트하기 1
      * assume
        + assumeTrue, assumeFalse 
        + assumingThat


***


#### TestClass05
 - 특정 조건에서만 테스트하기 2
    * annotation
        + @Enabled*** 
        + @Disabled***


***

#### TestClass06
 - 테스트 태깅하기 
    * annotation
        + @Tag

###### 테스트 태깅 : 모듈 / 프로필 등의 조건에 따라 해당 태그가 달린 테스트만 실행하기
###### intellij에서 설정을 해주어야 하는데, 상단 구동 드롭 리스트에서 edit configurations...에서 해당 테스트 클래스의 Test kind를 Tags로 변경
###### Test kind 아래의 Tag Expession에서, 테스트 메서드에 단 @Tag("문자열") 문자열을 입력해줄것
###### 이렇게 했을 때, 상단의 드롭박스에서 TAGS를 선택하고 버튼 클릭으로 실행해주어야 함..

###### 터미널에서 mvnw test 하고 싶다면 pom.xml에서 프로파일을 설정하여, 프로파일 별로 실행해줄것
###### mvnw test -P 프로파일명


***


#### TestClass07
 - 커스터마이징 테스트 태그 
    * annotation
        + 상단 테스트 태깅을 메타 어노테이션으로 사용하여 커스텀 어노테이션화하기 

###### 테스트 태깅 커스터마이징하기
###### 테스트 폴더에서 커스텀 annotation을 생성해서 제공되는 메타 annotation을 이용하면 됨


*** 


#### TestClass08
 - 테스트 반복하기 
    * annotation
        + @RepeatedTest :  @RepeatedTest(10) 10번 반복하기
        + @RepeatedTest + 파라미터 RepetitionInfo
        + @RepeatedTest 와  @DisplayName 같이 쓰기 


*** 

#### TestClass09
 1. 파라미터를 이용해 테스트 반복하기 
    * annotation
        + @ParameterizedTest
        + @ValueSource 
        + @ParameterizedTest 와  @DisplayName 같이 쓰기 
        + @EmptySource : 빈 값 하나를 추가
        + @NullSource : null 값 하나를 추가 
        + @NullAndEmptySource : @NullSource + @EmptySource
        
        
 2. 파라미터를 이용해 반복해서 한개의 인자를 받는 생성자 객체 생성하기 
    * annotation
        + @ParameterizedTest
        + @ValueSource
        + @ConvertWith : SimpleArgumentConverter 를 상속하여 컨버터 생성해야 함
        
###### SimpleArgumentConverter 는 하나의 인자인 경우에만 적용됨


 3. 파라미터를 이용해 반복해서 여러 타입의 인자를 받는 생성자 객체 생성하기 
    ##### 첫번째 방법 : 각각의 인자를 파라미터로 받아 새 객체 생성하기 
    * annotation
        + @ParameterizedTest
        + @CsvSource
        
    ##### 두번째 방법 : 애그리게이트(여러개의 아규먼트를 받기) = ArgumentsAccessor로 여러인자를 하나로 조합
    * annotation
        + @ParameterizedTest
        + @CsvSource
        + @AggregateWith : ArgumentsAggregator 상속한 컨버터 생성해야 함
    
###### ArgumentsAggregator를 상속한 컨버터 클래스는 반드시 static inner class 이거나 public 일 것 
    
*** 


#### TestClass10 
 1.  테스트 인스턴스 1 
    * 테스트 인스턴스는 단위 테스트 당 하나씩 매번 새로 생성된다 

#### TestClass 11
 2. 테스트 인스턴스 2 
    * 테스트 인스턴스를 클래스 당 하나씩 생성하는 방법
    * annotation 
        + @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        
######  @TestInstance를 사용하면 @BeforeAll / @afterAll 이 static일 필요가 없어짐

*** 

#### TestClass12
 - 테스트 순서
    * annotation
        - @TestMethodOrder : @TestMethodOrder(MethodOrderer.OrderAnnotation.class)로 @Order으로 순서를 지정하도록 하는 orderer를 사용한다
        
###### @TestMethodOrder는 @TestInstance가 없어도 순서는 보장한다. 단위 테스트 간의 의존성을 보장하지 않을 뿐        

***

