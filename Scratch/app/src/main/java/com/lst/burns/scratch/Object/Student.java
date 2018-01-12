package com.lst.burns.scratch.Object;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Student implements Parcelable {
    private String name;
    private String surname;
    private String email;

    @Override
    public int describeContents() {
        Log.d("ZFH","describeContents");
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d("ZFH","writeToParcel");
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(email);
    }

    // We need to add a Creator
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel parcel) {
            Log.d("ZFH","createFromParcel");
            return new Student(parcel);
        }

        @Override
        public Student[] newArray(int size) {
            Log.d("ZFH","newArray");
            return new Student[size];
        }
    };

    // We reconstruct the object reading from the Parcel data
    public Student(Parcel p) {
        Log.d("ZFH","Student Constructor with parcel");
        name = p.readString();
        surname = p.readString();
        email = p.readString();
    }

    public Student() {
        Log.d("ZFH","Student Constructor default");

    }

    public String getName() {
        Log.d("ZFH","Student getName");
        return name;
    }

    public Student setName(String name) {
        Log.d("ZFH","Student setName");
        this.name = name;
        return this;
    }

    public String getSurname() {
        Log.d("ZFH","Student getSurname");
        return surname;
    }

    public Student setSurname(String surname) {
        Log.d("ZFH","Student setSurname");
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }
}