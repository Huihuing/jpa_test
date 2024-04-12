package com.example.jpa_Test.Article;

import org.springframework.stereotype.Component;

@Component
public class MYSQLDB implements MyDB{
    public void run() {
        System.out.println("MYSQLDB run");
    }
}
