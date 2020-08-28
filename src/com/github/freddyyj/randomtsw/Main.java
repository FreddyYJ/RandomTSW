package com.github.freddyyj.randomtsw;

import java.util.*;

import com.github.freddyyj.randomtsw.config.Config;
import com.github.freddyyj.randomtsw.config.SaveLoco;

import javafx.application.Application;
import org.junit.Test;

public class Main {
	private ArrayList<Route> routes;
	private HashMap<Route,ArrayList<Locomotive>> locos;
	private ArrayList<Weather> weathers;
	private SaveLoco unselectedLocos;
	private Config config;
	private static Main core=null;
	public static void main(String[] args) {
		Application.launch(com.github.freddyyj.randomtsw.gui.Main.class);
		
	}
	@Test
	public void randomTest(){
		
	}
	public static Main getInstance() {
		if (core==null) core=new Main();
		return core;
	}
	protected Main()
	{
		routes=new ArrayList<>();
		locos=new HashMap<>();
		weathers=new ArrayList<>();

		config=new Config();
		if (config.getConfig("DefaultSaveFilePath")==null)
			unselectedLocos=new SaveLoco(com.github.freddyyj.randomtsw.gui.Main.controller.getRouteList());
		else {
			unselectedLocos=new SaveLoco(com.github.freddyyj.randomtsw.gui.Main.controller.getRouteList(), config.getConfig("DefaultSaveFilePath"));
		}

		for (int i=0;i<com.github.freddyyj.randomtsw.gui.Main.controller.getRouteList().size();i++){
			Route route=new Route(com.github.freddyyj.randomtsw.gui.Main.controller.getRouteList().get(i));
			ArrayList<Locomotive> locoList=new ArrayList<>();
			for (int j=0;j<com.github.freddyyj.randomtsw.gui.Main.controller.getLocoList().get(i).size();j++){
				Locomotive locomotive=new Locomotive(com.github.freddyyj.randomtsw.gui.Main.controller.getLocoList().get(i).get(j),route);
				locoList.add(locomotive);
			}
			locos.put(route,locoList);
		}

		for (int i=0;i<com.github.freddyyj.randomtsw.gui.Main.controller.getWeather().size();i++){
			Weather weather=new Weather(com.github.freddyyj.randomtsw.gui.Main.controller.getWeather().get(i));
			weathers.add(weather);
		}

		reload();
	}
	public void reload(){
		for (int i=0;i<routes.size();i++){
			for (int j=0;j<unselectedLocos.getRoute().size();j++){
				if (routes.get(i).getName().equals(unselectedLocos.getRoute().get(j))) {
					routes.remove(i);
					i--;
					break;
				}
			}
			ArrayList<String> locos=unselectedLocos.getLocomotive(routes.get(i).getName());
			for (int k=0;k<this.locos.get(routes.get(i)).size();k++){
				for (int l=0;l<locos.size();l++){
					if (this.locos.get(routes.get(i)).get(k).getName().equals(locos.get(l))){
						this.locos.get(routes.get(i)).remove(k);
						k--;
						break;
					}
				}
			}
		}

		for (int i=0;i<weathers.size();i++){
			for (int j=0;j<unselectedLocos.getWeather().size();j++){
				if (weathers.get(i).getName().equals(unselectedLocos.getWeather().get(j))){
					weathers.remove(i);
					i--;
					break;
				}
			}
		}
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
		String path;
		try {
			 path=config.getConfig("DefaultSaveFilePath");
		}catch (NullPointerException e) {
			path=null;
		}
		return path;
	}
	public boolean isSaveChanged(){return unselectedLocos.isChanged();}
	public void saveConfig() {
		config.save();
	}
}
