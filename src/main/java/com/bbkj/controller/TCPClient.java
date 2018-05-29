package com.bbkj.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class TCPClient {
    public static void main(String[] args) throws Exception {
        byte[] bytes1 = {(byte) 0x81, (byte) 0x8A, (byte) 0x8B, (byte) 0x96};
        byte[] bytes = {(byte) 0x61, (byte) 0x25, (byte) 0x01, (byte) 0x0F, (byte) 0x96};
        byte[] on = {(byte) 0x71, (byte) 0x23, (byte) 0x0F, (byte) 0xA3};
        byte[] off = {(byte) 0x71, (byte) 0x24, (byte) 0x0F, (byte) 0xA4};
        byte[] bytes3 = {0x31, (byte) 0x8A, (byte) 0x96, 0x0F, 32};
        byte[] red = {(byte) 0x61, (byte) 0x26, (byte) 0x01, (byte) 0x0F, (byte) 0x97};
        byte[] RoyalBlue1 = {(byte) 0x31, (byte) 0x48, (byte) 0x76, (byte) 0xe1, (byte) 0x97, (byte) 0xf0, (byte) 0xf0, (byte) 0x31 + (byte) 0x48 + (byte) 0x76 + (byte) 0xe1 + (byte) 0x97 + (byte) 0xf0 + (byte) 0xf0};
        byte[] DarkOrchid1 = {(byte) 0x31, (byte) 0xbf, (byte) 0x3e, (byte) 0xff, (byte) 0x97, (byte) 0xf0, (byte) 0xf0, (byte) 0x4a4};
        String ip = "192.168.137.33";
        magicLight(ip, red);
    }

    private static void magicLight(String ip, byte[] bytes) throws Exception {
        Socket client = new Socket(ip, 5577);
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        out.write(bytes);
        out.close();
        in.close();
        client.close();
        System.out.println("success");
    }
}