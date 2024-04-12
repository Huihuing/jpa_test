package com.example.jpa_Test.Article.Tag;

import com.example.jpa_Test.Article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Integer> {
    void deleteByArticle(Article article);
}
