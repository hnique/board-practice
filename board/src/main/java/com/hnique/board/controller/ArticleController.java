package com.hnique.board.controller;

import com.hnique.board.dto.ArticleForm;
import com.hnique.board.entity.Article;
import com.hnique.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j // 로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 Repository 객체 주입(DI)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼 데이터를 DTO로 받기
        // System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        log.info(form.toString()); // 기존 println()문 대신 로깅 코드로 대체

        // 1. DTO를 Entity로 변환
        Article article = form.toEntity();
        // System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository로 Entity를 DB에 저장
        Article saved = articleRepository.save(article); // article Entity를 저장해 saved 객체에 반환
        // System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }
}
