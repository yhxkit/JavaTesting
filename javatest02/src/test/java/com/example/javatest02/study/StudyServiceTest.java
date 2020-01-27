package com.example.javatest02.study;

import com.example.javatest02.domain.Member;
import com.example.javatest02.domain.Study;
import com.example.javatest02.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
//        StudyService를 생성하려면 StudyRepository와 MemberService를 인자로 받아야 하는데 둘 다 구현체가 없다..!
//        이럴 때에 Mock을 써보자
    @Mock
    StudyRepository studyRepository;

    @Mock
    MemberService memberService;



    //  --Mock 객체 생성하기

    @Test
    void createMockStudyServiceByMethod() {
//        StudyRepository studyRepository = Mockito.mock(StudyRepository.class);
//        MemberService memberService = Mockito.mock(MemberService.class);
//        일일이 Mockito.mock 쓰면 귀찮으니 Mockito를 static으로 import해서 쓰자...

        StudyRepository studyRepository1 = mock(StudyRepository.class);
        MemberService memberService1 = mock(MemberService.class);

        StudyService studyService = new StudyService(memberService1, studyRepository1);

        assertNotNull(studyService);
        System.out.println("mock()을 사용하여 성공적으로 Mock 객체를 생성했습니다~");
    }

    @Test
    void createMockStudyServiceByAnnotation(){
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
        System.out.println("@ExtendWith(MockitoExtension.class)과 @Mock을 사용하여 성공적으로 Mock 객체를 생성했습니다~");
    }

    @Test
    void createMockStudyServiceByParameterAnnotation(@Mock StudyRepository studyRepository3, @Mock MemberService memberService3){
        StudyService studyService = new StudyService(memberService3, studyRepository3);
        assertNotNull(studyService);
        System.out.println("@ExtendWith(MockitoExtension.class)과 @Mock을 파라미터로 사용하여 성공적으로 Mock 객체를 생성했습니다~");
    }


    // -- Mock 객체 생성에 성공했다면, 이제 Stubbing을 해보자

    @Test
    void testStubbing(){
        Optional<Member> memberOptional = memberService.findById(1L);
        //Mock을 사용하여 스터빙한 결과는 null / optional의 경우는 empty
        assertEquals(Optional.empty(), memberOptional);


        //memberService.findById(1L) 를 스터빙하여 결과를 새롭게 만들고 싶다면
        Member stubbedMember = new Member();
        stubbedMember.setId(1L);
        stubbedMember.setEmail("stubbing@stub.com");
        //when > thenReturn을 이용한다
        when(memberService.findById(any())) // any() 는 어떤값이든 무조건 아래의 값을 return.. 위에 넣은 1L로 넣어도 상관없음
                .thenReturn(Optional.of(stubbedMember));

        assertEquals("stubbing@stub.com", memberService.findById(any()).get().getEmail());

    }

    @Test
    void testStubThrowException(){
        //memberService.validate 사용 시 무조건 익셉션 나도록
        doThrow(new IllegalArgumentException()).when(memberService).validate(2L);
        assertThrows(IllegalArgumentException.class, () -> memberService.validate(2L));
        assertDoesNotThrow(() -> memberService.validate(any()));
    }


    @Test
    void testStubWithSeveralThen(){

        Member stubbedMember = new Member();
        stubbedMember.setId(1L);
        stubbedMember.setEmail("stubbing@stub.com");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(stubbedMember)) //처음에는 stubbedMember를 리턴
                .thenThrow(RuntimeException.class) //두번째에는 런타임 익셉션 발생
                .thenReturn(Optional.empty()); //세번째에는 empty 리턴

        assertNotNull(memberService.findById(any()));
        assertThrows(RuntimeException.class, () -> memberService.findById(any()));
        assertEquals(Optional.empty(), memberService.findById(any()));

    }



    // -- stubbing을 했다면 이제 Mock verify를 해보자

    @Test
    void testVerifyMock(){
        Study study = new Study();
        when(studyRepository.save(study)).thenReturn(study);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("test@test.com");
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createNewStudy(1L, study);
        //createNewStudy 내부에서 memberService.notify 를 사용함

        assertEquals(member.getId(), study.getOwnerId());

        verify(memberService, times(1)).notify(study); // study를 인자로 받는 notify가 딱 1번 호출되었는가?
        verify(memberService, times(1)).notify(member);
        verify(memberService, never()).validate(any()); //validate는 전혀 호출되지 않는가?

        //순서가 필요한 경우에도 사용 가능한 verify
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study); //study로 notify가 먼저 호출된 후에
        inOrder.verify(memberService).notify(member); // member로 notify가 호출되는가?

        verifyNoMoreInteractions(memberService); //이제 더 이상 memberService를 호출하지 않는가?
    }

}