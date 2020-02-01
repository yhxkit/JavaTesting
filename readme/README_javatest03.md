# JavaTesting
## Docker And Test (TestContainers)

### In project javatest03


#### `TestContainers 소개`
```
 테스트에서 도커 컨테이너를 실행할 수 있게 해주는 라이브러리 
 http://www.testcontainers.org

- 장점 
    1. 테스트 실행 시 별도의 DB를 설정할 필요 없음
    2. 실 서비스에 가까운 테스트가 가능

- 단점 
    1. 테스트가 느려진다
```

#### `Spring Boot에서의 Mockito`
```
 - 부트를 사용하면 기본적으로 Mockito가 딸려 온다. 
 - 만약 dependency에서 mockito를 찾을 수 없다면? 
     : Junit5와 Mockito를 연동해주는 mockito-core 와 mockito-junit-jupiter 의존성을 추가하자  
        1. mockito-core : mockito의 기본 코어 기능
        2. mockito-junit-jupiter : Junit test에서 mock을 연동해서 사용할 수 있는 추가적인 Junit 확장 구현체 
 ```

***

#### StudyService

**① Mock 객체 생성하기**
   - 테스트하려는 서비스의 의존성으로 구현체가 없는 객체를 사용하려 할 때에(repository, service interface...) Mock을 사용하기 적절
   - 이때에 테스트하려는 서비스가 의존하는 객체들(StudyRepository, MemberService)을 Mocking하면 됨
    
        * Mock 객체 생성하는 방법 세 가지
            1. 메서드 사용하기 : Mockito.mock(Mock으로 생성하려는 객체)
            2. 어노테이션 사용하여 필드값으로 만들기 : @Mock과 @Mock을 처리해줄 @ExtendWith(MockitoExtension.class)을 이용해 Mock으로 생성할 객체를 필드로 주기 
            3. 어노테이션을 사용하여 파라미터로 만들기 : @Mock과 @Mock을 처리해줄 @ExtendWith(MockitoExtension.class)을 이용해 Mock으로 생성할 객체를 사용할 메서드의 인자로 넘김
            
#            
**② Mock 객체 Stubbing 하기 (Mock 객체의 행위 관리 / 검증)**

   * 모든 Mock 객체는 더미 객체답게 빈 값을 반환한다
        1. null, Optional.empty, 빈 콜렉션 등...
        2. void의 경우 아무일도 발생하지 않는다 
        
   * 여기서 Mock 객체가 빈 값이 아니라 어떤 특정 값을 반환하도록 지정하는 것이 Stubbing             
        1. when(스터빙할 메서드).thenReturn(원하는 결과값) 
        
#      
**③ Mock 객체 verify 하기**

   * Stubbing을 하기 어려운 Mock 객체를 검증하기 위해 사용한다
   * 예를 들자면, 해당 Mock 객체의 특정 메서드가 호출됐는가? 됐다면 몇번 호출됐는가? 와 같은 내용
        1. verify() : 몇번 호출되었는지 등..
        2. InOrder :  순서대로 실행되는지
        3. verifyNoMoreInteractions : 더 이상 호출하지 않는지 

#
         
- Mockito가 지원하는 Behavior Driven Development 스타일의 API 

    : 앱이 어떻게 행동해야하는지에 대한 공통된 이해를 구성하는 방법. TDD에서 창안
    
    : Given(service, repository, 객체 등) > When(Given의 행위) > Then(When의 결과) 의 플로우로 테스트를 진행
   
    :: DBBMockito 패키지를 사용하여 해당 코드에서 사용한 when = given으로, times = should 로 변경하면 BDD 스타일이 됩니다
    