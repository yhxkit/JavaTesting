package com.example.javatest03.study;

import com.example.javatest03.member.MemberService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // @ExtendWith({SpringExtension.class}) 를 가지고 있음. 스프링부트에서 사용하는 모든 빈을 등록해줌

class StudyServiceTest {

    @Mock
    MemberService memberService;
    //여전히 구현체가 없기 때문에 mock 객체를 사용함

    @Autowired
    StudyRepository studyRepository;
    //테스트 컨테이너를 사용하기 위해서 mock객체가 아니라 실제 리포지토리를 사용!!





}