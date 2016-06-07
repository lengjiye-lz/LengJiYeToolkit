package com.lengjiye.toolkit.utils;

import org.junit.Test;

/**
 * 类说明
 * Created by lz on 2016/6/6.
 */
public class FileUtilTest {
    private String path = "/storage/emulated/0/J1Health/test";

    @Test
    public void testIsExist() throws Exception {

    }

    @Test
    public void testIsExist1() throws Exception {

    }

    @Test
    public void testCreateFolder() throws Exception {
        FileUtil.createFolder(path);
    }

    @Test
    public void testCreateDirectory() throws Exception {
        FileUtil.createFolder(path);
    }

    @Test
    public void testCreateFile() throws Exception {
        FileUtil.createFile(path, "11111.text");
    }

    @Test
    public void testCreateDocument() throws Exception {

    }

    @Test
    public void testDeleteEmptyFolder() throws Exception {

    }

    @Test
    public void testDeleteFile() throws Exception {

    }

    @Test
    public void testDeleteAll() throws Exception {

    }

    @Test
    public void testTimingDelete() throws Exception {

    }
}