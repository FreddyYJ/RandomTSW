import java.util.ArrayList;
import java.util.Random;

public class Locomotive {
	private int id;
	private String name;
	private int route;
	public static int count=0;
	public Locomotive(int id,String name,int route)
	{
		this.setId(id);
		this.setName(name);
		this.setRoute(route);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoute() {
		return route;
	}
	public void setRoute(int route) {
		this.route = route;
	}
}
