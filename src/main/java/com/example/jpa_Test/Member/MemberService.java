package com.example.jpa_Test.Member;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Member save(String name) {
        Member member = new Member();
        member.setName(name);

        return memberRepository.save(member);
    }
}
