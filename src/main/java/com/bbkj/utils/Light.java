package com.bbkj.utils;

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

    public static void connectRedis() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
    }

    public static void main(String[] args) {
        connectRedis();
        String ip = "192.168.1.102";
        String key = "O1a1JJb3XZw2DUAOXtpDoIFviJ4Pf7vrq60qMQ9K";
        String on = "false";
        int h = 46920;
        jedis.flushAll();
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
                                        h = 46920;
                                        controller(3, ip, key, on, 250, h, 254);
                                        controller(4, ip, key, on, 250, h, 254);
                                        controller(5, ip, key, on, 250, h, 254);
                                        controller(3, ip, key, on, 250, h, 254);
                                        controller(4, ip, key, on, 250, h, 254);
                                        controller(5, ip, key, on, 250, h, 254);
                                    } else {
                                        h = 46920;
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
                        /*controller(3, ip, key, on, 200, 46920, 254);
                        controller(4, ip, key, on, 200, 46920, 254);
                        controller(5, ip, key, on, 200, 46920, 254);*/
                        break;

                    case 1:
                        c = 0;

                        s = 0;
                        /*if (Integer.parseInt(hubArr[1]) == -1) {
                            if (Integer.parseInt(hubArr[2]) > 100 && Integer.parseInt(hubArr[2]) < 200) {
                                controller(3, ip, key, on, 140, 46920, Integer.parseInt(hubArr[2]) + 30);
                                controller(4, ip, key, on, 140, 46920, Integer.parseInt(hubArr[2]) + 30);
                                controller(5, ip, key, on, 140, 46920, Integer.parseInt(hubArr[2]) + 30);
                            } else {
                                controller(3, ip, key, on, 150, 46920, Integer.parseInt(hubArr[2]));
                                controller(4, ip, key, on, 150, 46920, Integer.parseInt(hubArr[2]));
                                controller(5, ip, key, on, 150, 46920, Integer.parseInt(hubArr[2]));
                            }
                        } else if (Integer.parseInt(hubArr[1]) == 1) {
                            if (Integer.parseInt(hubArr[2]) > 100 && Integer.parseInt(hubArr[2]) < 200) {
                                controller(3, ip, key, on, 200, 46920, Integer.parseInt(hubArr[2]) + 30);
                                controller(4, ip, key, on, 200, 46920, Integer.parseInt(hubArr[2]) + 30);
                                controller(5, ip, key, on, 200, 46920, Integer.parseInt(hubArr[2]) + 30);
                            } else {
                                controller(3, ip, key, on, 220, 46920, 254);
                                controller(4, ip, key, on, 220, 46920, 254);
                                controller(5, ip, key, on, 220, 46920, 254);
                            }
                        }*/
                        break;
                    case 2:
                        /*controller(3, ip, key, on, 250, 46920, 254);
                        controller(4, ip, key, on, 250, 46920, 254);
                        controller(5, ip, key, on, 250, 46920, 254);*/
                       /* x++;
                        if (x % 2 == 0) {
                            controller(3, ip, key, on, 100, h, 254);
                            controller(4, ip, key, on, 100, h, 254);
                            controller(5, ip, key, on, 100, h, 254);
                        } else {
                            controller(3, ip, key, on, 200, h, 254);
                            controller(4, ip, key, on, 200, h, 254);
                            controller(5, ip, key, on, 200, h, 254);
                        }*/
                        break;
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
//            System.out.println(response.body().string());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
