package com.example.login;

import com.example.login.doamin.member.Member;
import com.example.login.doamin.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {


    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스트");

        memberRepository.save(member);
    }

}