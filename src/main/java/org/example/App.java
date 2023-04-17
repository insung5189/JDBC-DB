package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = Container.scanner;

        List<Article> articles = new ArrayList<>();

        int articleLastId = 0;

        while (true) {
            System.out.printf("명령어를 입력하세요 : ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("등록")) {
                System.out.println("== 게시물 등록 ==");
                System.out.printf("제목 : ");
                String title = sc.nextLine().trim();
                System.out.printf("내용 : ");
                String body = sc.nextLine().trim();

                int id = ++articleLastId;


                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";

                    conn = DriverManager.getConnection(url, "root", "");

                    String sql = "INSERT INTO article";
                    sql += " SET regDate = NOW()";
                    sql += ", updateDate = NOW()";
                    sql += ", title = 'testTitle'";
                    sql += ", `body` = 'testBody'";

                    pstmt = conn.prepareStatement(sql);
                    int affectedRows = pstmt.executeUpdate();

                    System.out.println("affectedRows : " + affectedRows);

//                    System.out.println("연결 성공");

                } catch (SQLException e) {
                    System.out.println("에러 : " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("드라이버 로딩실패");
                } finally {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }




                Article article = new Article(id, title, body);
                articles.add(article);


                System.out.println("생성된 게시물 객체 : " + article);
                System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);


            } else if (cmd.equals("목록")) {
                System.out.println("=====목록=====");

                if (articles.isEmpty()) {
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }
                System.out.println("번호 / 제목");
                for (Article article : articles) {
                    System.out.printf("%d / %s\n", article.id, article.title);
                }

            } else if (cmd.equals("종료")) {
                System.out.println("시스템이 종료되었습니다.");
                break;

            } else {
                System.out.println("명령어를 확인해주세요.");
            }
        }
        sc.close();
    }
}
