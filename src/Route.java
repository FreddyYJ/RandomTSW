import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Route {
	private int number;
	private String nameString;
	private File routeFile;
	public Route(int num, File file)
	{
		number=num;
		routeFile=file;
		String[] name=file.getName().split(".txt");
		nameString=name[0];
	}
	public String getName()
	{
		return nameString;
	}
	public int getId() {return number;}
	public List<Locomotive> getLocomotives()
	{
		ArrayList<Locomotive> locos=new ArrayList<>();
		Scanner file;
		try {
			file = new Scanner(routeFile);
			while(file.hasNext())
			{
				locos.add(new Locomotive(Locomotive.count++, file.nextLine(), number));
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locos;
	}
}
