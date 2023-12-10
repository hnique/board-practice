package com.hnique.board.controller;

import com.hnique.board.dto.ArticleForm;
import com.hnique.board.entity.Article;
import com.hnique.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

        // 3. 상세 페이지로 리다이렉트
        return "redirect:/articles/" + saved.getId();
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. Entity를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터 값을 갱신하기
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그

        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); // id값으로 데이터를 찾을 때 해당 id값이 없으면 null을 저장

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList =  articleRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 반환하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String Edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 반환하기
        return "articles/edit";
    }
}
