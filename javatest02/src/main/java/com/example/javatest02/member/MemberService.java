package com.example.javatest02.member;

import com.example.javatest02.domain.Member;
import com.example.javatest02.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}

