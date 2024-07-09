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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;


public class CustomerController implements Initializable {
	
	

	@FXML private TableView<Customer> tbData;
	
	@FXML public TableColumn<Customer, String> customerID;
	@FXML public TableColumn<Customer, String> customerName;
	@FXML public TableColumn<Customer, String> mobile;
	@FXML public TableColumn<Customer, Date> birthday;
	@FXML public TableColumn<Customer, String> identityCard;
	@FXML public TableColumn<Customer, Integer> lienceNumber;
	@FXML public TableColumn<Customer, Date> lienceDate;
	@FXML public TableColumn<Customer, String> email;
	@FXML public TableColumn<Customer, String> password;
	@FXML public TableColumn<Customer, Account> accountID;
	
	@FXML public TextField txtAccountID;
	@FXML public TextField txtIdentityCard;
	@FXML public TextField txtMobile;
	@FXML public TextField txtLienceNumber;
	@FXML public TextField txtLienceDate;
	@FXML public TextField txtEmail;
	@FXML public TextField txtPassword;
	@FXML public TextField txtName;
	@FXML public TextField txtBirthday;
	@FXML public TextField txtCustomerID;
	
	private String ID;
	
	private ICustomerService iCustomerService;
	
	private ObservableList<Customer> customerModels;
	
	public CustomerController() {
		
		iCustomerService = new CustomerService("hibernate.cfg.xml");
		
		customerModels = FXCollections.observableArrayList(iCustomerService.findAll());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		customerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
		customerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
		mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
		birthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
		identityCard.setCellValueFactory(new PropertyValueFactory<>("IdentityCard"));
		lienceNumber.setCellValueFactory(new PropertyValueFactory<>("LicenceNumber"));
		lienceDate.setCellValueFactory(new PropertyValueFactory<>("LicenceDate"));
		email.setCellValueFactory(new PropertyValueFactory<>("Email"));
		password.setCellValueFactory(new PropertyValueFactory<>("Password"));
		accountID.setCellValueFactory(new PropertyValueFactory<>("Account"));		
		accountID.setCellFactory(column -> {
		    return new TableCell<Customer, Account>() {
		        @Override
		        protected void updateItem(Account account, boolean empty) {
		            super.updateItem(account, empty);
		            if (account == null || empty) {
		                setText(null);
		            } else {
		                setText(account.getAccountID());
		            }
		        }
		    };
		});
		
		tbData.setItems(customerModels);
		
		tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observalueValue, Object oldValue, Object index) {
				if(tbData.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tbData.getSelectionModel();
					ObservableList selectedcells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedcells.get(0);
					
					Object customerID = tablePosition.getTableColumn().getCellData(index);
					try {
						Customer cus = iCustomerService.findById(customerID.toString());
						showCustomer(cus);
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
	
	public void showCustomer(Customer customer) {
		this.setIdCustomer(customer.getCustomerID());
		this.txtAccountID.setText(customer.getAccount().getAccountID());
		this.txtIdentityCard.setText(customer.getIdentityCard());
		this.txtMobile.setText(customer.getMobile());
		this.txtLienceNumber.setText(String.valueOf(customer.getLicenceNumber()));
		this.txtLienceDate.setText(String.valueOf(customer.getLicenceDate()));
		this.txtEmail.setText(customer.getEmail());
		this.txtPassword.setText(customer.getPassword());
		this.txtName.setText(customer.getCustomerName());
		this.txtBirthday.setText(String.valueOf(customer.getBirthday()));
		this.txtCustomerID.setText(customer.getCustomerID());
	}
	
	private void refreshDataTable() {
		this.txtCustomerID.setText("");
		this.txtAccountID.setText("");
		this.txtIdentityCard.setText("");
		this.txtMobile.setText("");
		this.txtLienceNumber.setText("");
		this.txtLienceDate.setText("");
		this.txtEmail.setText("");
		this.txtPassword.setText("");
		this.txtName.setText("");
		this.txtBirthday.setText("");

		customerModels = FXCollections.observableArrayList(iCustomerService.findAll());
		tbData.setItems(customerModels);
	}
	
	@FXML
	public void addCustomer() throws ParseException {
		try {
			if(!this.txtCustomerID.getText().equals("") || !this.txtAccountID.getText().equals("") 
					|| !this.txtIdentityCard.getText().equals("") || !this.txtMobile.getText().equals("")
					|| !this.txtLienceNumber.getText().equals("") || !this.txtLienceDate.getText().equals("")
					|| !this.txtEmail.getText().equals("") || !this.txtPassword.getText().equals("")
					|| !this.txtName.getText().equals("") || !this.txtBirthday.getText().equals("")) {
				
				Account account = iCustomerService.getAccountByID(txtAccountID.getText()); 
				
				SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
				Date birthdate = spd.parse(txtBirthday.getText());
				Date licenceDate = spd.parse(txtLienceDate.getText());
				
				Customer customer = new Customer(txtCustomerID.getText(), txtName.getText(), txtMobile.getText(), birthdate, txtIdentityCard.getText(), Integer.parseInt(txtLienceNumber.getText()), licenceDate, txtEmail.getText(), txtPassword.getText(), account);
				iCustomerService.save(customer);
				refreshDataTable();		
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
	public void deleteCustomer() {
		iCustomerService.delete(this.getId());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Delete successfully");
		alert.show();
		refreshDataTable();
	}
	
	@FXML
	public void updateCustomer() throws ParseException {
		Customer customer = iCustomerService.findById(this.ID);
		if(customer != null) {
			customer.setAccount(customer.getAccount());
			customer.setCustomerID(customer.getCustomerID());	

			SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
			Date birthdate = spd.parse(txtBirthday.getText());
			Date licenceDate = spd.parse(txtLienceDate.getText());
			
			customer.setBirthday(birthdate);
			customer.setCustomerName(txtName.getText());
			customer.setEmail(txtEmail.getText());
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
		refreshDataTable();
	}
	
	public String getId() {
		return ID;
	}
	
	public void setIdCustomer(String id) {
		this.ID = id;
	}

}
