package com.example.jpa_Test;

import com.example.jpa_Test.Article.Article;
import com.example.jpa_Test.Article.ArticleRepository;
import com.example.jpa_Test.Article.CountPerYmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class GroupingTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    void test1() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int randInt = random.nextInt(12);

            Article article = new Article();
            article.setTitle("제목" + i);
            article.setContent("내용" + i);
            
            article.setCreateDate(LocalDateTime.now().minusMonths(randInt));
            
            articleRepository.save(article);
        }
    }
    @Test
    void test2() {
        // 월별 게시물 등록 수
        List<CountPerYmDto> dtoList = articleRepository.findCountPerYm();
        for(CountPerYmDto dto : dtoList) {
            System.out.println(dto.getYm() + " : " + dto.getCnt());
        }
    }
}
