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
	public Locomotive getRandomLocomotive(Route route) {
		ArrayList<Locomotive> locos=new ArrayList<>();
		for (int i=0;i<locoList.size();i++) {
			if (locoList.get(i).getRoute()==route.getId()) {
				locos.add(locoList.get(i));
			}
		}
		Random random=new Random();
		int num=random.nextInt(locos.size());
		return locos.get(num);
	}
	public Weather getRandomWeather()
	{
		Random random=new Random();
		int num=random.nextInt(weatherList.size());
		return weatherList.get(num);
	}
	public Route getRandomRoute() {
		Random random=new Random();
		int num=random.nextInt(routeList.size());
		return routeList.get(num);
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
	public Route getRoute(String name) {
		for (int i=0;i<routeList.size();i++) {
			if (routeList.get(i).getName().equals(name)) {
				return routeList.get(i);
			}
		}
		return null;
	}
	@Deprecated
	public void printRandomTSW(String[] args) {
		Main main=new Main();
		Locomotive loco=main.getRandomLocomotive();
		Route route=main.getRoute(loco);
		System.out.println(route.getName()+": "+loco.getName());
		
	}
}
