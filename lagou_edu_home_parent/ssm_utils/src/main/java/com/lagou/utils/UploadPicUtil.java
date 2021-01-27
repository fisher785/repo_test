package com.lagou.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadPicUtil {

    /**
     * 将上传的图片文件，存储项目目录的下upload文件夹下，并如下格式的map集合
     *      "fileName": "UUID.jpg",
     * 		"filePath": "http://localhost:8080/upload/UUID.jpg"
     * @param file 接收前端传递来过的文件类
     * @param request HttpServletRequest
     * @param currentProjectPath 当前项目名称
     * @return map集合
     */
    public static Map<String, Object> getFileNameAndFilePathMap(MultipartFile file,HttpServletRequest request,String currentProjectPath) {
        // 1.获取项目部署真实路径
        String realPath;
        {
            assert false;
            realPath = request.getServletContext().getRealPath("/");
        }

        // 2.截取当前项目路径
        String substring = realPath.substring(0, realPath.indexOf(currentProjectPath));

        // 3.获取文件原始文件名
        String originalFilename = file.getOriginalFilename();

        // 4.生成新文件名 uuid+后缀名
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 5.文件上传，上传地址位根路径的下的upload文件夹
        String uploadPath = substring + "upload\\";

        File filePath = new File(uploadPath, newFileName);
        if (!filePath.getParentFile().exists()) {
            if(filePath.getParentFile().mkdirs()) {
                System.out.println("创建目录：" + filePath);
            }
        }

        // 图片上传
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 6。将文件名和文件路径返回，进行响应
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        return map;
    }
}
