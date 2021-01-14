package com.nwpu.melonbookkeeping.util;

import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Objects;

/**
 * @author noorall
 * @date 2021/1/102:10 下午
 * @Description: 图片处理相关工具类
 */
public class ImageProcess {
    /**
     * 将Base64编码的图片解码并存储
     *
     * @param imgStr Base64编码
     * @param path   存储路径
     * @return 是否成功解码并存储
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        try {
            imgStr=imgStr.replace("\r\n", "");
            imgStr=imgStr.replace("\n", "");
            // 解密
            byte[] b = Base64Utils.decodeFromString(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            path = ResourceUtils.getURL("classpath:").getPath() + "static/user/avatar/" + path + ".png";
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将图片编码为Base64
     *
     * @param imgFile 图片的路径
     * @return 加密后的图片
     */
    public static String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            imgFile = ResourceUtils.getURL("classpath:").getPath() + "static/user/avatar/" + imgFile + ".png";
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data != null) {
            return Base64Utils.encodeToString(data);
        } else {
            return null;
        }
    }

}
