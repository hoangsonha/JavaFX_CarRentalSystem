package com.hsf301.controller;


import java.io.IOException;
import hsf301.hsh.pojo.Customer;
import hsf301.hsh.service.CustomerService;
import hsf301.hsh.service.ICustomerService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField txtEmail;
	@FXML
	private PasswordField txtPassword;
	
	private ICustomerService iCustomerService = null;

	public LoginController() {
		if(iCustomerService == null) {
			iCustomerService = new CustomerService("hibernate.cfg.xml");
		}
	}
	
	@FXML public void login() throws IOException {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		
		Customer customer = iCustomerService.checkLogin(txtEmail.getText(), txtPassword.getText());
		if(customer != null) {			
			if(customer.getAccount().getRole() == 1) {	
				CustomerLogin.customerLogin = customer;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Dashboard.fxml"));
				Parent root = fxmlLoader.load();	
				Stage stage = new Stage();	
				stage.setScene(new Scene(root));
				stage.show();
			} else if(customer.getAccount().getRole() == 2) {
				CustomerLogin.customerLogin = customer;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HomePage.fxml"));
				Parent root = fxmlLoader.load();	
				Stage stage = new Stage();	
				stage.setScene(new Scene(root));
				stage.show();
			} else {
				alert.setContentText("Your role is not correct!!!");
				alert.show();
			}
		} else {
			alert.setContentText("Your email or password is not correct!!!");
			alert.show();
		}
		
	}
	
	@FXML public void cancel() {
		Platform.exit();
	}

	
}
