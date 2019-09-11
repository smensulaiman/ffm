package com.diufinalproject.sugarsense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void forceDatabaseReload(Context context) {
        MyDatabase dbHelper = new MyDatabase(context);
        dbHelper.setForcedUpgradeVersion(DATABASE_VERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.setVersion(-1);
        db.close();
        db = dbHelper.getWritableDatabase();
    }

    private void openDB() throws Exception {
        db = getReadableDatabase();
    }

    public boolean registerUser(String name, String email, String password) {
        boolean condition = false;
        if (name == null || email == null || password == null) {
            return false;
        } else {
            try {
                openDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ContentValues contentValues = new ContentValues();

            contentValues.put("name", name);
            contentValues.put("email", email);
            contentValues.put("password", password);

            long result = db.insert("user", null, contentValues);

            if (result == -1) {

                condition = false;
            } else {
                condition = true;
            }
        }
        return condition;
    }

    public String validateUser(String email, String password) {
        String name = null;
        try {
            openDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select name from user";
        Cursor cr = db.rawQuery(sql, null);

        if (cr.moveToNext() && cr.getCount() > 0) {
            cr.moveToFirst();
            while (cr.moveToNext()) {
                name = cr.getString(cr.getColumnIndex("name"));
            }
        } else {
            name = "invalid";
        }

        return name;
    }

    public List<String> getAllTips() {
        List<String> booklist = new ArrayList<>();
        try {
            openDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select * from tips";
        Cursor cr = db.rawQuery(sql, null);

        boolean check = true;

        if (cr != null && cr.getCount() > 0) {
            cr.moveToFirst();
            while (check) {
                String name = cr.getString(cr.getColumnIndex("menu"));
                booklist.add(name);
                check = cr.moveToNext();
            }
        }
        cr.close();
        db.close();
        return booklist;
    }

    public String getBody(String head) {
        try {
            openDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select * from tips where menu = '" + head + "'";
        Cursor cr = db.rawQuery(sql, null);

        boolean check = true;

        if (cr != null && cr.getCount() > 0) {
            cr.moveToFirst();
            while (check) {
                String name = cr.getString(cr.getColumnIndex("desc"));
                return name;
            }
        }
        cr.close();
        db.close();
        return "";
    }

    public ArrayList<String> getAllFruits() {

        ArrayList<String> booklist = new ArrayList<>();
        try {
            openDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select * from cffr";
        Cursor cr = db.rawQuery(sql, null);

        boolean check = true;

        if (cr != null && cr.getCount() > 0) {
            cr.moveToFirst();
            while (check) {
                String name = cr.getString(cr.getColumnIndex("name"));
                booklist.add(name);
                check = cr.moveToNext();
            }
        }
        cr.close();
        db.close();
        return booklist;
    }

    public String getDescription(String item) {
        try {
            openDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select desc from cffr where name = '" + item + "'";
        Cursor cr = db.rawQuery(sql, null);

        boolean check = true;

        if (cr != null && cr.getCount() > 0) {
            cr.moveToFirst();
            while (check) {
                String name = cr.getString(cr.getColumnIndex("desc"));
                if (!name.equals(null))
                    return name;
                check = cr.moveToNext();
            }
        }
        cr.close();
        db.close();
        return "not found";
    }

    public List<String> getFood(String s) {
        List<String> names = new ArrayList<>();
        try {

            openDB();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Cursor cr = db.rawQuery(s, null);
        if (cr != null && cr.getCount() > 0) {
            cr.moveToFirst();
        }

        boolean FLAG = true;

        while (FLAG) {

            String name = cr.getString(cr.getColumnIndex("name"));
            names.add(name);
            FLAG = cr.moveToNext();
        }

        cr.close();
        db.close();

        return names;

    }
}