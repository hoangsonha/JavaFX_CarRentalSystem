package com.hsf301.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import hsf301.hsh.pojo.Account;
import hsf301.hsh.pojo.Customer;
import hsf301.hsh.service.CustomerService;
import hsf301.hsh.service.ICustomerService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;


public class ProfileController implements Initializable{
	
	

//	@FXML private TableView<Customer> tbData;
	
//	@FXML public TableColumn<Customer, String> customerID;
//	@FXML public TableColumn<Customer, String> customerName;
//	@FXML public TableColumn<Customer, String> mobile;
//	@FXML public TableColumn<Customer, Date> birthday;
//	@FXML public TableColumn<Customer, String> identityCard;
//	@FXML public TableColumn<Customer, Integer> lienceNumber;
//	@FXML public TableColumn<Customer, Date> lienceDate;
//	@FXML public TableColumn<Customer, String> email;
//	@FXML public TableColumn<Customer, String> password;
//	@FXML public TableColumn<Customer, Account> accountID;
	
	@FXML public TextField txtIdentityCard;
	@FXML public TextField txtMobile;
	@FXML public TextField txtLienceNumber;
	@FXML public TextField txtLienceDate;
	@FXML public TextField txtEmail;
	@FXML public TextField txtPassword;
	@FXML public TextField txtName;
	@FXML public TextField txtBirthday;
	

	private String ID;
	
	private ICustomerService iCustomerService;
	
//	private ObservableList<Customer> customerModels;
	
	public ProfileController() {
		
		iCustomerService = new CustomerService("hibernate.cfg.xml");

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showCustomer(CustomerLogin.customerLogin);
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public void showCustomer(Customer customer) {
		setIdCustomer(customer.getCustomerID());
		this.txtIdentityCard.setText(customer.getIdentityCard());
		txtMobile.setText(customer.getMobile());
		txtLienceNumber.setText(String.valueOf(customer.getLicenceNumber()));
		txtLienceDate.setText(String.valueOf(customer.getLicenceDate()));
		txtEmail.setText(customer.getEmail());
		txtPassword.setText(customer.getPassword());
		txtName.setText(customer.getCustomerName());
		txtBirthday.setText(String.valueOf(customer.getBirthday()));
	}
	
	
	@FXML
	public void updateCustomer() throws ParseException {
		Customer customer = iCustomerService.findById(this.ID);
		if(customer != null) {
			customer.setAccount(customer.getAccount());
			customer.setCustomerID(customer.getCustomerID());	
			customer.setEmail(customer.getEmail());
			
			SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
			Date birthdate = spd.parse(txtBirthday.getText());
			Date licenceDate = spd.parse(txtLienceDate.getText());
			
			customer.setBirthday(birthdate);
			customer.setCustomerName(txtName.getText());		
			customer.setIdentityCard(txtIdentityCard.getText());
			customer.setLicenceDate(licenceDate);
			customer.setLicenceNumber(Integer.parseInt(txtLienceNumber.getText()));
			customer.setMobile(txtMobile.getText());
			customer.setPassword(txtPassword.getText());
	
			iCustomerService.update(customer);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Update successfully");
			alert.show();
		}
	}
	
	public String getId() {
		return ID;
	}
	
	public void setIdCustomer(String id) {
		this.ID = id;
	}

}
