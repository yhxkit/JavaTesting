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

```

***

#### StudyService

**① 이제 StudyServiceTest에서 ServiceRepository는 더 이상 Mock객체가 아닌 실제의 객체를 사용한다(@Autowired)**
   