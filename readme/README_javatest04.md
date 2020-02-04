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

#### `들어가기에 앞서 도커부터 설치해보자`
```
System Requirements

1. Windows 10 64-bit: Pro, Enterprise, or Education (Build 15063 or later).
2. Hyper-V and Containers Windows features must be enabled.
3.The following hardware prerequisites are required to successfully run Client Hyper-V on Windows 10:
    - 64 bit processor with Second Level Address Translation (SLAT)
    - 4GB system RAM
    - BIOS-level hardware virtualization support must be enabled in the BIOS settings. For more information, see Virtualizatio     

>> 현재 Window 10 Home 을 사용하고 있는 상태이므로 Docker Tool Box 를 이용하여 설치 시도
 : 가상화 사용이 가능 상태임에도 불구하고, "This computer doesn't have VT-X/AMD-v enabled. Enabling it in the BIOS is mandatory" 오류로 실패. 
    원인을 찾지 못하여 다른 방법으로 도커를 설치해보기로 했다 
 ```

#### `Window 10 Home에서 Hyper-V 사용하기`
```
Window 10 Home 은 Hyper-V(일종의 VM인듯)를 사용할 수 없기 때문에 도커를 설치할 수 없음
그래서 Hyper-V를 강제로 설치해보기로 함 


 1) 하기의 내용으로 .bat 파일을 생성

    pushd "%~dp0"
    dir /b %SystemRoot%\servicing\Packages\*Hyper-V*.mum >hyper-v.txt
    for /f %%i in ('findstr /i . hyper-v.txt 2^>nul') do dism /online /norestart /add-package:"%SystemRoot%\servicing\Packages\%%i"
    del hyper-v.txt
    Dism /online /enable-feature /featurename:Microsoft-Hyper-V -All /LimitAccess /ALL
    pause

2) 관리자 권한으로 1)의 파일을 실행하여 Hyper-V를 설치 후 PC 재부팅. 
    이제 제어판\프로그램\프로그램 및 기능의 windows 기능 켜기/끄기에서 Hyper-v 항목을 확인할 수 있다 

3) 관리자 권한으로 regedit을 실행하여 컴퓨터\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion 의 EditionID를 Professional 로 변경

4) 최신 버전의 도커를 다운받아 인스톨해보자... 인스톨에 실패한다 

5) 인스톨을 시도한 도커 버전은 2.2.0 버전이었는데, 3)이 아닌 곳에서도 os를 확인하는 로직이 업데이트된 것으로 추정

6) 구버전의 도커를 인스톨해보자. 2.0.0 버전은 다행히도 regedit만 Professional로 수정하면 인스톨에 성공한다

7) 인스톨이 끝났다면 regedit은 원상복구 시켜둘 것

    ~~예시가 postgresql을 사용하길래 도커에서 postgresql 이미지 풀받아서 써보려는데...겁내 생소하고 어렵다... 내가 알던 db들하고 넘 다름..~~
```

***

#### StudyService

 - 이제 StudyServiceTest에서 ServiceRepository는 더 이상 Mock객체가 아닌 실제의 객체를 사용한다(@Autowired)
 - 이 때, 터미널에서 도커를 띄우고 db로 사용할 postgresql 컨테이너를 띄워놓아야 테스트가 성공하게 되며, 
 이를 위해 테스트용 설정 파일과 스크립트 파일을 별도로 작성해두어야 한다
 - 이 단점을 보완하기 위해 사용할 수 있는것이  [TestContainers.org](http://testcontainers.org/) 라는 라이브러리 
 
 **※목표 :  도커를 띄워두지 않아도 테스트를 실행할 때 자동으로 떴다가 테스트 종료시 자동으로 내려가는 테스트 컨테이너 만들기**

#### TestContainers 설치하기 
```
junit을 지원하는 테스트 컨테이너 의존성을 추가하면 됨 

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>1.12.4</version>
    <scope>test</scope>
</dependency>

→ 이제 @TestContainers라는 어노테이션을 사용할 수 있게 됨
```   


```
postgres 모듈설치 

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <version>1.12.4</version>
    <scope>test</scope>
</dependency>

여러 모듈을 제공하며, 각 모듈은 별도 설치 필요. 모듈을 설치해야 해당 db의 컨테이너를 띄울 수 있다
이제 테스트 클래스의 필드에 static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();를 설정해주자..
static으로 하지 않으면 매 메소드마다 새로운 컨테이너를 생성하므로 static해줄것
즉 인스턴스 필드로 사용하면 모든 테스트 마다 컨테이너를 재시작 하고, 스태틱 필드로 사용하면 클래스 내부 모든 테스트에서 동일한 컨테이너를 재사용한다.

```

##### in TestServiceTest

이제 @BeforeAll/@BeforeAfter를 사용하여 테스트 컨테이너를 올리고 내리면 됨  
다만, postgres가 설정파일에 설정해둔 url에 떠 있어야만 정상적으로 테스트 컨테이너를 올리고 내릴 수 있다는 것..!   
- 이걸 설정하려면?
    1. [https://www.testcontainers.org/modules/databases/](https://www.testcontainers.org/modules/databases/)에서 확인해보자....  
    2. jdbc:tc:mysql~~~ / jdbc:tc:postgresql~~~ 식으로 **tc**를 추가하면 된다 
    3. 설정파일에서 더 이상 url과 포트번호는 필요없다. 알아서 매칭해줌  
        - spring.datasource.url=jdbc:tc:postgresql:///studytest
    4. 설정파일에 다음 설정이 추가되어야 한다   
        - spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver


##### in TestService2Test

◎ 그런데.....!  
 이렇게 @BeforeAll/@AfterAll 로 일일이 해주지 않아도 어노테이션을 사용하여 해당 기능을 사용할 수 있다

- @Testcontainers 
    - JUnit 5 확장팩으로 테스트 클래스에 @Container를 사용한 필드를 찾아서 컨테이너 라이프사이클 관련 메소드를 실행해준다.

- @Container
    - 인스턴스 필드에 사용하면 모든 테스트 마다 컨테이너를 재시작 하고, 스태틱 필드에 사용하면 클래스 내부 모든 테스트에서 동일한 컨테이너를 재사용한다.

*** 

나머지 내용은 그냥 강좌로 확인하는 것이 나아 보임
##### - TestContainers의 추가적인 기능 설명 
##### - 스프링 테스트에서 컨테이너 정보 참조하기 
##### - TestContainers를 도커 Compose(도커 컨테이너 여러개를 동시에 띄워 사용할 경우 사용) 환경에서 사용하기 
