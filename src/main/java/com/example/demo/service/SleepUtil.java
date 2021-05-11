package com.example.demo.service;

public class SleepUtil {
    public static  void sleepSeconds( int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
