package com.example.jpa_Test.Member;

import com.example.jpa_Test.Article.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;

//    @OneToMany
//    @JoinColumn(name="member_id")
//    List<Article> articles;

    // mappedBy 속성은 연관관계의 주인이 아닌 쪽에서 사용한다.
    // 관계의 주인??? -> 외래키를 가지고 있는 애가 주인(N쪽)
    // 양방향 관계에서는 관계의 주인에 적용된 것만 DB에 반영된다.
    // fetch 타입 2가지 - Lazy : 지연로딩, Eager : 즉시로딩
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        article.setMember(this);
        articles.add(article);
    }
}
