package com.example.jpa_Test.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// DI = 의존성 주입
@Component
public class ArticleController {

    @Autowired
    MyDB memoryDB;

    public void test() {
        memoryDB.run();
    }
}
