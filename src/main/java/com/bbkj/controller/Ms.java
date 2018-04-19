package com.bbkj.controller;

import com.bbkj.utils.ContrastList;

import javax.swing.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 * @author rrx
 */
public class Ms extends JFrame {
    private static JPanel root = new JPanel();
    private static JLabel jLabel = new JLabel();
    private String[] cipherText; // 密文
    private StringBuffer plainStr = new StringBuffer(" ");  // 明文字符串
    // 实例化对照表对象
    private ContrastList c = new ContrastList();

    private Map<Character, String> mapList = c.mapList;


    public static void main(String[] args) {

        System.out.println("************ 呼吸打字  **************");
        Ms m = new Ms();
        while (true) {
            m.init();
            m.decryption();
        }

    }


    private void ts(String s) {
        jLabel.setSize(200, 150);
        jLabel.setText(s);
        root.add(jLabel);
        add(root);
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 初始化
     */
    private void init() {
        System.out.println("请输入一段密文：");
        Scanner scanner = new Scanner(System.in);
        String next = "," + scanner.next();
        cipherText = null;
        cipherText = next.split(",");
    }

    /**
     * 解密
     */
    private void decryption() {
        for (int i = 1; i < cipherText.length; i++) {
            String tmp = cipherText[i];
            if (mapList.containsValue(tmp)) {
                for (Entry<Character, String> s : mapList.entrySet()) {
                    if (tmp.equals(s.getValue())) {
                        plainStr.append(s.getKey().toString());
                    }
                }
            }
        }
        ts(plainStr.toString().toLowerCase());
    }
}