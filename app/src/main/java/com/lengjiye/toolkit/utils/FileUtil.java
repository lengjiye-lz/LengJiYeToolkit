package com.lengjiye.toolkit.utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作类，包括创建、删除文件、文件夹，定期删除，制定大小删除等
 * Created by lz on 2016/6/3.
 */
public class FileUtil {

    /**
     * 判断文件夹是否存在
     *
     * @param path 路径
     * @return {@code true} 存在, {@code false} 不存在
     */
    public static boolean isExist(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * 判断文件是否存在
     *
     * @param path 路径
     * @param name 名字
     * @return {@code true} 存在, {@code false} 不存在
     */
    public static boolean isExist(String path, String name) {
        String absolutePath = path + File.separator + name;
        return isExist(absolutePath);
    }

    /**
     * 创建文件夹
     *
     * @param path 路径
     */
    public static void createFolder(String path) {
        createDirectory(path);
    }

    /**
     * 创建目录
     *
     * @param path 路径
     */
    public static File createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 创建文件
     *
     * @param path 路径
     * @param name 名字
     */
    public static void createFile(String path, String name) {
        createDocument(path, name);
    }

    /**
     * 创建文件
     *
     * @param path 路径
     * @param name 名字
     */
    public static File createDocument(String path, String name) {
        createFolder(path);
        File file = new File(path, name);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 删除空的文件夹
     *
     * @param path
     */
    public static boolean deleteEmptyFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return file.delete();
    }

    /**
     * 删除单个文件文件
     *
     * @param path 文件的路径
     * @param name 文件的名字
     */
    public static boolean deleteFile(String path, String name) {
        String absolutePath = path + File.separator + name;
        return deleteEmptyFolder(absolutePath);
    }

    /**
     * 删除文件夹下的所有文件和子文件，并删除当前文件
     * 如果文件目录层级比较深的话，建议添加线程进行操作
     *
     * @param path 路径
     * @return {@code true} 删除成功, {@code false} 删除失败
     */
    public static boolean deleteAll(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            // 递归删除子文件和子文件目录下的所有文件
            String children[] = file.list();
            for (int i = 0; i < children.length; i++) {
                String childrenPath = path + File.separator + children[i];
                if (!deleteAll(childrenPath)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /**
     * 指定大小删除
     *
     * @param path     文件路径
     * @param fileSize 指定的文件大小
     */
    public static void timingDelete(String path, long fileSize) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (fileSize > 0 && file.length() > fileSize) {
            deleteAll(path);
        }
    }

    /**
     * 获取子文件
     *
     * @param path
     * @return
     */
    public static String[] getChildren(String path) {
        String children[] = null;
        File file = new File(path);
        if (!file.exists()) {
            return children;
        }
        if (file.isDirectory()) {
            children = file.list();
        }
        return children;
    }
}
