package com.github.freddyyj.randomtsw;
public class Locomotive {
	private String name;
	private Route route;
	public boolean isSelected;
	public static int count=0;
	@Deprecated
	public Locomotive(int id,String name,Route route)
	{
		this.setName(name);
		this.setRoute(route);
	}
	public Locomotive(String name,Route route,boolean isSelected)
	{
		this.setName(name);
		this.setRoute(route);
		this.isSelected=isSelected;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
}
