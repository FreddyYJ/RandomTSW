package com.github.freddyyj.randomtsw.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.github.freddyyj.randomtsw.Locomotive;
import com.github.freddyyj.randomtsw.Main;
import com.github.freddyyj.randomtsw.Route;
import com.github.freddyyj.randomtsw.Weather;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainController {
	@FXML private VBox boxRoute;
	@FXML private Pane boxLoco;
	@FXML private VBox boxWeather;
	@FXML private TextField textPickedRoute;
	@FXML private TextField textPickedLoco;
	@FXML private TextField textPickedWeather;
	private List<List<Node>> locos;
	private List<Node> routes;
	private List<Node> weathers;
	private VBox currentRoute;
	private Main core;
	public MainController() {}
	@FXML
	private void initialize() {
		locos=new Vector<List<Node>>();
		routes=boxRoute.getChildren();
		weathers=boxWeather.getChildren();
		currentRoute=getLocoBoxByID("checkCSX");
		List<Node> locoList;
		for (int i=0;i<boxLoco.getChildren().size();i++) {
			VBox locoBox=(VBox) boxLoco.getChildren().get(i);
			locoList=locoBox.getChildren();
			locos.add(locoList);
		}

		Vector<String> routes=new Vector<>();
		HashMap<String, List<String>> locos=new HashMap<>();
		for (int i=0;i<this.routes.size();i++) {
			routes.add(((CheckBox)this.routes.get(i)).getText());
			List<String> locoLists=new Vector<>();
			for (int j=0;j<this.locos.get(i).size();j++) {
				locoLists.add(((CheckBox)this.locos.get(i).get(j)).getText());
			}
			locos.put(((CheckBox)this.routes.get(i)).getText(), locoLists);
		}
		Vector<String> weather=new Vector<>();
		for (int i=0;i<weathers.size();i++) {
			weather.add(((CheckBox)weathers.get(i)).getText());
		}
		core=new Main(routes,locos,weather);
	}
	@FXML
	protected void onCheckRouteClick(MouseEvent e) {
		if(currentRoute!=null) {
			currentRoute.setVisible(false);
			currentRoute.setDisable(true);
			Node routeBox=(Node)e.getSource();
			String id=routeBox.getId();
			currentRoute=getLocoBoxByID(id);
			currentRoute.setVisible(true);
			currentRoute.setDisable(false);
		}
		else {
			currentRoute=getLocoBoxByID("checkCSX");
		}
	}
	@FXML
	protected void onRandomAll(ActionEvent e) {
		Locomotive loco=core.getRandomLocomotive();
		Route route=core.getRoute(loco);
		Weather weather=core.getRansomWeather();
		textPickedRoute.setText(route.getName());
		textPickedLoco.setText(loco.getName());
		textPickedWeather.setText(weather.getName());
	}
	protected VBox getLocoBoxByID(String routeId) {
		List<Node> loco=boxLoco.getChildren();
		String locoId;
		String[] id=routeId.split("check");
		locoId=id[1];
		locoId="box"+locoId+"Locos";
		for (int i=0;i<loco.size();i++) {
			if (loco.get(i).getId().equals(locoId))
				return (VBox) loco.get(i);
		}
		return null;
	}
}
