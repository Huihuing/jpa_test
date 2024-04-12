package com.example.jpa_Test;

import com.example.jpa_Test.Article.Article;
import com.example.jpa_Test.Article.ArticleRepository;
import com.example.jpa_Test.Article.MyresultDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BasicTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    @Transactional
    void test1() {
        // 월별 게시물 등록 수
    }

    @Test
    @Transactional
    void test2() {
        List<Article> articles = articleRepository.findByTitleLike("제목");
        for (Article article : articles) {
            System.out.println(article.getTitle());
        }
    }

    @Test
    @Transactional
    void test3() {
//        Object[] result = articleRepository.findByTitleAndContent();
//        Object[] result1 = (Object[])result[0];
//
//        System.out.println(result1[0]);
//        System.out.println(result1[1]);
//        System.out.println(result1[2]);

        List<MyresultDTO> dtoList = articleRepository.findTitleAndContent();
        for (MyresultDTO myresultDTO : dtoList) {
            System.out.println(myresultDTO.getId());
            System.out.println(myresultDTO.getTitle());
            System.out.println(myresultDTO.getContent());
        }

//      Object[] result = articleRepository.findByTitleAndContent();

//        Integer id = (Integer)result[0];
//        String title = (String)result[1];
//        String content = (String)result[2];
//
//        System.out.println("id = " + id);
//        System.out.println("title = " + title);
//        System.out.println("content = " + content);
    }

    @Test
    @Transactional
    void test4() {
        Dog d1 = new Dog();
        Cat c1 = new Cat();

        Object[] dogs = {d1, c1};
    }
}

class Animal {}

class Dog extends Animal {}

class Cat extends Animal {}