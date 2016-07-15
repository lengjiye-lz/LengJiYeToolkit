package com.lengjiye.toolkit.business;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lengjiye.toolkit.application.LJYApplication;

/**
 * 数据库操作类
 * Created by lz on 2016/7/14.
 */
public class DBOperationDao {

    private final static String NAME = "lengjiye.db";
    private final static int VERSION = 1;

    private DBHelper dbHelper;
    private static DBOperationDao operation;

    public DBOperationDao() {
        dbHelper = new DBHelper(LJYApplication.getInstance().getApplicationContext(), NAME, null, VERSION);
    }

    public static DBOperationDao getInstance() {
        if (operation == null) {
            operation = new DBOperationDao();
        }
        return operation;
    }

    /**
     * 向数据库中插入和更新数据
     */
    public void insertAndUpdateData() {
        //获取数据库对象
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //使用execSQL方法向表中插入数据
        db.execSQL("insert into hero_info(name,level) values('bb',0)");
        //使用insert方法向表中插入数据
        ContentValues values = new ContentValues();
        values.put("name", "xh");
        values.put("level", 5);
        //调用方法插入数据
        db.insert("hero_info", "id", values);
        //使用update方法更新表中的数据
        //清空ContentValues对象
        values.clear();
        values.put("name", "xh");
        values.put("level", 10);
        //更新xh的level 为10
        db.update("hero_info", values, "level = 5", null);
        //关闭SQLiteDatabase对象
        db.close();
    }

    /**
     * 从数据库中查询数据
     *
     * @return
     */
    public String queryData() {
        String result = "";
        //获得数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询表中的数据
        Cursor cursor = db.query("hero_info", null, null, null, null, null, "id asc");
        //获取name列的索引
        int nameIndex = cursor.getColumnIndex("name");
        //获取level列的索引
        int levelIndex = cursor.getColumnIndex("level");
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            result = result + cursor.getString(nameIndex) + "\t\t";
            result = result + cursor.getInt(levelIndex) + "       \n";
        }
        cursor.close();//关闭结果集
        db.close();//关闭数据库对象
        return result;
    }

}
