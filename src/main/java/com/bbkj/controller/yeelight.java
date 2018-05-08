package com.bbkj.controller;


import com.mollin.yapi.YeelightDevice;

public class yeelight {
    public static void main(String[] args) throws Exception {
        //实例化灯
        YeelightDevice device = new YeelightDevice("192.168.1.47");
        //开灯
        device.setPower(true);
        //设置rgb颜色
        device.setRGB(255, 126, 0);
        //设置亮度
        device.setBrightness(100);
    }
}
