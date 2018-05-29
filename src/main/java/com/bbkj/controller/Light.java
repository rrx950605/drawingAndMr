package com.bbkj.controller;

import com.bbkj.utils.RedisUtil;
import okhttp3.*;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rrx
 */
public class Light {
    private static Jedis jedis = new Jedis();

    private static void connectRedis() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
    }

    public static void main(String[] args) {
        connectRedis();
        String ip = "192.168.1.100";
        String key = "O1a1JJb3XZw2DUAOXtpDoIFviJ4Pf7vrq60qMQ9K";
        String on = "true";
        String off = "false";
        jedis.flushAll();
        //计时
        List<Long> time = new ArrayList<>();
        while (true) {
            String hue = jedis.rpop("hue");
            if (hue != null) {
                time.clear();
                System.out.println(hue);
                String[] hubArr = hue.split(",");
                switch (Integer.parseInt(hubArr[0])) {
                    //憋气
                    case -1:
                        controller(3, ip, key, on, 200, 12750, 254);
                        controller(4, ip, key, on, 200, 12750, 254);
                        controller(5, ip, key, on, 200, 12750, 254);
                        break;
                    //离开坐垫
                    case 0:
                        controller(3, ip, key, off, 200, 36000, 254);
                        controller(4, ip, key, off, 200, 36000, 254);
                        controller(5, ip, key, off, 200, 36000, 254);
                        break;
                    //正常
                    case 1:

                        if (Integer.parseInt(hubArr[1]) == -1) {
                            controller(3, ip, key, on, 140, 46920, Integer.parseInt(hubArr[2]) / 2 + 30);
                            controller(4, ip, key, on, 140, 46920, Integer.parseInt(hubArr[2]) / 2 + 30);
                            controller(5, ip, key, on, 140, 46920, Integer.parseInt(hubArr[2]) / 2 + 30);

                        } else if (Integer.parseInt(hubArr[1]) == 1) {

                            controller(3, ip, key, on, 200, 46920, Integer.parseInt(hubArr[2]) / 2 + 30);
                            controller(4, ip, key, on, 200, 46920, Integer.parseInt(hubArr[2]) / 2 + 30);
                            controller(5, ip, key, on, 200, 46920, Integer.parseInt(hubArr[2]) / 2 + 30);
                        }
                        break;
                    //体动
                    case 2:
                       /* controller(3, ip, key, on, 200, 0, 254);
                        controller(4, ip, key, on, 200, 0, 254);
                        controller(5, ip, key, on, 200, 0, 254);*/
                        break;

                    default:

                }
            } else {
                long t = System.currentTimeMillis();
                time.add(t);
                if (time.size() > 2) {
                    if (time.get(time.size() - 1) - time.get(0) > 2000) {
                        controller(3, ip, key, off, 200, 46920, 254);
                        controller(4, ip, key, off, 200, 46920, 254);
                        controller(5, ip, key, off, 200, 46920, 254);
                    }
                }
            }
        }
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
    private static void controller(int lights, String ip, String key, String on, int bri, int hue, int sat) {
        controllerLight(lights, ip, key, on, bri, hue, sat);
    }

    public static void controllerLight(int lights, String ip, String key, String on, int bri, int hue, int sat) {
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
