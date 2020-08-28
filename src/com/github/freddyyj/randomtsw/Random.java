package com.github.freddyyj.randomtsw;

import java.util.ArrayList;

public class Random {
    private static Random random=null;
    private java.util.Random randomObject;
    protected Random(){
        randomObject=new java.util.Random();
    }
    public static Random getInstance(){
        if (random==null) random=new Random();
        return random;
    }
    public Route randomRoute(ArrayList<Route> routes){
        int size=routes.size();
        int randomValue=randomObject.nextInt(size);

        return routes.get(randomValue);
    }
    public Locomotive randomWeather(ArrayList<Locomotive> weathers){
        int size=weathers.size();
        int randomValue=randomObject.nextInt(size);

        return weathers.get(randomValue);
    }
    public Weather randomLocomotive(ArrayList<Weather> locomotives){
        int size=locomotives.size();
        int randomValue=randomObject.nextInt(size);

        return locomotives.get(randomValue);
    }
}
