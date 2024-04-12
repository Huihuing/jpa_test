package com.example.jpa_Test.Article;

import org.springframework.stereotype.Component;

@Component
public class MemoryDB implements MyDB {
    public void run() {
        System.out.println("MemoryDB run");
    }
}
