package com.bbkj.utils;

import com.mollin.yapi.YeelightDevice;
import okhttp3.*;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rrx
 */
public class Light {
    private static Jedis jedis = new Jedis();

    public static void connectRedis() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
    }

    public static void main(String[] args) throws Exception {
        connectRedis();
        String ip = "192.168.1.100";
        String key = "O1a1JJb3XZw2DUAOXtpDoIFviJ4Pf7vrq60qMQ9K";
        String on = "false";
        YeelightDevice device = new YeelightDevice("192.168.99.100");
        String magicip = "192.168.137.33";
        byte[] magicon = {(byte) 0x71, (byte) 0x23, (byte) 0x0F, (byte) 0xA3};
        byte[] magicoff = {(byte) 0x71, (byte) 0x24, (byte) 0x0F, (byte) 0xA4};
        int h = 46920;
        jedis.flushAll();
        controllerYee(device, false, 144, 238, 144, 20);
        controller(3, ip, key, "false", 250, h, 254);
        controller(4, ip, key, "false", 250, h, 254);
        controller(5, ip, key, "false", 250, h, 254);
        controller(3, ip, key, "false", 250, h, 254);
        controller(4, ip, key, "false", 250, h, 254);
        controller(5, ip, key, "false", 250, h, 254);
        List<Long> time = new ArrayList<>();
        int s = 0;
        int x = 0;
        int c = 0;
        while (true) {
            String hue = jedis.rpop("hue");
            if (hue != null) {
                time.clear();
                System.out.println(hue);
                String[] hubArr = hue.split(",");
                switch (Integer.parseInt(hubArr[0])) {
                    case -1:
                        c++;
                        if (c > 1) {
                            if (s == 0) {
                                s = 1;
                                System.out.println("憋气");
                                if ("true".equals(on)) {
                                    on = "false";
                                    controllerYee(device, false, 144, 238, 144, 20);
                                    magicLight(magicip, magicoff);
                                    controller(3, ip, key, on, 250, h, 254);
                                    controller(4, ip, key, on, 250, h, 254);
                                    controller(5, ip, key, on, 250, h, 254);
                                    controller(3, ip, key, on, 250, h, 254);
                                    controller(4, ip, key, on, 250, h, 254);
                                    controller(5, ip, key, on, 250, h, 254);
                                } else {
                                    on = "true";
                                    x++;
                                    if (x % 2 != 0) {
                                        controllerYee(device, true, 144, 238, 144, 20);
                                        magicLight(magicip, magicon);
                                        controller(3, ip, key, on, 250, h, 254);
                                        controller(4, ip, key, on, 250, h, 254);
                                        controller(5, ip, key, on, 250, h, 254);
                                        controller(3, ip, key, on, 250, h, 254);
                                        controller(4, ip, key, on, 250, h, 254);
                                        controller(5, ip, key, on, 250, h, 254);
                                    }
                                }
                            }
                        }
                        break;
                    case 0:
                        c = 0;
                        s = 0;

                        break;

                    case 1:
                        c = 0;

                        s = 0;

                        break;
                    case 2:

                        break;
                    default:

                }

            } else {
                /*long t = System.currentTimeMillis();
                time.add(t);
                if (time.size() > 2) {
                    if (time.get(time.size() - 1) - time.get(0) > 2000) {
                        controller(3, ip, key, on, 200, 46920, 254);
                        controller(4, ip, key, on, 200, 46920, 254);
                        controller(5, ip, key, on, 200, 46920, 254);
                    }
                }*/
            }
        }

    }


    private static void controllerYee(YeelightDevice device, boolean power, int r, int g, int b, int ness) throws Exception {
        //开灯
        device.setPower(power);
        //设置rgb颜色
        device.setRGB(r, g, b);
        //设置亮度
        device.setBrightness(ness);
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

    /**
     * @param lights 设备号
     * @param ip     网桥ip
     * @param key    用户key
     * @param on     灯的开关
     * @param bri    亮度
     * @param hue    色调
     * @param sat    饱和度
     */

    public static void controller(int lights, String ip, String key, String on, int bri, int hue, int sat) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\"on\":" + on + ",\"bri\":" + bri + ",\"hue\":" + hue + ",\"sat\":" + sat + "}");
        Request request = new Request.Builder()
                .url("http://" + ip + "/api/" + key + "/lights/" + lights + "/state")
                .put(body)
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .build();
        try {
            Response execute = client.newCall(request).execute();
            execute.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
