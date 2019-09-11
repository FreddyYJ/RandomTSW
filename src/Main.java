import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	private ArrayList<Locomotive> locoList;
	private ArrayList<Route> routeList;
	public static final String ROUTE_DIR_NAME="TSWRoutes";
	public Main()
	{
		locoList=new ArrayList<>();
		routeList=new ArrayList<>();
		File routeFile=new java.io.File(".\\"+ROUTE_DIR_NAME);
		if (!routeFile.exists())
		{
			routeFile.mkdir();
		}
		
		File[] routeFiles=routeFile.listFiles();
		for (int i=0;i<routeFiles.length;i++)
		{
			routeList.add(new Route(i, routeFiles[i]));
			locoList.addAll(routeList.get(i).getLocomotives());
		}
	}
	public Locomotive getRandomLocomotive()
	{
		Random random=new Random();
		int num=random.nextInt(locoList.size());
		return locoList.get(num);
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
		Locomotive loco=main.getRandomLocomotive();
		Route route=main.getRoute(loco);
		System.out.println(route.getName()+": "+loco.getName());
	}
}
