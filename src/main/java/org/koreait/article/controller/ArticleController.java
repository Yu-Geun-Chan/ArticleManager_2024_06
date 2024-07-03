package org.koreait.article.controller;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.Util;
import org.koreait.article.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    int lastId;
    List<Article> articles;

    public ArticleController() {
        lastId = 0;
        articles = new ArrayList<Article>();
    }

    public void write() {
        System.out.println("== article write ==");

        int id = lastId + 1;
        System.out.print("제목 : ");
        String title = Container.getScanner().nextLine();
        String regDate = Util.getNow();
        String updateDate = regDate;
        System.out.print("내용 : ");
        String body = Container.getScanner().nextLine();
        lastId++;

        System.out.printf("%d번 글이 생성되었습니다.\n", lastId);


        Article article = new Article(id, regDate, updateDate, title, body);

        articles.add(article);
    }

    public void list(Rq rq) {
        if (articles.size() == 0) {
            System.out.println("게시글이 존재하지 않습니다.");
            return;
        }

        String searchWord = rq.getTail();

        List<Article> relevantArticles = articles;

        if (searchWord.length() > 0) {
            System.out.println("검색어 : " + searchWord);

            relevantArticles = new ArrayList<Article>();

            for (Article article : articles) {
                if (article.getTitle().contains(searchWord)) {
                    relevantArticles.add(article);
                }
            }
            if (relevantArticles.size() == 0) {
                System.out.println("검색 결과가 없습니다.");
                return;
            }
        }

        System.out.println("== article list ==");
        System.out.println(" 번호  /    날짜       /  제목    /  내용     ");
        System.out.println("=".repeat(50));

        for (int i = relevantArticles.size() - 1; i >= 0; i--) {
            Article article = relevantArticles.get(i);
            if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                System.out.printf("  %d   /  %s    /  %s    /   %s     \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
            } else
                System.out.printf("  %d   /  %s    /  %s    /   %s     \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
        }
    }

    public void detail(Rq rq) {
        System.out.println("== article detail ==");

        int id;

        try {
            id = Integer.parseInt(rq.getTail());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            return;
        }

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 없습니다.\n", id);
            return;
        }

        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("작성날짜 : " + foundArticle.getRegDate());
        System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getBody());
    }

    public void delete(Rq rq) {
        System.out.println("== article delete ==");

        int id;

        try {
            id = Integer.parseInt(rq.getTail());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            return;
        }

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 없습니다.\n", id);
            return;
        }

        articles.remove(foundArticle);

        System.out.printf("%d번 게시글이 삭제되었습니다\n", id);

    }

    public void modify(Rq rq) {
        System.out.println("== article modify ==");

        int id;

        try {
            id = Integer.parseInt(rq.getTail());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            return;
        }

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 없습니다.\n", id);
            return;
        }

        System.out.printf("기존 제목 : %s \n", foundArticle.getTitle());
        System.out.printf("기존 내용 : %s \n", foundArticle.getBody());
        System.out.printf("작성 날짜 : %s \n", foundArticle.getRegDate());
        System.out.printf("제목 : ");
        String newTitle = Container.getScanner().nextLine();
        System.out.printf("내용 : ");
        String newBody = Container.getScanner().nextLine();

        foundArticle.setTitle(newTitle);
        foundArticle.setBody(newBody);
        foundArticle.setRegDate(Util.getNow());

        System.out.printf("%d번 게시글이 수정되었습니다.\n", foundArticle.getId());
    }

    public void makeTestData() {
        System.out.println("== 테스트용 게시물 데이터를 생성하였습니다. ==");
        for (int i = 1; i <= 3; i++) {
            articles.add(new Article(++lastId, Util.getNow(), Util.getNow(),"제목" + i, "내용" + i));
        }
    }

    private Article getArticleById(int id) {
        Article foundArticle;
        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                return foundArticle;
            }
        }
        return null;
    }
}
