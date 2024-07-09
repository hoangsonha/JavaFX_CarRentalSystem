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


public class CarRentalController implements Initializable {
	
	@FXML private TableView<CarRental> tbData;
	
	@FXML public TableColumn<CarRental, String> carRentalID;
	@FXML public TableColumn<CarRental, Customer> customerID;
	@FXML public TableColumn<CarRental, Car> carID;
	@FXML public TableColumn<CarRental, String> rentPrice;
	@FXML public TableColumn<CarRental, String> status;
	@FXML public TableColumn<CarRental, Date> pickupDate;
	@FXML public TableColumn<CarRental, Date> returnDate;
	
	@FXML public TextField txtCarRentalID;
	@FXML public TextField txtCarID;
	@FXML public TextField txtCustomerID;
	@FXML public TextField txtPickupDate;
	@FXML public TextField txtRentPrice;
	@FXML public TextField txtStatus;
	@FXML public TextField txtReturnDate;

	
	private int ID;
	
	private ICarRentalService iCarRentalService;
	private ICarService iCarService;
	private ICustomerService iCustomerService;
	
	private ObservableList<CarRental> carRentalModels;
	
	public CarRentalController() {
		
		iCarRentalService = new CarRentalService("hibernate.cfg.xml");
		iCarService = new CarService("hibernate.cfg.xml");
		iCustomerService = new CustomerService("hibernate.cfg.xml");
		carRentalModels = FXCollections.observableArrayList(iCarRentalService.findAll());
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
		                setText(customer.getCustomerID());
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
		                setText(car.getCarID());
		            }
		        }
		    };
		});
		
		tbData.setItems(carRentalModels);
		
		tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observalueValue, Object oldValue, Object index) {
				if(tbData.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tbData.getSelectionModel();
					ObservableList selectedcells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedcells.get(0);
					
					Object carRentalID = tablePosition.getTableColumn().getCellData(index);
					try {
						CarRental carRental = iCarRentalService.findById(Integer.parseInt(carRentalID.toString()));
						showCarRental(carRental);
					} catch(Exception e) {
						showAlert("Information Board !", "Please choose the First Cell !");
					}
				}
				
			}
		});
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public void showCarRental(CarRental carRental) {
		
		this.setIdCarRental(carRental.getCarRentalID());
		
		this.txtCarRentalID.setText(String.valueOf(carRental.getCarRentalID()));
		this.txtCarID.setText(carRental.getCar().getCarID());
		this.txtCustomerID.setText(carRental.getCustomer().getCustomerID());
		this.txtRentPrice.setText(String.valueOf(carRental.getRentPrice()));
		this.txtPickupDate.setText(String.valueOf(carRental.getPickupDate()));
		this.txtReturnDate.setText(String.valueOf(carRental.getReturnDate()));
		this.txtStatus.setText(carRental.getStatus());

	}
	
	private void refreshDataTable() {
		this.txtCarRentalID.setText("");
		this.txtCarID.setText("");
		this.txtCustomerID.setText("");
		this.txtPickupDate.setText("");
		this.txtRentPrice.setText("");
		this.txtStatus.setText("");
		this.txtReturnDate.setText("");

		carRentalModels = FXCollections.observableArrayList(iCarRentalService.findAll());
		tbData.setItems(carRentalModels);
	}
	
	@FXML
	public void addCarRental() throws ParseException {
		try {
			if(!this.txtCarRentalID.getText().equals("") || !this.txtCarID.getText().equals("") 
					|| !this.txtCustomerID.getText().equals("") || !this.txtPickupDate.getText().equals("")
					|| !this.txtRentPrice.getText().equals("") || !this.txtStatus.getText().equals("")
					|| !this.txtReturnDate.getText().equals("")) {
				
				Customer customer = iCustomerService.findById(txtCustomerID.getText());
				Car car = iCarService.findById(txtCarID.getText());
				
				SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
				Date pickerdate = spd.parse(txtPickupDate.getText());
				Date returndate = spd.parse(txtReturnDate.getText());
				
				if(pickerdate.before(returndate)) {
					CarRental carRental = new CarRental(Integer.parseInt(txtCarRentalID.getText()), customer, car, pickerdate, returndate, Double.parseDouble(txtRentPrice.getText()), txtStatus.getText());
					iCarRentalService.save(carRental);
					refreshDataTable();		
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Picker Date must be before Return Date");
					alert.show();
				}						
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please input full field");
				alert.show();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Cannot add this customer!!!");
			alert.show();
		}
	}
	
	@FXML
	public void deleteCarRental() {
		iCarRentalService.delete(this.getId());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Delete successfully");
		alert.show();
		refreshDataTable();
	}
	
	@FXML
	public void updateCarRental() throws ParseException {
		CarRental carRental = iCarRentalService.findById(this.ID);
		if(carRental != null) {
			carRental.setCarRentalID(carRental.getCarRentalID());

			SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
			Date pickerdate = spd.parse(txtPickupDate.getText());
			Date returndate = spd.parse(txtReturnDate.getText());
			
			if(pickerdate.before(returndate)) { 
				carRental.setCustomer(carRental.getCustomer());
				carRental.setCar(carRental.getCar());
				carRental.setRentPrice(Double.parseDouble(txtRentPrice.getText()));
				carRental.setStatus(txtStatus.getText());
				carRental.setReturnDate(returndate);
				carRental.setPickupDate(pickerdate);
		
				iCarRentalService.update(carRental);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Update successfully");
				alert.show();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Picker Date before Return Date, can not update");
				alert.show();
			}
		}
		refreshDataTable();
	}
	
	public int getId() {
		return ID;
	}
	
	public void setIdCarRental(int id) {
		this.ID = id;
	}

}
