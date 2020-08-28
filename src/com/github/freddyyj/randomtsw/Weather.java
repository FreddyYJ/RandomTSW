package com.github.freddyyj.randomtsw;

public class Weather {
	private String name;
	@Deprecated
	public Weather(int id,String name) {
		this.setName(name);
	}
	public Weather(String name) {
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
