package com.hsf301.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DashboardController {

	@FXML Button btnCustomer;
	@FXML Button btnReport;
	@FXML Button btnCar;
	@FXML Button btnSettings;
	@FXML Button btnCarRental;
	@FXML Button btnClasses;
	
	@FXML Label txtWelcome;
	
	public void initialize() {
		txtWelcome.setText("Welcome: " + CustomerLogin.customerLogin.getCustomerName());	
	}

	@FXML public void goCustomerManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CustomerManager.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	
	@FXML public void goCarManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CarManager.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML public void goCarRentalManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CarRentalManager.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML public void goReport() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Report.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML public void goReviewManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ReviewManager.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
