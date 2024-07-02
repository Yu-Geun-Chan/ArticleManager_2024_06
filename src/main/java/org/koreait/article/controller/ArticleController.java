package org.koreait.motivation.controller;

import org.koreait.motivation.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    int lastId;
    List<Article> articles;

    public ArticleController() {
        lastId = 0;
        articles = new ArrayList<Article>();
    }

}
