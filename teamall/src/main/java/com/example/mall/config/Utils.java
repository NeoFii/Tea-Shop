package com.example.mall.config;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Utils
 *
 */
public class Utils {

    static String uploadPath = System.getProperty("user.dir").replace("\\","/")+"/product-img/";

    public static String uploadFile(MultipartFile iconFile, String id) {
        if (iconFile != null && !iconFile.getOriginalFilename().equals("")) {
            try {
                String imgFormat = iconFile.getOriginalFilename().substring(iconFile.getOriginalFilename().lastIndexOf("."));
                // 指定保存路径
                String fileName = id;
                String filePath = uploadPath + "\\" + fileName + imgFormat;
                File file = new File(uploadPath);
                //如果文件夹不存在则会创建
                if (!file.exists()){
                    file.mkdirs();
                }
                // 保存文件到本地
                iconFile.transferTo(new File(filePath));
                return "/upload/"+fileName + imgFormat;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }

}
