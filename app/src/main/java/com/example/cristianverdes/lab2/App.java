package com.example.cristianverdes.lab2;

import android.app.Application;

import com.example.cristianverdes.lab2.database.MarsDataBase;

/**
 * Created by Cristian Verdes on 11/2/2016.
 */

public class App extends Application {
    MarsDataBase marsDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        this.marsDataBase = new MarsDataBase(this, 1);
    }

    public MarsDataBase getMarsDataBase() {
        return marsDataBase;
    }
}
