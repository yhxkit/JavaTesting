package com.example.javatest03.study;

import com.example.javatest03.member.MemberService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // @ExtendWith({SpringExtension.class}) 를 가지고 있음. 스프링부트에서 사용하는 모든 빈을 등록해줌
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class StudyServiceTest {

    @Mock
    MemberService memberService;
    //여전히 구현체가 없기 때문에 mock 객체를 사용함

    @Autowired
    StudyRepository studyRepository;
    //테스트 컨테이너를 사용하기 위해서 mock객체가 아니라 실제 리포지토리를 사용!!
    //test가 아닌 main 리소스의 설정파일에 있는 db를 사용하게 된다..

    static PostgreSQLContainer postgreSQLContainer
            = new PostgreSQLContainer()
                .withDatabaseName("studyTest"); //설정 파일의 db네임

    @BeforeAll
    static void beforeAll(){
        postgreSQLContainer.start();
        System.out.println(postgreSQLContainer.getJdbcUrl());
        // 설정파일에 설정해둔 url로 뜨지 않을것... 스프링에 설정해둔거지 postgres에 설정해둔게 아니니까
        // postgres와 스프링의 url을 매칭시켜야 한다
    }

    @AfterAll
    static void afterAll(){
        postgreSQLContainer.stop();
    }

    @Test
    void test1(){
        assertNotNull(studyRepository);
        System.out.println("postgre 조내 어려워..... mysql로 하면 안되나?");
    }





}