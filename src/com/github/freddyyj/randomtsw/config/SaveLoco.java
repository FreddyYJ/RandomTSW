package com.github.freddyyj.randomtsw.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import com.github.freddyyj.randomtsw.Locomotive;
import com.github.freddyyj.randomtsw.Main;
import com.github.freddyyj.randomtsw.Route;
import com.github.freddyyj.randomtsw.Weather;

public class SaveLoco {
	private JsonObject object;
	private File saveFile;
	private Main core;
	public SaveLoco(ArrayList<Route> routes, Main main) {
		core=main;
		JsonObjectBuilder builder=Json.createObjectBuilder();
		
		builder.add("route",Json.createArrayBuilder());
		
		for (int i=0;i<routes.size();i++) {
			builder.add(routes.get(i).getName(),Json.createArrayBuilder());
		}
		builder.add("weather", Json.createArrayBuilder());
		
		object=builder.build();
	}
	public SaveLoco(ArrayList<Route> routes, Main main,String defaultPath) {
		core=main;
		saveFile=new File(defaultPath);
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				
				JsonObjectBuilder builder=Json.createObjectBuilder();
				
				builder.add("route",Json.createArrayBuilder());
				
				for (int i=0;i<routes.size();i++) {
					builder.add(routes.get(i).getName(),Json.createArrayBuilder());
				}
				builder.add("weather", Json.createArrayBuilder());
				
				object=builder.build();
				FileOutputStream writer=new FileOutputStream(saveFile);
				JsonWriter jsonWriter=Json.createWriter(writer);
				jsonWriter.writeObject(object);
				jsonWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileInputStream reader=new FileInputStream(saveFile);
			JsonReader jsonReader=Json.createReader(reader);
			object=jsonReader.readObject();
			jsonReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public void reload() {
		FileInputStream reader;
		try {
			reader = new FileInputStream(saveFile);
			JsonReader jsonReader=Json.createReader(reader);
			object=jsonReader.readObject();
			jsonReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void setFilePath(String path) {
		saveFile=new File(path);
	}
	public ArrayList<String> getRoute(){
		JsonArray routeArray= (JsonArray) object.get("route");
		ArrayList<String> routeStrings=new ArrayList<>();
		for (int i=0;i<routeArray.size();i++) {
			routeStrings.add(routeArray.getString(i));
		}
		return routeStrings;
		
	}
	public ArrayList<String> getLocomotive(Route route){
		JsonArray locoArray= (JsonArray) object.get(route.getName());
		if (locoArray==null) {
			JsonObjectBuilder builder=Json.createObjectBuilder(object);
			builder.add(route.getName(), Json.createArrayBuilder());
			object=builder.build();
			locoArray= (JsonArray) object.get(route.getName());
		}
		ArrayList<String> locoStrings=new ArrayList<>();
		for (int i=0;i<locoArray.size();i++) {
			locoStrings.add(locoArray.getString(i));
		}
		return locoStrings;

	}
	public ArrayList<String> getWeather(){
		JsonArray weatherArray= (JsonArray) object.get("weather");
		ArrayList<String> weatherStrings=new ArrayList<>();
		for (int i=0;i<weatherArray.size();i++) {
			weatherStrings.add(weatherArray.getString(i));
		}
		return weatherStrings;

	}
	public void add(Route route) {
		JsonArray routeArray= (JsonArray) object.get("route");
		JsonArrayBuilder builder=Json.createArrayBuilder(routeArray);
		if (find(routeArray, route.getName())==-1) {
			builder.add(route.getName());
			JsonObjectBuilder objectBuilder=Json.createObjectBuilder(object);
			objectBuilder.add("route", builder);
			object=objectBuilder.build();
		}
	}
	public void add(Locomotive loco) {
		JsonArray locoArray=(JsonArray) object.get(core.getRoute(loco).getName());
		JsonArrayBuilder builder=Json.createArrayBuilder(locoArray);
		if (find(locoArray, loco.getName())==-1) {
			builder.add(loco.getName());
			JsonObjectBuilder objectBuilder=Json.createObjectBuilder(object);
			objectBuilder.add(core.getRoute(loco).getName(), builder);
			object=objectBuilder.build();
		}
	}
	public void add(Weather weather) {
		JsonArray weatherArray= (JsonArray) object.get("weather");
		JsonArrayBuilder builder=Json.createArrayBuilder(weatherArray);
		if (find(weatherArray, weather.getName())==-1) {
			builder.add(weather.getName());
			JsonObjectBuilder objectBuilder=Json.createObjectBuilder(object);
			objectBuilder.add("weather", builder);
			object=objectBuilder.build();
		}

	}
	public void remove(Route route) {
		JsonArray routeArray= (JsonArray) object.get("route");
		JsonArrayBuilder builder=Json.createArrayBuilder(routeArray);
		if (find(routeArray, route.getName())!=-1) {
			builder.remove(find(routeArray, route.getName()));
			JsonObjectBuilder objectBuilder=Json.createObjectBuilder(object);
			objectBuilder.add("route", builder);
			object=objectBuilder.build();
		}
	}
	public void remove(Locomotive loco) {
		JsonArray locoArray=(JsonArray) object.get(core.getRoute(loco).getName());
		JsonArrayBuilder builder=Json.createArrayBuilder(locoArray);
		if (find(locoArray, loco.getName())!=-1) {
			builder.remove(find(locoArray, loco.getName()));
			JsonObjectBuilder objectBuilder=Json.createObjectBuilder(object);
			objectBuilder.add(core.getRoute(loco).getName(), builder);
			object=objectBuilder.build();
		}
	}
	public void remove(Weather weather) {
		JsonArray weatherArray= (JsonArray) object.get("weather");
		JsonArrayBuilder builder=Json.createArrayBuilder(weatherArray);
		if (find(weatherArray, weather.getName())!=-1) {
			builder.remove(find(weatherArray, weather.getName()));
			JsonObjectBuilder objectBuilder=Json.createObjectBuilder(object);
			objectBuilder.add("weather", builder);
			object=objectBuilder.build();
		}
	}
	public void save() {
		this.save(saveFile);
	}
	public void save(File file) {
		try {
			FileOutputStream writer = new FileOutputStream(file);
			JsonWriter jsonWriter=Json.createWriter(writer);
			jsonWriter.writeObject(object);
			jsonWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private int find(JsonArray array,String string) {
		for (int i=0;i<array.size();i++) {
			if (array.getString(i).equals(string))
				return i;
		}
		return -1;
	}
	
}
