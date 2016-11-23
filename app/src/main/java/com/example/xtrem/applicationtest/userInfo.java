package com.example.xtrem.applicationtest;

/**
 * Created by YaHeng on 2016-10-27.
 */

public class userInfo {

    public String email;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public int numberRides = 0;
    public int rating;
    public int distance = 0;


    public userInfo(){

    }

    //makes a User object that can store info and move that information into the Firebase Database API

    public userInfo(String email,String firstName, String lastName, String phoneNumber, int numberRides, int rating, int distance)
    {
        this.email=email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.numberRides = numberRides;
        this.rating = rating;
        this.distance = distance;
    }
}
