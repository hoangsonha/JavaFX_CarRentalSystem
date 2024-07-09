package com.hsf301.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import hsf301.hsh.pojo.Account;
import hsf301.hsh.pojo.Car;
import hsf301.hsh.pojo.CarProducer;
import hsf301.hsh.pojo.CarRental;
import hsf301.hsh.pojo.Customer;
import hsf301.hsh.pojo.Review;
import hsf301.hsh.service.CarRentalService;
import hsf301.hsh.service.CarService;
import hsf301.hsh.service.CustomerService;
import hsf301.hsh.service.ICarRentalService;
import hsf301.hsh.service.ICarService;
import hsf301.hsh.service.ICustomerService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;


public class ReviewController implements Initializable {
	
	@FXML private TableView<Review> tbData;
	
	@FXML public TableColumn<Review, Integer> carReviewID;
	@FXML public TableColumn<Review, Customer> customerID;
	@FXML public TableColumn<Review, Car> carID;
	@FXML public TableColumn<Review, String> reviewStar;
	@FXML public TableColumn<Review, String> comment;
	
	private ICarRentalService iCarRentalService;
	
	private ObservableList<Review> reviewModels;
	
	public ReviewController() {	
		iCarRentalService = new CarRentalService("hibernate.cfg.xml");
		reviewModels = FXCollections.observableArrayList(iCarRentalService.findAllReviews());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carReviewID.setCellValueFactory(new PropertyValueFactory<>("id"));
		customerID.setCellValueFactory(new PropertyValueFactory<>("customer"));
		carID.setCellValueFactory(new PropertyValueFactory<>("car"));
		reviewStar.setCellValueFactory(new PropertyValueFactory<>("reviewStar"));
		comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
		
		customerID.setCellFactory(column -> {
		    return new TableCell<Review, Customer>() {
		        @Override
		        protected void updateItem(Customer customer, boolean empty) {
		            super.updateItem(customer, empty);
		            if (customer == null || empty) {
		                setText(null);
		            } else {
		                setText(customer.getCustomerID());
		            }
		        }
		    };
		});
		
		carID.setCellFactory(column -> {
		    return new TableCell<Review, Car>() {
		        @Override
		        protected void updateItem(Car car, boolean empty) {
		            super.updateItem(car, empty);
		            if (car == null || empty) {
		                setText(null);
		            } else {
		                setText(car.getCarID());
		            }
		        }
		    };
		});
		
		tbData.setItems(reviewModels);
	
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	
}
