package com.github.freddyyj.randomtsw.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import com.github.freddyyj.randomtsw.Route;

public class SaveLoco {
	private JsonArray array;
	private String documentFile=javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
	private File saveFile;
	public SaveLoco(ArrayList<Route> routes) {
		saveFile=new File(documentFile+"/randomtsw.json");
		if (!saveFile.exists())
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			
			JsonArrayBuilder builder=Json.createArrayBuilder();
			
			JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
			objectBuilder.addNull("route");
			builder.add(objectBuilder);
			
			for (int i=0;i<routes.size();i++) {
				objectBuilder=Json.createObjectBuilder();
				objectBuilder.addNull(routes.get(i).getName());
				builder.add(objectBuilder);
			}
			
			array=builder.build();
			System.out.println(array.toString());
			FileOutputStream writer=new FileOutputStream(saveFile);
			JsonWriter jsonWriter=Json.createWriter(writer);
			jsonWriter.writeArray(array);
			jsonWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
}
