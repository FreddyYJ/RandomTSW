package com.github.freddyyj.randomtsw;

/**
 * A locomotive class
 * @author FreddyYJ_
 */
public class Locomotive {
	private String name;
	private Route route;

	/**
	 * If true, this locomotive can be selected by random pick.
	 */
	public boolean isSelected;

	/**
	 * Constructor with loco ID, loco name and running route
	 * @deprecated Use {@link Locomotive#Locomotive(String, Route)} instead.
	 * @param id locomotive ID
	 * @param name locomotive name
	 * @param route route that this locomotive running
	 */
	@Deprecated
	public Locomotive(int id,String name,Route route)
	{
		this.setName(name);
		this.setRoute(route);
	}

	/**
	 * Constructor with loco name, running route and selection
	 * @param name locomotive name
	 * @param route route that this locomotive running
	 * @param isSelected initial {@link Locomotive#isSelected} value
	 */
	public Locomotive(String name,Route route,boolean isSelected)
	{
		this.setName(name);
		this.setRoute(route);
		this.isSelected=isSelected;
	}

	/**
	 * Constructor with loco name and running route. {@link Locomotive#isSelected} sets to true.
	 * @param name locomotive name
	 * @param route route that this locomotive running
	 */
	public Locomotive(String name,Route route){
		this(name,route,true);
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
