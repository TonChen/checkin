package com.fredchen.checkin.common.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: fredchen
 * @Date: 2018/1/23 10:06
 */
public class FileUtil {
    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
        return targetFile.getAbsolutePath();
    }
}
