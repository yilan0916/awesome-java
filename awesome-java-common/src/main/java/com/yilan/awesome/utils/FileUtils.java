package com.yilan.awesome.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * 下载文件
     *
     * @param response /
     * @param file     /
     */    public static void download(HttpServletResponse response, File file) {
        assert file != null : "文件为null";
        assert response != null : "response为null";

        String filename = file.getName();

        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setContentType("application/octet-stream;charset=UTF-8");

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream outputStream = response.getOutputStream()) {

            // 发送给客户端的数据
            byte[] buff = new byte[1024];
            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void download(HttpServletResponse response,String filename, InputStream inputStream) {
        assert response != null : "response为null";


        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        response.setHeader("Content-Length", String.valueOf(inputStream.available()));  */
        response.setContentType("application/octet-stream;charset=UTF-8");

        try (BufferedInputStream bis = new BufferedInputStream(inputStream);
             OutputStream outputStream = response.getOutputStream()) {

            // 发送给客户端的数据
            byte[] buff = new byte[1024];
            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除文件、或者删除文件夹和其下的所有文件
     *
     * @param file /
     */    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {

                for (File subFile : subFiles) {
                    deleteFile(subFile);
                }
            }            //删除当前目录
            file.delete();
        }
    }
    /**
     * 创建txt文件，
     *
     * @param dirPath  目录
     * @param fileName 文件名，包括后缀.txt
     * @param content  内容
     */
    public static void createFile(String dirPath, String fileName, byte[] content) {
        // 检查路径，不存在则创建
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dirPath, fileName);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(content);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static File createFile(String fileName, byte[] content) {

        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(content);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * @param inputFile
     * @param outputFile
     * @return
     */    public static boolean upload(MultipartFile inputFile, File outputFile) {
        try (FileInputStream fis = (FileInputStream) inputFile.getInputStream();
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean filesToZip(List<File> fileList, File zipFile) {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            for (File file : fileList) {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, len);
                }
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}