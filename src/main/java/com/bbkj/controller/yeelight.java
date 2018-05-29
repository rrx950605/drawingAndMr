package com.bbkj.controller;


import com.bbkj.utils.RedisUtil;
import com.mollin.yapi.YeelightDevice;
import redis.clients.jedis.Jedis;

/**
 * @author rrx
 */
public class yeelight {

    private static Jedis jedis = new Jedis();

    private static void connectRedis() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
    }

    public static void main(String[] args) throws Exception {

      /*  connectRedis();

        jedis.flushAll();*/
        //实例化灯
        YeelightDevice device = new YeelightDevice("192.168.1.105");
        //
        int cronDelay = device.getCronDelay();
        System.out.println(cronDelay);
        controllerYee(device,false,255,0,255,2);

    }

    private static void controllerYee(YeelightDevice device, boolean power, int r, int g, int b, int ness) throws Exception {
        //开灯
        device.setPower(power);
        //设置rgb颜色
        device.setRGB(r, g, b);
        //设置亮度
        device.setBrightness(ness);
    }
}
