package com.damddos.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
 
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
 
public final class GraphicHelper {
 /**
       * 以字符串形式返回生成的验证码，同时输出一个图片
  * @param width
  * @param height
  * @param imgType
  * @param output
  * @return
  */
	public static String create(final int width, final int height, final String imgType, OutputStream output) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
	 
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
	 
		graphic.setColor(Color.getColor("F8F8F8"));
		graphic.fillRect(0, 0, width, height);
	 
		Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,
			Color.CYAN };
		// 在 "画板"上生成干扰线条 ( 50 是线条个数)
		for (int i = 0; i < 50; i++) {
		    graphic.setColor(colors[random.nextInt(colors.length)]);
		    final int x = random.nextInt(width);
		    final int y = random.nextInt(height);
		    final int w = random.nextInt(20);
		    final int h = random.nextInt(20);
		    final int signA = random.nextBoolean() ? 1 : -1;
		    final int signB = random.nextBoolean() ? 1 : -1;
		    graphic.drawLine(x, y, x + w * signA, y + h * signB);
		} 
		// 在 "画板"上绘制字母
		graphic.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		for (int i = 0; i < 6; i++) {
		    final int temp = random.nextInt(26) + 97;
		    String s = String.valueOf((char) temp);
		    sb.append(s);
		    graphic.setColor(colors[random.nextInt(colors.length)]);
		    graphic.drawString(s, i * (width / 6), height - (height / 3));
		}
		graphic.dispose();
		try {
		    ImageIO.write(image, imgType, output);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return sb.toString();
	}

	public static void  main(String[] args) {
		final int width = 180;
		final int height = 40;
		final String imageType = "jpeg";
        ByteArrayOutputStream output = new ByteArrayOutputStream();		
		String code = GraphicHelper.create(width, height, imageType, output);
		
//		核心是使用ByteArrayOutputStream output = new ByteArrayOutputStream();流将图片对象转换为字节数组，再使用自带的Base64.Encoder进行转换。
        byte[] captcha = output.toByteArray();
        
		System.out.println("验证码内容= " + code);
		      
        Base64 base64Encoder = new Base64();
        String imagestr =  base64Encoder.encode(captcha);// 返回Base64编码过的字节数组字符串
        System.out.println("------imagestr----------:"+imagestr);
        System.out.println("------captcha.toString()----------:"+captcha.toString());
        String path = "D:/myimgTest.png";
        String path2 = "D:/myimg2Test.png";
        byte[] data = captcha;
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }

        Base64 base64Decoder = new Base64();
//        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码         Base64字符串生成 图片
            byte[] bytes = base64Decoder.decode(imagestr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
//                    System.out.println("bytes[i] = " + bytes[i]);
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path2);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {

        }
     
	}
	
}

//   https://blog.csdn.net/weixin_36380516/article/details/56497508
