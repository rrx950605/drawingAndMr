package com.bbkj.controller;

import javax.swing.*;

class Ts extends JFrame {

    public Ts(StringBuffer s,JPanel root,JLabel jLabel) {
        super("Ts");
        jLabel.setText(s.toString());
        root.add(jLabel);
        add(root);
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        System.out.println("s");
    }
}