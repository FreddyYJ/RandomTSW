package com.github.freddyyj.randomtsw.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 * Default configuration class
 * Don't create this object directly. Use {@link com.github.freddyyj.randomtsw.Main} methods instead.
 * @author FreddyYJ_
 */
public class Config {
	private static final String FILE_NAME="/randomtsw.json";
	private JsonObject object;
	private String documentFile=javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
	private File saveFile;

	/**
	 * Constructor
	 */
	public Config() {
		saveFile=new File(documentFile+FILE_NAME);
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			JsonObjectBuilder builder=Json.createObjectBuilder();
			builder.addNull("DefaultSaveFilePath");
			object=builder.build();
			
			FileOutputStream writer;
			try {
				writer = new FileOutputStream(saveFile);
				JsonWriter jsonWriter=Json.createWriter(writer);
				jsonWriter.writeObject(object);
				jsonWriter.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			FileInputStream reader=new FileInputStream(saveFile);
			JsonReader jsonReader=Json.createReader(reader);
			object=jsonReader.readObject();
			jsonReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add or update value of key.
	 * @param key target key
	 * @param value target value
	 */
	public void setConfig(String key,String value) {
		JsonObjectBuilder builder=Json.createObjectBuilder(object);
		builder.add(key, value);
		object=builder.build();
	}

	/**
	 * Get value of key.
	 * <p>
	 *     If key doesn't exist, return null.
	 * </p>
	 * @param key target key
	 * @return value of key
	 */
	public String getConfig(String key) {
		if (object.get(key)==JsonValue.NULL || !object.containsKey(key))
			return null;
		return object.getString(key);
	}

	/**
	 * Save changes.
	 */
	public void save() {
		try {
			FileOutputStream writer = new FileOutputStream(saveFile);
			JsonWriter jsonWriter=Json.createWriter(writer);
			jsonWriter.writeObject(object);
			jsonWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
