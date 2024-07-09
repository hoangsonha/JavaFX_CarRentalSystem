package com.hsf301.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HomePageController {

	@FXML Label txtWelcome;
	
	@FXML public void goProfileManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ProfileManager.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	
	@FXML public void goHistoryManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HistoryManager.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		System.out.println("Da in : " + CustomerLogin.customerLogin.getCustomerName());
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void goReview() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Review.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		System.out.println("Da in : " + CustomerLogin.customerLogin.getCustomerName());
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void initialize() {
		txtWelcome.setText("Welcome: " + CustomerLogin.customerLogin.getCustomerName());
		
	}
	
}
