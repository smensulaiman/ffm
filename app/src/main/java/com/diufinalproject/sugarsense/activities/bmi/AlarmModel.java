package com.diufinalproject.sugarsense.activities.bmi;

public class AlarmModel {
    private String time,desc;

    public AlarmModel(String time, String desc) {
        this.time = time;
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
