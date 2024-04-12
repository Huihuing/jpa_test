package com.example.jpa_Test.Article;

import com.example.jpa_Test.Article.Tag.ArticleTag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.jpa_Test.Member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    // 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @ManyToMany // 실제로는 이렇게 안함.
//    @JoinColumn(
//            name = "article_tag",
//            joinColumns = @JoinColumn(name = "article.id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private List<Tag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "article", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<ArticleTag> articleTags = new ArrayList<>();

    private LocalDateTime createDate;

}
