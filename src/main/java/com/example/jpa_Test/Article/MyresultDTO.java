package com.example.jpa_Test.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static org.apache.coyote.http11.Constants.a;

// 데이터베이스 저장할 건 아닌데, 자바 프로그램 내에서 특별한 형태로 데이터를 담아두고 싶을 때
// 데이터 담아두는 전용 객체 => DTO
@Getter
@Setter
@AllArgsConstructor
public class MyresultDTO {
    private int id;
    private String title;
    private String content;
}
