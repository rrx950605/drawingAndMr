package com.bbkj.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rrx
 */
public class ContrastList {
    // 摩尔斯编码表集合
    public Map<Character, String> mapList = new HashMap<Character, String>();

    public ContrastList() {
        /**
         * 0短1长
         */
        mapList.put('A', "01");
        mapList.put('B', "1000");
        mapList.put('C', "1010");
        mapList.put('D', "100");
        mapList.put('E', "0");
        mapList.put('F', "0010");
        mapList.put('G', "110");
        mapList.put('H', "0000");
        mapList.put('I', "00");
        mapList.put('J', "0111");
        mapList.put('K', "101");
        mapList.put('L', "0100");
        mapList.put('M', "11");
        mapList.put('N', "10");
        mapList.put('O', "111");
        mapList.put('P', "0110");
        mapList.put('Q', "1101");
        mapList.put('R', "010");
        mapList.put('S', "000");
        mapList.put('T', "1");
        mapList.put('U', "001");
        mapList.put('V', "0001");
        mapList.put('W', "011");
        mapList.put('X', "1001");
        mapList.put('Y', "1011");
        mapList.put('Z', "1100");

        /* 数字电码019 */
        mapList.put('0', "11111");
        mapList.put('1', "01111");
        mapList.put('2', "00111");
        mapList.put('3', "00011");
        mapList.put('4', "00001");
        mapList.put('5', "00000");
        mapList.put('6', "10000");
        mapList.put('7', "11000");
        mapList.put('8', "11100");
        mapList.put('9', "11110");

        /* 标点符号*/
        mapList.put(',', "110011"); // ,逗号
        mapList.put('.', "010101"); // .句号
        mapList.put('?', "001100"); // ?问号
        mapList.put('!', "101011"); // !感叹号
        mapList.put('\'', "011110");// '单引号
        mapList.put('\"', "010010");// "引号
        mapList.put('=', "10001");  // =等号
        mapList.put(':', "111000"); // :冒号
        mapList.put(';', "101010"); // ;分号
        mapList.put('(', "10110");  // (前括号
        mapList.put(')', "101101"); // )后括号
        mapList.put(' ', "*");      // 空格
    }

}