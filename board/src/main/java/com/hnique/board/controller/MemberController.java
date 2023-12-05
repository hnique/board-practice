package com.hnique.board.controller;

import com.hnique.board.dto.MemberForm;
import com.hnique.board.entity.Member;
import com.hnique.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    public String newJoinForm() {
        return "members/new";
    }

    @PostMapping("/join")
    public String joinMembers(MemberForm form) {
        // DTO -> Entity 변환
        Member member = form.toEntity();

        // Repository로 Entity를 DB에 저장
        memberRepository.save(member);

        return "";
    }
}
