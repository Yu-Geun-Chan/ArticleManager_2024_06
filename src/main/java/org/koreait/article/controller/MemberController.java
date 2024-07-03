package org.koreait.article.controller;

import org.koreait.Container;
import org.koreait.Util;
import org.koreait.article.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
    private int lastMemberId;
    private List<Member> members;
    private String loginId;
    private String loginPw;
    private String nickName;


    public MemberController() {
        lastMemberId = 0;
        members = new ArrayList<Member>();
        loginId = "";
        loginPw = "";
        nickName = "";
    }

    public void join() {
        System.out.println("== member join ==");

        while (true) {
            System.out.print("아이디 : ");
            loginId = Container.getScanner().nextLine().trim();

            if (loginId.isEmpty()) {
                System.out.println("아이디를 입력해주세요.");
                continue;
            }

            boolean isLoginIdDup = false;

            for (Member member : members) {
                if (loginId.equals(member.getLoginId())) {
                    isLoginIdDup = true;
                    break;
                }
            }
            if (isLoginIdDup == true) {
                System.out.printf("'%s'은(는) 이미 사용중인 아이디 입니다.\n", loginId);
                continue;
            }
            System.out.printf("%s은(는) 사용 가능한 아이디 입니다.\n", loginId);
            break;

        }
        while (true) {
            System.out.print("비밀번호 입력 : ");
            loginPw = Container.getScanner().nextLine().trim();

            if (loginPw.isEmpty()) {
                System.out.println("비밀번호를 입력해주세요.");
                continue;
            }

            System.out.print("비밀번호 확인 : ");
            String loginPwCheck = Container.getScanner().nextLine().trim();
            if (loginPwCheck.equals(loginPw) == false) {
                System.out.println("비밀번호가 맞지 않습니다.");
                continue;
            } break;
        }
        while (true) {
            System.out.print("닉네임 입력 : ");
            nickName = Container.getScanner().nextLine().trim();

            if (nickName.isEmpty()) {
                System.out.println("닉네임을 입력하세요.");
                continue;
            } break;
        }
        String regDate = Util.getNow();
        int id = lastMemberId + 1;
        lastMemberId++;
        Member member = new Member(id, regDate, loginId, loginPw, nickName);
        members.add(member);
        System.out.printf("[%s]님 회원가입을 환영합니다.\n", nickName);
    }

    public void makeTestDate() {
        System.out.println("== 테스트용 회원 데이터를 생성하였습니다. ==");
        for (int i = 1; i <= 3; i++) {
            members.add(new Member(i, Util.getNow(), "아이디" + i, "비밀번호" + i, "닉네임" + i));
        }
    }
}
