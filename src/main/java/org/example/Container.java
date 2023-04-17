package org.example;

import java.util.Scanner;

public class Container {
    public static Scanner scanner;

    static { // static키워드의 생성자함수
        scanner = new Scanner(System.in);
    }
    public static void close() {
        scanner.close();
    }
}
