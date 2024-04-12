package com.example.jpa_Test.Article.Tag;

import com.example.jpa_Test.Article.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

//    @ManyToMany(mappedBy = "tagList")
//    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    private List<ArticleTag> articleTags = new ArrayList<>();
}
