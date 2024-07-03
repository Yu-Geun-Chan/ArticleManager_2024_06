package org.koreait;

import org.koreait.article.controller.ArticleController;
import org.koreait.article.controller.SystemController;
import org.koreait.article.entity.Article;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Container.init();

        new App().run();

        Container.close();
    }
}

