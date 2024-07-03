package org.koreait;

import org.koreait.article.controller.ArticleController;
import org.koreait.article.controller.MemberController;
import org.koreait.article.controller.SystemController;

public class App {

    public void run() {

        ArticleController articleController = new ArticleController();
        SystemController systemController = new SystemController();
        MemberController memberController = new MemberController();

        System.out.println("== Article Manager execution ==");

        articleController.makeTestData();
        memberController.makeTestDate();


        while (true) {
            System.out.print("명령어) ");
            String cmd = Container.getScanner().nextLine().trim();
            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요.");
                continue;
            }

            Rq rq = new Rq(cmd);

            if (rq.getHead().equals("exit")) {
                systemController.exit();
                break;
            } else if (rq.getHead().equals("article") && rq.getBody().equals("write")) {
                articleController.write();
            } else if (rq.getHead().equals("article") && rq.getBody().startsWith("list")) {
                articleController.list(rq);
            } else if (rq.getHead().equals("article") && rq.getBody().startsWith("detail")) {
                articleController.detail(rq);
            } else if (rq.getHead().equals("article") && rq.getBody().startsWith("delete")) {
                articleController.delete(rq);
            } else if (rq.getHead().equals("article") && rq.getBody().startsWith("modify")) {
                articleController.modify(rq);
            } else if (rq.getHead().equals("member") && rq.getBody().equals("join")) {
                memberController.join();
            } else if (rq.getHead().equals("member") && rq.getBody().equals("login")) {
                memberController.logIn();
            } else if (rq.getHead().equals("member") && rq.getBody().equals("logout")) {
                memberController.logOut();
            }
            else System.out.println("올바르지 않은 명령어 입니다.");
        }

    }
}
