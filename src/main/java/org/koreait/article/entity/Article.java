package org.koreait.motivation.entity;

public class Article {
    private int id;
    private String title;
    private String body;
    private String regDate;

    public Article(int id, String title, String body, String regDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

}
