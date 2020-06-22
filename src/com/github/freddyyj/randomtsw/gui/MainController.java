package com.github.freddyyj.randomtsw.gui;

import com.github.freddyyj.randomtsw.Locomotive;
import com.github.freddyyj.randomtsw.Main;
import com.github.freddyyj.randomtsw.Route;
import com.github.freddyyj.randomtsw.Weather;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.*;

public class MainController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox boxRoute;
    @FXML
    private Pane boxLoco;
    @FXML
    private VBox boxWeather;
    @FXML
    private TextField textPickedRoute;
    @FXML
    private TextField textPickedLoco;
    @FXML
    private TextField textPickedWeather;
    @FXML
    private MenuItem itemSave;
    @FXML
    private MenuItem itemSaveAs;
    @FXML
    private MenuItem itemLoad;
    @FXML
    private MenuItem itemClose;
    @FXML
    private MenuItem itemAbout;
    private List<List<Node>> locos;
    private List<Node> routes;
    private List<Node> weathers;
    private VBox currentRoute;
    private CheckBox currentBox;
    private Main core;

    public MainController() {
    }

    @FXML
    private void initialize() {
        locos = new Vector<List<Node>>();
        routes = boxRoute.getChildren();
        weathers = boxWeather.getChildren();
        currentRoute = getLocoBoxByID("checkCSX");
        currentRoute.setVisible(true);
        currentRoute.setDisable(false);
        List<Node> locoList;
        for (int i = 0; i < boxLoco.getChildren().size(); i++) {
            VBox locoBox = (VBox) boxLoco.getChildren().get(i);
            locoList = locoBox.getChildren();
            locos.add(locoList);
        }

        Vector<String> routes = new Vector<>();
        HashMap<String, List<String>> locos = new HashMap<>();
        for (int i = 0; i < this.routes.size(); i++) {
            routes.add(((CheckBox) this.routes.get(i)).getText());
            List<String> locoLists = new Vector<>();
            for (int j = 0; j < this.locos.get(i).size(); j++) {
                locoLists.add(((CheckBox) this.locos.get(i).get(j)).getText());
            }
            locos.put(((CheckBox) this.routes.get(i)).getText(), locoLists);
        }
        Vector<String> weather = new Vector<>();
        for (int i = 0; i < weathers.size(); i++) {
            weather.add(((CheckBox) weathers.get(i)).getText());
        }
        core = new Main(routes, locos, weather);
        reload();

        currentBox = (CheckBox) this.routes.get(0);

        anchorPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                newScene.setOnKeyPressed(this::onShortcut);
            }
        });
    }

    @FXML
    protected void onCheckRouteClick(MouseEvent e) {
        if (currentBox == null) {
            currentBox = (CheckBox) routes.get(0);
        }
        if (e.getSource() instanceof CheckBox) {
            currentBox = (CheckBox) e.getSource();
        }
        if (currentRoute == null) {
            currentRoute = getLocoBoxByID("checkCSX");
        }
        currentRoute.setVisible(false);
        currentRoute.setDisable(true);
        Node routeBox = (Node) e.getSource();
        String id = routeBox.getId();
        currentRoute = getLocoBoxByID(id);
        currentRoute.setVisible(true);
        currentRoute.setDisable(false);

        textPickedRoute.setText(((CheckBox) routeBox).getText());
    }

    @FXML
    protected void onRandomAll(ActionEvent e) {
        Locomotive loco = core.getRandomLocomotive();
        Route route = core.getRoute(loco);
        CheckBox locoBox = getLocoByName(loco.getName(), route.getId());
        Weather weather = core.getRandomWeather();
        while (!locoBox.isSelected() || !getWeatherByName(weather.getName()).isSelected() || !((CheckBox) routes.get(route.getId())).isSelected()) {
            loco = core.getRandomLocomotive();
            route = core.getRoute(loco);
            locoBox = getLocoByName(loco.getName(), route.getId());
            weather = core.getRandomWeather();
        }
        textPickedRoute.setText(route.getName());
        textPickedLoco.setText(loco.getName());
        textPickedWeather.setText(weather.getName());

        currentRoute.setVisible(false);
        currentRoute.setDisable(true);
        int id=route.getId();
        String name=routes.get(id).getId();
        currentRoute = getLocoBoxByID(name);
        currentRoute.setVisible(true);
        currentRoute.setDisable(false);
    }

    @FXML
    protected void onRandomRoute(ActionEvent e) {
        Random random = new Random();
        int index = random.nextInt(routes.size());
        CheckBox selectedRoute = (CheckBox) routes.get(index);
        while (!selectedRoute.isSelected()) {
            index = random.nextInt(routes.size());
            selectedRoute = (CheckBox) routes.get(index);
        }
        currentRoute.setVisible(false);
        currentRoute.setDisable(true);

        textPickedRoute.setText(selectedRoute.getText());
        currentRoute = getLocoBoxByID(selectedRoute.getId());
        currentRoute.setVisible(true);
        currentRoute.setDisable(false);
    }

    @FXML
    protected void onRandomLoco(ActionEvent e) {
        Locomotive loco = core.getRandomLocomotive(core.getRoute(getRouteByVBox(currentRoute).getText()));
        Route route = core.getRoute(loco);
        while (!getLocoByName(loco.getName(), route.getId()).isSelected()) {
            loco = core.getRandomLocomotive(core.getRoute(getRouteByVBox(currentRoute).getText()));
            route = core.getRoute(loco);
        }
        textPickedRoute.setText(route.getName());
        textPickedLoco.setText(loco.getName());
    }

    @FXML
    protected void onRandomWeather(ActionEvent e) {
        Weather weather = core.getRandomWeather();
        while (!getWeatherByName(weather.getName()).isSelected())
            weather = core.getRandomWeather();
        textPickedWeather.setText(weather.getName());
    }

    @FXML
    protected void onCheckLocoSelect(ActionEvent e) {
        if (e.getSource() instanceof CheckBox) {
            CheckBox selectedLoco = (CheckBox) e.getSource();
            core.selectLocomotive(selectedLoco.isSelected(), selectedLoco.getText(), currentBox.getText());
        }
    }

    @FXML
    protected void onCheckRouteSelect(ActionEvent e) {
        if (e.getSource() instanceof CheckBox) {
            CheckBox selectedRoute = (CheckBox) e.getSource();
            core.selectRoute(selectedRoute.isSelected(), selectedRoute.getText());
        }

    }

    @FXML
    protected void onCheckWeatherSelect(ActionEvent e) {
        if (e.getSource() instanceof CheckBox) {
            CheckBox selectedWeather = (CheckBox) e.getSource();
            core.selectWeather(selectedWeather.isSelected(), selectedWeather.getText());
        }

    }

    @FXML
    protected void onSaveAs(ActionEvent e) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save File as");
        chooser.getExtensionFilters().add(new ExtensionFilter("JSON File", "*.json"));
        File currentFile = chooser.showSaveDialog(anchorPane.getScene().getWindow());
        if (currentFile != null) {
            core.saveAs(currentFile.getPath());
        }
    }

    @FXML
    protected void onLoad(ActionEvent e) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Save File");
        chooser.getExtensionFilters().add(new ExtensionFilter("JSON File", "*.json"));
        File file = chooser.showOpenDialog(anchorPane.getScene().getWindow());
        if (file != null) {
            core.reloadSaveFile(file.getPath());
            reload();
        }
    }

    @FXML
    protected void onSave(ActionEvent e) {
        if (core.getSaveFilePath() != null)
            core.saveAs(core.getSaveFilePath());
        else {
            onSaveAs(e);
        }
    }

    @FXML
    protected void onClose(ActionEvent e) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onHelp(ActionEvent e) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Random Train Sim World");
        alert.setHeaderText("Random picker for Train Sim World");
        alert.setContentText("Homepage: https://github.com/FreddyYJ/RandomTrainSimWorld");

        alert.showAndWait();
    }

    @FXML
    protected void onShortcut(KeyEvent e) {
        if (e.isShortcutDown()) {
            if (e.getCode() == KeyCode.S) {
                onSave(null);
            }
        }
    }

    public void reload() {
        ArrayList<Route> unselectedRoutes = core.getUnselectedRoute();
        for (int i = 0; i < this.routes.size(); i++) {
            if (unselectedRoutes.contains(core.getRoute(((CheckBox) this.routes.get(i)).getText()))) {
                ((CheckBox) this.routes.get(i)).setSelected(false);
            } else {
                ((CheckBox) this.routes.get(i)).setSelected(true);
            }
        }

        ArrayList<ArrayList<Locomotive>> unselectedLocos = core.getUnselectedLoco();
        for (int i = 0; i < unselectedLocos.size(); i++) {
            for (int j = 0; j < this.locos.get(i).size(); j++) {
                if (unselectedLocos.get(i).contains(core.getLocomotive(((CheckBox) this.locos.get(i).get(j)).getText(), ((CheckBox) this.routes.get(i)).getText()))) {
                    ((CheckBox) this.locos.get(i).get(j)).setSelected(false);
                } else {
                    ((CheckBox) this.locos.get(i).get(j)).setSelected(true);
                }

            }
        }

        ArrayList<Weather> unselectedWeathers = core.getUnselectedWeather();
        for (int i = 0; i < this.weathers.size(); i++) {
            if (unselectedWeathers.contains(core.getWeather(((CheckBox) this.weathers.get(i)).getText()))) {
                ((CheckBox) this.weathers.get(i)).setSelected(false);
            } else {
                ((CheckBox) this.weathers.get(i)).setSelected(true);
            }
        }

    }

    protected VBox getLocoBoxByID(String routeId) {
        List<Node> loco = boxLoco.getChildren();
        String locoId;
        String[] id = routeId.split("check");
        locoId = id[1];
        locoId = "box" + locoId + "Locos";
        for (int i = 0; i < loco.size(); i++) {
            if (loco.get(i).getId().equals(locoId))
                return (VBox) loco.get(i);
        }
        return null;
    }

    protected CheckBox getRouteByVBox(VBox locoId) {
        String id;
        id = (locoId.getId().split("box"))[1];
        id = (id.split("Locos"))[0];
        id = "check" + id;
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getId().equals(id))
                return (CheckBox) routes.get(i);
        }
        return null;
    }

    protected CheckBox getLocoByName(String name, int routeId) {
        List<Node> locoList = locos.get(routeId);
        for (int i = 0; i < locoList.size(); i++) {
            if (((CheckBox) locoList.get(i)).getText().equals(name))
                return (CheckBox) locoList.get(i);
        }
        return null;
    }

    protected CheckBox getWeatherByName(String name) {
        for (int i = 0; i < weathers.size(); i++) {
            if (((CheckBox) weathers.get(i)).getText().equals(name))
                return (CheckBox) weathers.get(i);
        }
        return null;
    }
}
