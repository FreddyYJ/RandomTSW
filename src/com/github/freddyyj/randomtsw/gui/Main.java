package com.github.freddyyj.randomtsw.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		Scene scene=new Scene(FXMLLoader.load(getClass().getResource("MainDoc.fxml")),250,75);
		primaryStage.setTitle("RandomTSW");
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(400);
		
		
		primaryStage.show();
	}
	@Override
	public void stop() throws Exception {
		com.github.freddyyj.randomtsw.Main core=com.github.freddyyj.randomtsw.Main.getInstance();
		super.stop();
	}
}
