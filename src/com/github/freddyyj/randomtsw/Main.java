package com.github.freddyyj.randomtsw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.github.freddyyj.randomtsw.config.Config;
import com.github.freddyyj.randomtsw.config.SaveLoco;

import javafx.application.Application;

public class Main {
	private ArrayList<Locomotive> locoList;
	private ArrayList<Route> routeList;
	private ArrayList<Weather> weatherList;
	private SaveLoco unselectedLocos;
	private Config config;
	private static Main core;
	public static void main(String[] args) {
		Application.launch(com.github.freddyyj.randomtsw.gui.Main.class);
		
	}
	public static Main getInstance() {
		return core;
	}
	@Deprecated
	public Main()
	{
		locoList=new ArrayList<>();
		routeList=new ArrayList<>();
	}
	public Main(Vector<String> routeName,HashMap<String, List<String>> locos,Vector<String> weather)
	{
		core=this;

		locoList=new ArrayList<>();
		routeList=new ArrayList<>();
		weatherList=new ArrayList<>();
		
		for (int i=0;i<routeName.size();i++)
		{
			ArrayList<Locomotive> locoRoute=new ArrayList<>();
			for (int j=0;j<locos.get(routeName.get(i)).size();j++) {
				Locomotive locomotive=new Locomotive(locoList.size(), locos.get(routeName.get(i)).get(j), i);
				locoList.add(locomotive);
				locoRoute.add(locomotive);
			}
			routeList.add(new Route(i, routeName.get(i),locoRoute));
		}
		for (int i=0;i<weather.size();i++) {
			weatherList.add(new Weather(i, weather.get(i)));
		}
		
		config=new Config(this);
		if (config.getConfig("DefaultSaveFilePath")==null)
			unselectedLocos=new SaveLoco(routeList,this);
		else {
			unselectedLocos=new SaveLoco(routeList, this, config.getConfig("DefaultSaveFilePath"));
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
	public Locomotive getLocomotive(String name,String routeName) {
		for (int i=0;i<locoList.size();i++) {
			if (locoList.get(i).getName().equals(name) && routeList.get(locoList.get(i).getRoute()).getName().equals(routeName))
				return locoList.get(i);
		}
		return null;
	}
	public Weather getWeather(String name) {
		for (int i=0;i<weatherList.size();i++) {
			if (weatherList.get(i).getName().equals(name)) {
				return weatherList.get(i);
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
	public void selectRoute(boolean isSelected,String routeName) {
		Route route=getRoute(routeName);
		if (isSelected==false) {
			unselectedLocos.add(route);
		}
		else {
			unselectedLocos.remove(route);
		}
	}
	public void selectLocomotive(boolean isSelected,String locoName,String routeName) {
		Locomotive loco=getLocomotive(locoName, routeName);
		if (isSelected==false) {
			unselectedLocos.add(loco);
		}
		else {
			unselectedLocos.remove(loco);
		}

	}
	public void selectWeather(boolean isSelected,String weatherName) {
		Weather weather=getWeather(weatherName);
		if (isSelected==false) {
			unselectedLocos.add(weather);
		}
		else {
			unselectedLocos.remove(weather);
		}

	}
	public ArrayList<Route> getUnselectedRoute(){
		ArrayList<Route> routes=new ArrayList<>();
		ArrayList<String> routeStrings=unselectedLocos.getRoute();
		for (int i=0;i<routeStrings.size();i++) {
			routes.add(getRoute(routeStrings.get(i)));
		}
		return routes;
	}
	public ArrayList<ArrayList<Locomotive>> getUnselectedLoco(){
		ArrayList<ArrayList<Locomotive>> locos=new ArrayList<>();
		ArrayList<String> locoStrings;
		ArrayList<Locomotive> locoList;
		for (int i=0;i<routeList.size();i++) {
			locoList=new ArrayList<>();
			locoStrings=unselectedLocos.getLocomotive(routeList.get(i));
			for (int j=0;j<locoStrings.size();j++) {
				locoList.add(getLocomotive(locoStrings.get(j), routeList.get(i).getName()));
			}
			locos.add(locoList);
		}
		return locos;
	}
	public ArrayList<Weather> getUnselectedWeather(){
		ArrayList<Weather> weathers=new ArrayList<>();
		ArrayList<String> weatherStrings=unselectedLocos.getWeather();
		for (int i=0;i<weatherStrings.size();i++) {
			weathers.add(getWeather(weatherStrings.get(i)));
		}
		return weathers;
	}
	public void close() {
		unselectedLocos.save();
	}
	public void saveAs(String path) {
		setSaveFilePath(path);
		unselectedLocos.save();
	}
	public void reloadSaveFile(String path) {
		setSaveFilePath(path);
		unselectedLocos.reload();
	}
	public void setSaveFilePath(String path) {
		config.setConfig("DefaultSaveFilePath", path);
		unselectedLocos.setFilePath(path);
	}
	public String getSaveFilePath() {
		return config.getConfig("DefaultSaveFilePath");
	}
	public void saveConfig() {
		config.save();
	}
}
