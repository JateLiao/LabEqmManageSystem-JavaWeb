/*
 * 文件名：ValidCodeHelper.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ValidCodeHelper.java
 * 修改人：KOBE
 * 修改时间：2016年3月12日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * TODO 登录验证码生成Helper.
 * 
 * @author KOBE
 */
public class ValidCodeHelper {
    /**
     * random.
     */
    static Random random = new Random();

    /**
     * TODO 随机生成包含验证码的字符串.
     * 
     * @param num
     *            验证码字符个数
     * @return 随机验证码
     */
    public static String random(int num) {
        // 初始化种子
        String[] seed = {"0", "1", "2", "3", "4", "5", "6", "7", "8", 
                         "9", "a", "b", "c", "d", "e", "f", "g", "h", 
                         "i", "j", "k", "l", "m", "n", "o", "p", "q", 
                         "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        
        
        int number = seed.length;
        // 接受随机字符
        String randomText = "";
        // 随机产生4个字符的字符串
        for (int i = 0; i < num; i++) {
            randomText += seed[random.nextInt(number)];
        }

        return randomText.toUpperCase();
    }

    /**
     * TODO 随机产生定义的颜色.
     * 
     * 
     * @return Color
     */
    private static Color getRandomColor() {
        Random r = new Random();
        Color[] colors = new Color[10];
        colors[0] = new Color(32, 158, 25);
        colors[1] = new Color(218, 42, 19);
        colors[2] = new Color(31, 75, 208);
        colors[3] = new Color(0, 102, 182);
        colors[4] = new Color(171, 0, 85);

        return colors[r.nextInt(5)];
    }

    /**
     * TODO 产生随机字体.
     *
     * @return Font
     */
    private static Font getFont() {
        Random randoms = new Random();
        Font[] font = new Font[5];
        font[0] = new Font("Consolas", Font.BOLD, 30);
        font[1] = new Font("Consolas", Font.BOLD, 30);
        font[2] = new Font("Consolas", Font.BOLD, 30);
        font[3] = new Font("Consolas", Font.BOLD, 30);
        font[4] = new Font("Consolas", Font.BOLD, 30);

        return font[randoms.nextInt(5)];
    }

    /**
     * TODO 生成图片.
     * 
     * @param randomStr
     *            随机字符串
     * @param out
     *            输出流
     * @param width
     *            验证码图片宽度
     * @param height
     *            验证码图片高度
     * @throws IOException
     *             .
     */
    public static void render(String randomStr, OutputStream out, int width, int height) throws IOException {
        // 在内存中创建图像
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
        // 获取图形上下文
        Graphics2D g = (Graphics2D) bi.getGraphics();
        // 话边框
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        g.setFont(getFont());
        g.setColor(Color.BLACK);
        // 画认证码，每个认证码在不同的水平位置
        String[] str1 = new String[randomStr.length()];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = randomStr.substring(i, i + 1);
            int w = 0;
            int x = (i + 1) % 3;
            // 随机生成验证码字符水平偏移量
            if (x == random.nextInt(7)) {
                w = 30 - random.nextInt(7);
            } else {
                w = 30 + random.nextInt(7);
            }
            // 随机生成颜色
            g.setColor(getRandomColor());
            g.drawString(str1[i], 20 * i + 10, w);
        }
        // 随机产生干扰点，并用不同的颜色表示，事图像的认证码不易被其他程序探测到
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            // 随机画各种颜色的线
            g.setColor(color);
            g.drawOval(x, y, 0, 0);
        }
        // 画干扰线
        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            // 随机画各种颜色线
            g.setColor(color);
            g.drawLine(x, y, x1, y1);
        }
        // 图像生效
        g.dispose();
        // 输出页面
        ImageIO.write(bi, "jpg", out);
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param args
     *            .
     * @throws Exception
     *             .
     */
    public static void main(String[] args) throws Exception {
        // 获取随机字符串
        String randomStr = random(4);
        System.out.println(randomStr);
        // 生成图片
        render(randomStr, new FileOutputStream("D:\\Logs\\test.jpg"), 100, 35);
    }
}
