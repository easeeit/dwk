package com.dwk.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Random code to image
 * 
 * @author: xp
 * @data : 2014-9-2
 * @since : 1.5
 */
public class ImageCodeUtils {

  private static final Logger logger = LoggerFactory.getLogger(ImageCodeUtils.class);

  public static final int WIDTH = 100;
  public static final int HEIGHT = 30;
  public static final int SIZE = 5;
  
  public static final int FONT_SIZE = 20;
  private static final int FONT_RANDOM_SIZE = 3;

  private static final int START_X = 10;
  private static final int START_Y = 18;
  
  private static final int ROTATE_X = 4;
  private static final int ROTATE_Y = 6;

  private static final int COLOR = 255;

  private static final char[] CHAR = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

  private ByteArrayInputStream image; // 图像
  private String text;// 验证码

  private ImageCodeUtils(int width, int height, int size, int font) {
    init(width, height, size, font);
  }

  public static ImageCodeUtils Instance() {
    return new ImageCodeUtils(WIDTH, HEIGHT, SIZE, FONT_SIZE);
  }

  public static ImageCodeUtils Instance(int width, int height, int size, int font) {
    return new ImageCodeUtils(width, height, size, font);
  }

  private void init(int width, int height, int size, int font) {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // 得到该图片的绘图对象
    Graphics2D g = (Graphics2D) img.getGraphics();

    // start end 使用不同的值 可用于渐变
    Color startColor = Color.WHITE;
    Color endColor = Color.WHITE;

    GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);
    g.setPaint(gradient);

    Rectangle2D rect = new Rectangle2D.Float(0, 0, width, height);
    g.fill(rect);

    /*
     * 加噪点 g.setColor(getRandColor(60,200)); for (int i=0;i<155;i++){ int x = random.nextInt(width);
     * int y = random.nextInt(height); int xl = random.nextInt(12); int yl = random.nextInt(12);
     * g.drawLine(x,y,x+xl,y+yl); }
     */

    Random random = new Random();

    // 字体
    Font[] fonts = {
        new Font(Font.DIALOG, Font.ITALIC, font + random.nextInt(FONT_RANDOM_SIZE)), 
        new Font(Font.DIALOG_INPUT, Font.BOLD, font + random.nextInt(FONT_RANDOM_SIZE)),
        new Font(Font.SANS_SERIF, Font.ITALIC, font + random.nextInt(FONT_RANDOM_SIZE)),
        new Font(Font.SERIF, Font.BOLD, font + random.nextInt(FONT_RANDOM_SIZE)), 
        new Font(Font.MONOSPACED, Font.BOLD, font + random.nextInt(FONT_RANDOM_SIZE))};
    
    int index, x = START_X, y = START_Y, len = CHAR.length;

    StringBuffer buffer = new StringBuffer();
    // 字体颜色
    Color charColor = new Color(random.nextInt(COLOR), random.nextInt(COLOR), random.nextInt(COLOR));
    for (int i = 0; i < size; i++) {
      index = random.nextInt(len);
      g.setColor(charColor);

      // 平滑处理
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      Font f = fonts[random.nextInt(fonts.length)];
      g.setFont(f);
      x += (i == 0) ? 0 : (f.getSize()-random.nextInt(FONT_RANDOM_SIZE));

      // 旋转
      double ro = (random.nextDouble() * Math.PI / ROTATE_X) - (Math.PI / ROTATE_Y);
      g.rotate(ro, x, y);

      g.drawString("" + CHAR[index], x, y);
      g.rotate(-ro, x, y);
      buffer.append(CHAR[index]);
    }

    // 图象生效
    g.dispose();
    ByteArrayInputStream input = null;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
      ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
      ImageIO.write(img, "JPEG", imageOut);
      imageOut.close();
      input = new ByteArrayInputStream(output.toByteArray());
    } catch (Exception e) {
      logger.error("Create random code image error. Cause:", e);
    }
    this.image = input;
    this.text = buffer.toString();
  }

  public ByteArrayInputStream getImage() {
    return this.image;
  }

  public String getString() {
    return this.text;
  }
  
}
