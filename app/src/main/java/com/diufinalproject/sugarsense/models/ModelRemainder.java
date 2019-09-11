package com.diufinalproject.sugarsense.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelRemainder implements Parcelable {

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public String getFiredate() {
        return firedate;
    }

    public String getColor() {
        return color;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static Creator<ModelRemainder> getCREATOR() {
        return CREATOR;
    }

    String id;
    String type;
    String details;
    String firedate;
    String color;
    String createdAt;

    public ModelRemainder(JSONObject object) throws JSONException {

        String id         = object.getString("id");
        String type    = object.getString("type");
        String details             = object.getString("details");
        String firedate            = object.getString("firedate");
        String color            = object.getString("color");
        String createdAt             = object.getString("created_at");

        this.id = id;
        this.type = type;
        this.details = details;
        this.firedate = firedate;
        this.color = color;
        this.createdAt = createdAt;
    }

    protected ModelRemainder(Parcel in) {
        id = in.readString();
        type = in.readString();
        details = in.readString();
        firedate = in.readString();
        color = in.readString();
        createdAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(details);
        dest.writeString(firedate);
        dest.writeString(color);
        dest.writeString(createdAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelRemainder> CREATOR = new Creator<ModelRemainder>() {
        @Override
        public ModelRemainder createFromParcel(Parcel in) {
            return new ModelRemainder(in);
        }

        @Override
        public ModelRemainder[] newArray(int size) {
            return new ModelRemainder[size];
        }
    };
}
