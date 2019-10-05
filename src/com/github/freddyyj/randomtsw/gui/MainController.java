package com.github.freddyyj.randomtsw.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {
	@FXML
	private Button button1;
	@FXML
	private TextArea textarea;
	@FXML
	private void setText()
	{
		textarea.setText("Success!");
	}
	public MainController() {}
	@FXML
	private void initialize() {}
}
