package com.example.javatest03.member;

import com.example.javatest03.domain.Member;
import com.example.javatest03.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}

