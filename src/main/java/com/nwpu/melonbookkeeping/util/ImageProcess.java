package com.nwpu.melonbookkeeping.util;

import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author noorall
 * @date 2021/1/102:10 下午
 * @Description: 图片处理相关工具类
 */
public class ImageProcess {
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        try {
            // 解密
            byte[] b = Base64Utils.decodeFromString(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            path = ResourceUtils.getURL("classpath:").getPath() +"static/user/avatar/"+ path + ".png";
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
