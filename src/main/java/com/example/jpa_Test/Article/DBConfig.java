package com.example.jpa_Test.Article;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정 용
@Configuration
public class DBConfig {
    //객체 리턴 메서드
    //메서드가 리턴하는 객체를 bean 등록

    @Bean
    public MyDB myDB() {
        return new MemoryDB();
    }
}
