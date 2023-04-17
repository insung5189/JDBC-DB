package org.example;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("명령어를 입력하세요 : ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("등록")) {
                System.out.println("== 게시물 등록 ==");
            }
        }

    }
}
