package com.github.freddyyj.randomtsw.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class Main extends Application{
	static Scene scene;
	static Stage stage;
	private MainController controller;
	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MainDoc.fxml"));
		scene=new Scene(loader.load(),250,75);
		stage=primaryStage;
		primaryStage.setTitle("RandomTSW");
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(400);
		controller=loader.getController();
		primaryStage.setOnCloseRequest(event -> {
			com.github.freddyyj.randomtsw.Main core= com.github.freddyyj.randomtsw.Main.getInstance();
			if(core.isSaveChanged()) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to save changed?", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
				alert.setHeaderText("Savefile changed!");
				alert.setTitle("Save File");
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent() && result.get() == ButtonType.YES) {
					controller.onSave(null);
					core.saveConfig();
				}
				else if (result.isPresent() && result.get() == ButtonType.NO){
					core.saveConfig();
				}
				else event.consume();
			}
		});
		primaryStage.show();
	}
}
