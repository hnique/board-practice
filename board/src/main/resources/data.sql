-- article 테이블에 데이터 추가
INSERT INTO article(title, content) VALUES('가가가', '111');
INSERT INTO article(title, content) VALUES('나나나', '222');
INSERT INTO article(title, content) VALUES('다다다', '333');

INSERT INTO article(title, content) VALUES('당신의 인생 영화는?', '댓글 고');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?', '댓글 고고고');

-- 4번 게시글에 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Kim', '아이엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Lee', '쇼생크 탈출');
-- 5번 게시글에 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Kim', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Lee', '초밥');
-- 6번 게시글에 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Park', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Kim', '유튜브');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Lee', '독서');

