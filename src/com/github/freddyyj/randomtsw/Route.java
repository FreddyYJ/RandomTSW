package com.github.freddyyj.randomtsw;
import java.io.File;
import java.util.ArrayList;

public class Route {
	private int number;
	private String nameString;
	ArrayList<Locomotive> locomotives;
	@Deprecated
	public Route(int num, File file)
	{
		number=num;
		String[] name=file.getName().split(".txt");
		nameString=name[0];
	}
	@Deprecated
	public Route(int num,String name) {
		number=num;
		nameString=name;
	}
	public Route(int num,String name, ArrayList<Locomotive> locos) {
		number=num;
		nameString=name;
		locomotives=locos;
	}
	public String getName()
	{
		return nameString;
	}
	public int getId() {return number;}
	public ArrayList<Locomotive> getLocomotives()
	{
		return locomotives;
	}
}
