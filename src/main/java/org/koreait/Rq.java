package org.koreait;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String head = "";
    private String body = "";
    private String tail = "";

    public Rq(String cmd) {
        String[] cmdBits = cmd.split(" ");

        if (cmdBits.length == 2) {
            this.head = cmdBits[0];
            this.body = cmdBits[1];
        } else if (cmdBits.length == 3) {
            this.head = cmdBits[0];
            this.body = cmdBits[1];
            this.tail = cmdBits[2];
        } else this.head = cmd;
    }
    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getTail() {
        return tail;
    }

}
