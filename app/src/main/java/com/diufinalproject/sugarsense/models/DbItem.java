package com.diufinalproject.sugarsense.models;

public class DbItem {
    String count;
    String date;

    public DbItem(String count, String date) {
        this.count = count;
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
