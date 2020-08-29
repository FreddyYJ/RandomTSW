package com.github.freddyyj.randomtsw;
import java.io.File;
import java.util.ArrayList;

public class Route {
	private String name;
	private ArrayList<Locomotive> locomotives;
	public boolean isSelected;
	@Deprecated
	public Route(int num, File file)
	{
		String[] name=file.getName().split(".txt");
		this.name=name[0];
	}
	@Deprecated
	public Route(int num,String name) {
		this.name=name;
	}
	@Deprecated
	public Route(int num,String name, ArrayList<Locomotive> locos) {
		this.name=name;
		locomotives=locos;
	}
	public Route(String name, ArrayList<Locomotive> locos,boolean select){
		this.name=name;
		locomotives=locos;
		isSelected=select;
	}
	public Route(String name,boolean select){
		this.name=name;
		isSelected=select;
	}
	public String getName()
	{
		return name;
	}
	public ArrayList<Locomotive> getLocomotives()
	{
		return locomotives;
	}
}
