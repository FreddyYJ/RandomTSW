package com.github.freddyyj.randomtsw;

import com.github.freddyyj.randomtsw.exception.NoElementSelectedException;

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
        int i,temp=0;
        for (i=0;i<routes.size();i++){
            if (!routes.get(i).isSelected) temp++;
        }
        if (temp==i) throw new NoElementSelectedException("No Route Selected! Select one or more route.");

        int size=routes.size();
        int randomValue=randomObject.nextInt(size);
        while(!routes.get(randomValue).isSelected)
            randomValue=randomObject.nextInt(size);

        return routes.get(randomValue);
    }
    public Locomotive randomLocomotive(ArrayList<Locomotive> locomotives){
        int i,temp=0;
        for (i=0;i<locomotives.size();i++){
            if (!locomotives.get(i).isSelected) temp++;
        }
        if (temp==i) throw new NoElementSelectedException("No Locomotive Selected! Select one or more locomotive.");

        int size=locomotives.size();
        int randomValue=randomObject.nextInt(size);
        while(!locomotives.get(randomValue).isSelected)
            randomValue=randomObject.nextInt(size);

        return locomotives.get(randomValue);
    }
    public Locomotive randomLocomotiveInAll(ArrayList<ArrayList<Locomotive>> locomotives){
        ArrayList<Locomotive> list=new ArrayList<>();
        for (int i=0;i<locomotives.size();i++){
            list.addAll(locomotives.get(i));
        }

        int i,temp=0;
        for (i=0;i<list.size();i++){
            if (!list.get(i).isSelected) temp++;
        }
        if (temp==i) throw new NoElementSelectedException("No Locomotive Selected! Select one or more locomotive.");

        temp=0;
        for (i=0;i<locomotives.size();i++){
            if (!locomotives.get(i).get(0).getRoute().isSelected) temp++;
        }
        if (temp==i) throw new NoElementSelectedException("No Route Selected! Select one or more route.");


        int size=list.size();
        int randomValue=randomObject.nextInt(size);
        while(!list.get(randomValue).isSelected || !list.get(randomValue).getRoute().isSelected){
            randomValue=randomObject.nextInt(size);
        }

        return list.get(randomValue);
    }
    public Weather randomWeather(ArrayList<Weather> weathers){
        int i,temp=0;
        for (i=0;i<weathers.size();i++){
            if (!weathers.get(i).isSelected) temp++;
        }
        if (temp==i) throw new NoElementSelectedException("No Weather Selected! Select one or more weather.");

        int size=weathers.size();
        int randomValue=randomObject.nextInt(size);
        while(!weathers.get(randomValue).isSelected)
            randomValue=randomObject.nextInt(size);

        return weathers.get(randomValue);
    }
}
