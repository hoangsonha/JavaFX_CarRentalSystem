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


public class HistoryController implements Initializable {
	
	@FXML private TableView<CarRental> tbData;
	
	@FXML public TableColumn<CarRental, String> carRentalID;
	@FXML public TableColumn<CarRental, Customer> customerID;
	@FXML public TableColumn<CarRental, Car> carID;
	@FXML public TableColumn<CarRental, String> rentPrice;
	@FXML public TableColumn<CarRental, String> status;
	@FXML public TableColumn<CarRental, Date> pickupDate;
	@FXML public TableColumn<CarRental, Date> returnDate;
	
	@FXML public TextField txtStartDate;
	@FXML public TextField txtEndDate;
	
	private ICarRentalService iCarRentalService;
	
	private ObservableList<CarRental> carRentalModels;
	
	public HistoryController() {	
		iCarRentalService = new CarRentalService("hibernate.cfg.xml");
		carRentalModels = FXCollections.observableArrayList(iCarRentalService.getAllBySearchCustomerIDAll(CustomerLogin.customerLogin.getCustomerID()));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		carRentalID.setCellValueFactory(new PropertyValueFactory<>("carRentalID"));
		customerID.setCellValueFactory(new PropertyValueFactory<>("customer"));
		carID.setCellValueFactory(new PropertyValueFactory<>("car"));
		rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		pickupDate.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
		returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		
		customerID.setCellFactory(column -> {
		    return new TableCell<CarRental, Customer>() {
		        @Override
		        protected void updateItem(Customer customer, boolean empty) {
		            super.updateItem(customer, empty);
		            if (customer == null || empty) {
		                setText(null);
		            } else {
		                setText(customer.getCustomerName());
		            }
		        }
		    };
		});
		
		carID.setCellFactory(column -> {
		    return new TableCell<CarRental, Car>() {
		        @Override
		        protected void updateItem(Car car, boolean empty) {
		            super.updateItem(car, empty);
		            if (car == null || empty) {
		                setText(null);
		            } else {
		                setText(car.getCarName());
		            }
		        }
		    };
		});
		
		tbData.setItems(carRentalModels);
	
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	
	
	private void refreshDataTable() {
		this.txtEndDate.setText("");
		this.txtStartDate.setText("");

		carRentalModels = FXCollections.observableArrayList(iCarRentalService.getAllBySearchCustomerIDAll(CustomerLogin.customerLogin.getCustomerID()));
		tbData.setItems(carRentalModels);
	}
	
	public void search() throws ParseException {
		SimpleDateFormat spm = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = spm.parse(txtStartDate.getText());
		Date endDate = spm.parse(txtEndDate.getText());
		
		carRentalModels = FXCollections.observableArrayList(iCarRentalService.getAllBySearchCustomerID(startDate, endDate, CustomerLogin.customerLogin.getCustomerID()));
		tbData.setItems(carRentalModels);
	}

	
	
}
