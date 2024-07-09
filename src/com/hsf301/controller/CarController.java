package com.hsf301.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import hsf301.hsh.pojo.Account;
import hsf301.hsh.pojo.Car;
import hsf301.hsh.pojo.CarProducer;
import hsf301.hsh.pojo.Customer;
import hsf301.hsh.service.CarService;
import hsf301.hsh.service.CustomerService;
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


public class CarController implements Initializable {
	
	

	@FXML private TableView<Car> tbData;
	
	@FXML public TableColumn<Car, String> carID;
	@FXML public TableColumn<Car, String> carName;
	@FXML public TableColumn<Car, String> modelYear;
	@FXML public TableColumn<Car, String> capacity;
	@FXML public TableColumn<Car, String> color;
	@FXML public TableColumn<Car, Date> importDate;
	@FXML public TableColumn<Car, CarProducer> producerID;
	@FXML public TableColumn<Car, String> description;
	@FXML public TableColumn<Car, Double> rentPrice;
	@FXML public TableColumn<Car, String> status;
	
	@FXML public TextField txtCarID;
	@FXML public TextField txtModelYear;
	@FXML public TextField txtColor;
	@FXML public TextField txtImportDate;
	@FXML public TextField txtRentPrice;
	@FXML public TextField txtName;
	@FXML public TextField txtCapacity;
	@FXML public TextField txtDescription;
	@FXML public TextField txtProducerID;
	@FXML public TextField txtStatus;
	
	private String ID;
	
	private ICarService iCarService;
	
	private ObservableList<Car> carModels;
	
	public CarController() {
		
		iCarService = new CarService("hibernate.cfg.xml");
		
		carModels = FXCollections.observableArrayList(iCarService.findAll());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carID.setCellValueFactory(new PropertyValueFactory<>("CarID"));
		carName.setCellValueFactory(new PropertyValueFactory<>("carName"));
		modelYear.setCellValueFactory(new PropertyValueFactory<>("carModelYear"));
		capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		color.setCellValueFactory(new PropertyValueFactory<>("color"));
		importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
		producerID.setCellValueFactory(new PropertyValueFactory<>("producerID"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));	
		producerID.setCellFactory(column -> {
		    return new TableCell<Car, CarProducer>() {
		        @Override
		        protected void updateItem(CarProducer producer, boolean empty) {
		            super.updateItem(producer, empty);
		            if (producer == null || empty) {
		                setText(null);
		            } else {
		                setText(producer.getProducerID());
		            }
		        }
		    };
		});
		
		tbData.setItems(carModels);
		
		tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observalueValue, Object oldValue, Object index) {
				if(tbData.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tbData.getSelectionModel();
					ObservableList selectedcells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedcells.get(0);
					
					Object carID = tablePosition.getTableColumn().getCellData(index);
					try {
						Car car = iCarService.findById(carID.toString());
						showCar(car);
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
	
	public void showCar(Car car) {
		this.setIdCar(car.getCarID());
		this.txtCarID.setText(car.getCarID());
		this.txtModelYear.setText(car.getCarModelYear());
		this.txtColor.setText(car.getColor());
		this.txtRentPrice.setText(String.valueOf(car.getRentPrice()));
		this.txtImportDate.setText(String.valueOf(car.getImportDate()));
		this.txtName.setText(car.getCarName());
		this.txtCapacity.setText(car.getCapacity());
		this.txtDescription.setText(car.getDescription());
		this.txtProducerID.setText(car.getProducerID().getProducerID());
		this.txtStatus.setText(car.getStatus());

	}
	
	private void refreshDataTable() {
		this.txtCarID.setText("");
		this.txtModelYear.setText("");
		this.txtColor.setText("");
		this.txtRentPrice.setText("");
		this.txtImportDate.setText("");
		this.txtName.setText("");
		this.txtCapacity.setText("");
		this.txtDescription.setText("");
		this.txtProducerID.setText("");
		this.txtStatus.setText("");

		carModels = FXCollections.observableArrayList(iCarService.findAll());
		tbData.setItems(carModels);
	}
	
	@FXML
	public void addCar() throws ParseException {
		try {
			if(!this.txtCarID.getText().equals("") || !this.txtModelYear.getText().equals("") 
					|| !this.txtColor.getText().equals("") || !this.txtRentPrice.getText().equals("")
					|| !this.txtImportDate.getText().equals("") || !this.txtName.getText().equals("")
					|| !this.txtCapacity.getText().equals("") || !this.txtDescription.getText().equals("")
					|| !this.txtProducerID.getText().equals("") || !this.txtStatus.getText().equals("")) {
				
				CarProducer producer = iCarService.findCarProducerById(txtProducerID.getText()); 
				
				SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
				Date importdate = spd.parse(txtImportDate.getText());
				
				Car car = new Car(txtCarID.getText(), txtName.getText(), txtModelYear.getText(), txtColor.getText(), txtCapacity.getText(), txtDescription.getText(), importdate , producer, Double.parseDouble(txtRentPrice.getText()), txtStatus.getText());
				iCarService.save(car);
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
	public void deleteCar() {
		iCarService.delete(this.getId());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Delete successfully");
		alert.show();
		refreshDataTable();
	}
	
	@FXML
	public void updateCar() throws ParseException {
		Car car = iCarService.findById(this.ID);
		if(car != null) {
			car.setCarProducerID(car.getProducerID());
			car.setCarID(car.getCarID());	

			SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
			Date importdate = spd.parse(txtImportDate.getText());
			
			car.setCapacity(txtCapacity.getText());
			car.setCarModelYear(txtModelYear.getText());
			car.setCarName(txtName.getText());
			car.setColor(txtColor.getText());
			car.setDescription(txtDescription.getText());
			car.setImportDate(importdate);
			car.setRentPrice(Double.parseDouble(txtRentPrice.getText()));
			car.setStatus(txtStatus.getText());
	
			iCarService.update(car);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Update successfully");
			alert.show();
		}
		refreshDataTable();
	}
	
	public String getId() {
		return ID;
	}
	
	public void setIdCar(String id) {
		this.ID = id;
	}

}
