package cn.hxh;

import cn.hxh.util.HH;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "encrypted";
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();
        System.out.println(HH.getPasswordsString(path, code));
    }
}
