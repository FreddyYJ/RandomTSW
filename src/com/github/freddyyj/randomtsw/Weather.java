package com.github.freddyyj.randomtsw;

public class Weather {
	private String name;
	public boolean isSelected;
	@Deprecated
	public Weather(int id,String name) {
		this.setName(name);
	}
	public Weather(String name,boolean select) {
		this.setName(name);
		isSelected=select;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
