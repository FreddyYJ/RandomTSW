package com.github.freddyyj.randomtsw;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javafx.application.Application;

public class Main {
	private ArrayList<Locomotive> locoList;
	private ArrayList<Route> routeList;
	private ArrayList<Weather> weatherList;
	public static void main(String[] args) {
		Application.launch(com.github.freddyyj.randomtsw.gui.Main.class);
	}
	@Deprecated
	public Main()
	{
		locoList=new ArrayList<>();
		routeList=new ArrayList<>();
	}
	public Main(Vector<String> routeName,HashMap<String, List<String>> locos,Vector<String> weather)
	{
		locoList=new ArrayList<>();
		routeList=new ArrayList<>();
		weatherList=new ArrayList<>();
		
		for (int i=0;i<routeName.size();i++)
		{
			routeList.add(new Route(i, routeName.get(i)));
			for (int j=0;j<locos.get(routeName.get(i)).size();j++) {
				locoList.add(new Locomotive(locoList.size(), locos.get(routeName.get(i)).get(j), i));
			}
		}
		for (int i=0;i<weather.size();i++) {
			weatherList.add(new Weather(i, weather.get(i)));
		}
	}
	public Locomotive getRandomLocomotive()
	{
		Random random=new Random();
		int num=random.nextInt(locoList.size());
		return locoList.get(num);
	}
	public Weather getRansomWeather()
	{
		Random random=new Random();
		int num=random.nextInt(weatherList.size());
		return weatherList.get(num);
	}
	public Route getRoute(Locomotive loco)
	{
		for (Route route:routeList)
		{
			if (route.getId()==loco.getRoute())
				return route;
		}
		return null;
	}
	public void printRandomTSW(String[] args) {
		Main main=new Main();
		Locomotive loco=main.getRandomLocomotive();
		Route route=main.getRoute(loco);
		System.out.println(route.getName()+": "+loco.getName());
		
	}
}
