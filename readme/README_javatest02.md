# JavaTesting
## Mockito

### In project javatest02




#### `Mockito 란?`
```
 - Mock을 지원하는 프레임워크. Mock을 만들고 검증하고 관리할 수 있음
 - Mock : 더미 객체라고 생각하면 될 것 같다.
```

#### `Spring Boot에서의 Mockito`
```
 - 부트를 사용하면 기본적으로 Mockito가 딸려 온다. 
 - 만약 dependency에서 mockito를 찾을 수 없다면? 
     : Junit5와 Mockito를 연동해주는 mockito-core 와 mockito-junit-jupiter 의존성을 추가하자  
        1. mockito-core : mockito의 기본 코어 기능
        2. mockito-junit-jupiter : Junit test에서 mock을 연동해서 사용할 수 있는 추가적인 Junit 확장 구현체 
 ```
