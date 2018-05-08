package com.bbkj.controller;

import com.mollin.yapi.YeelightDevice;

public class yeelight {
    public static void main(String[] args) throws Exception {
        YeelightDevice device = new YeelightDevice("192.168.1.47");
        device.setPower(true);
        device.setRGB(255, 126, 0);
        device.setBrightness(100);
    }
}
