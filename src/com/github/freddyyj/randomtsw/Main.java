package com.github.freddyyj.randomtsw;

import java.util.*;

import com.github.freddyyj.randomtsw.config.Config;
import com.github.freddyyj.randomtsw.config.SaveLoco;

import javafx.application.Application;

import static com.github.freddyyj.randomtsw.gui.Main.controller;

public class Main {
	private ArrayList<Route> routes;

	public HashMap<Route, ArrayList<Locomotive>> getLocos() {
		return locos;
	}

	private HashMap<Route,ArrayList<Locomotive>> locos;
	private ArrayList<Weather> weathers;
	private SaveLoco unselectedLocos;
	private Config config;
	private static Main core=null;
	public static void main(String[] args) {
		Application.launch(com.github.freddyyj.randomtsw.gui.Main.class);
		
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
			unselectedLocos=new SaveLoco(controller.getRouteList());
		else {
			unselectedLocos=new SaveLoco(controller.getRouteList(), config.getConfig("DefaultSaveFilePath"));
		}

		for (int i = 0; i< controller.getRouteList().size(); i++){
			Route route=new Route(controller.getRouteList().get(i));
			ArrayList<Locomotive> locoList=new ArrayList<>();
			for (int j = 0; j< controller.getLocoList().get(i).size(); j++){
				Locomotive locomotive=new Locomotive(controller.getLocoList().get(i).get(j),route);
				locoList.add(locomotive);
			}
			locos.put(route,locoList);
			routes.add(route);
		}

		for (int i = 0; i< controller.getWeather().size(); i++){
			Weather weather=new Weather(controller.getWeather().get(i));
			weathers.add(weather);
		}

		reload();
		controller.reload(unselectedLocos);
	}

	public SaveLoco getUnselectedLocos() {
		return unselectedLocos;
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
	public ArrayList<Route> getRoutes(){return routes;}
	public ArrayList<Weather> getWeathers(){return weathers;}
	public Route getRoute(String name) {
		for (int i=0;i<routes.size();i++) {
			if (routes.get(i).getName().equals(name)) {
				return routes.get(i);
			}
		}
		return null;
	}
	public ArrayList<Locomotive> getLocomotive(String routeName) {
		Route route=getRoute(routeName);
		if (locos.get(route).size()>=1)
			return locos.get(route);
		else return null;
	}
	public Locomotive getLocomotive(String routeName,String locoName){
		ArrayList<Locomotive> locoList=getLocomotive(routeName);
		for (int i=0;i<locoList.size();i++){
			if (locoList.get(i).getName().equals(locoName)) return locoList.get(i);
		}
		return null;
	}
	public Weather getWeather(String name) {
		for (int i=0;i<weathers.size();i++) {
			if (weathers.get(i).getName().equals(name)) {
				return weathers.get(i);
			}
		}
		return null;
	}
	public void selectRoute(boolean isSelected,String route) {
		if (!isSelected) {
			unselectedLocos.addRoute(route);
		}
		else {
			unselectedLocos.removeRoute(route);
		}
	}
	public void selectLocomotive(boolean isSelected,Locomotive loco,Route route) {
		ArrayList<Locomotive> locoList=getLocomotive(route.getName());
		if (!isSelected) {
			unselectedLocos.addLocomotive(route.getName(),loco.getName());
		}
		else {
			unselectedLocos.removeLocomotive(route.getName(),loco.getName());
		}

	}
	public void selectWeather(boolean isSelected,Weather weather) {
		if (!isSelected) {
			unselectedLocos.addWeather(weather.getName());
		}
		else {
			unselectedLocos.removeWeather(weather.getName());
		}

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
