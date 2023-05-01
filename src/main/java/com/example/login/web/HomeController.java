package com.example.login.web;

import com.example.login.doamin.member.Member;
import com.example.login.doamin.member.MemberRepository;
import com.example.login.web.argumentresolver.Login;
import com.example.login.web.login.SessionConst;
import com.example.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    SessionManager sessionManager = new SessionManager();

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(
            @Login Member loginMember, Model model) {


        // 세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }



}

/**
 * homeLogin V1
 */
//    @GetMapping("/")
//    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
//        log.info("memberId={}", memberId);
//        if (memberId == null) {
//            return "home";
//        }
//        //로그인
//        Member loginMember = memberRepository.findById(memberId);
//        if (loginMember == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//
//    }

    /**
     * homeLogin V2
     */
//    @GetMapping("/")
//    public String homeLoginV2(HttpServletRequest request, Model model) {
//
//        // 세션 관리자에 저장된 회원 정보 조회
//        Member member = (Member)sessionManager.getSession(request);
//
//        //로그인
//        if (member == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", member);
//        return "loginHome";
//    }

    /**
     * homeLogin V3
     */
//    @GetMapping("/")
//    public String homeLoginV3(
//            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
//
//
//        //세션을 찾고 세션에 들어있는 데이터를 찾는 과정
////        HttpSession session = request.getSession(false);
////        if (session == null) {
////            return "home";
////        }
////
////        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
////        위의 코드를 @SessionAttribute로 해결
//
//        // 세션에 회원 데이터가 없으면 home
//        if (loginMember == null) {
//            return "home";
//        }
//
//        // 세션이 유지되면 로그인으로 이동
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//    }