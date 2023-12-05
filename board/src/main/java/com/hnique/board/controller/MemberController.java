package com.hnique.board.controller;

import com.hnique.board.dto.MemberForm;
import com.hnique.board.entity.Member;
import com.hnique.board.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
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
        log.info(form.toString());

        // DTO -> Entity 변환
        Member member = form.toEntity();
        log.info(member.toString());

        // Repository로 Entity를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "";
    }
}
