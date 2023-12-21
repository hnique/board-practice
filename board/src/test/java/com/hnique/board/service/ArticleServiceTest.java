package com.hnique.board.service;

import com.hnique.board.dto.ArticleForm;
import com.hnique.board.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; // 앞으로 사용할 클래스 패키지를 예비로 임포트 해놓은 것

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트 (스프링 부트가 관리하는 다양한 객체를 주입받을 수 있음)
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test // 해당 메서드가 테스트 코드임을 선언
    void index() {
        // 1. 예상 데이터 작성하기
        Article a = new Article(1L, "가가가", "111");
        Article b = new Article(1L, "나나나", "222");
        Article c = new Article(1L, "다다다", "333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 2. 실제 데이터 획득하기
        List<Article> articles = articleService.index();

        // 3. 예상 데이터와 실제 데이터 비교해 검증하기
        // assertEquals : JUnit에서 제공하는 메서드, 예상 데이터(x)와 실제 데이터(y)를 비교해 일치하면 테스트를 통과시킴
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가", "111");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "가가가";
        String content = "111";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(1L, title, content);

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 3L;
        String title = "다다다";
        String content = "333";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);
    }
}