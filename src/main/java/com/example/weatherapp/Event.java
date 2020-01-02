package com.example.weatherapp;
public class Event {

    /** Title of the earthquake event */
    public final String mname,mmain,mpressure,mhumidity,mspeed,msunrise;
    public final int  mtemp,mfeels_like,mmaxtemp,mmintemp;


    /** Number of people who felt the earthquake and reported how strong it was */


    public Event(String name,String main,int temp,int feels_like,int maxtemp,int mintemp,String pressure,String humidity,String speed,String sunrise) {
        mname=name;
        mmain=main;
        mtemp=temp-273;
        mfeels_like=feels_like-273;
        mmaxtemp=maxtemp-273;
        mmintemp = mintemp-273;
        mpressure=pressure;
        mhumidity= humidity;
        mspeed= speed;
        msunrise= sunrise;
    }
}
