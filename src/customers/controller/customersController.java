/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.controller;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import customers.model.Customers;
import customers.model.customersDAO;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jdleg
 */
public class customersController{

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField cardNo;
    @FXML
    private TextArea output;
    @FXML
    private TextField customerID;
    @FXML
    private TextField newPw;
    @FXML
    private TextField newCard;
    @FXML
    private TextField newPoints;
    
    //creating the columns names and data types for table
    @FXML
    private TableColumn<Customers, Integer> colCustID;
    @FXML
    private TableColumn<Customers, String> colUsername;
    @FXML
    private TableColumn<Customers, String> colPassword;
    @FXML
    private TableColumn<Customers, String> colCardNo;
    @FXML
    private TableColumn<Customers, Integer> colPoints;
    
    //create table
    @FXML
    private TableView customerTable;



    @FXML
    //insert the customer function. calls the customersDAO insert customer method
    private void insertCustomer(javafx.event.ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!username.getText().equals("") && !password.getText().equals("") && cardNo.getText().length() == 16) {
            try {
                customersDAO.insertCustomer(username.getText(), password.getText(), cardNo.getText());
                username.clear();
                password.clear();
                cardNo.clear();
                output.setText("Customer has been added.");
                ObservableList<Customers> custList = customersDAO.getAllRecords();
                populateTable(custList);
            } catch (SQLException e) {
                output.setText("Exception occurred in Insertion " + e);
                e.printStackTrace();
                throw e;
            }
        } else if (username.getText().equals("") || password.getText().equals("") || cardNo.getText().equals("")) {
            output.setText("Please fill in the username, password, and card number.");
        } else if (cardNo.getText().length() < 16 || cardNo.getText().length() > 16) {
            output.setText("Please input a 16 digit card number.");
        } else {
            output.setText("Error");
        }

    }

    @FXML
    //clears textfields above the clear button
    private void clearText(ActionEvent event) {
        username.clear();
        password.clear();
        cardNo.clear();
        output.setText("Textboxes cleared");
    }

    @FXML
    //creates a list of values found in the search customers method from customersDAO
    private void searchCustomer(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Customers> list = customersDAO.searchCustomers(customerID.getText());
        //calls populateTable function
        populateTable(list);
        customerID.clear();
    }

    @FXML
    private void updatePw(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(customerID.getText().matches("[0-9]+") && !newPw.getText().equals("")) {
            try {
            customersDAO.updatePassword(Integer.parseInt(customerID.getText()), newPw.getText());
            customerID.clear();
            newPw.clear();
            output.setText("Password has been updated.");
            ObservableList<Customers> custList = customersDAO.getAllRecords();
            populateTable(custList);
            } catch (SQLException e) {
                output.setText("Exception occurred in updatePassword " + e);
                e.printStackTrace();
                throw e;
            }
        } else {
            output.setText("Please input an integer for customer ID and a value for the new password");
        }
        
    }

    @FXML
    private void updateCardNo(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(customerID.getText().matches("[0-9]+") && newCard.getText().length() == 16) {
            try {
            customersDAO.updateCard(Integer.parseInt(customerID.getText()), newCard.getText());
            customerID.clear();
            newCard.clear();
            output.setText("Card Number has been updated.");
            ObservableList<Customers> custList = customersDAO.getAllRecords();
            populateTable(custList);
            } catch (SQLException e) {
                output.setText("Exception occurred in updateCardNo " + e);
                e.printStackTrace();
                throw e;
            }
        } else {
            output.setText("Please input an integer for customer ID and a 16 digit card number.");
        }
    }

    @FXML
    private void delCustomer(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(customerID.getText().matches("[0-9]+")) {
            try {
            customersDAO.deleteCustomer(Integer.parseInt(customerID.getText()));
            customerID.clear();
            output.setText("Customer has been deleted.");
            ObservableList<Customers> custList = customersDAO.getAllRecords();
            populateTable(custList);
            } catch (SQLException e) {
                output.setText("Exception occurred in delCustomer " + e);
                e.printStackTrace();
                throw e;
            }
        } else {
            output.setText("Please input an integer for customer ID");
        }
    }

    @FXML
    private void searchAllCustomer(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Customers> custList = customersDAO.getAllRecords();
            populateTable(custList); 
        } catch (SQLException e) {
                output.setText("Exception occurred in searchAllCustomer " + e);
                e.printStackTrace();
                throw e;
        }
        
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage customerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/selectionPage/controller/selectionPageView.fxml"));
        Scene scene = new Scene(root);
        customerStage.setTitle("Selection Page");
        customerStage.setScene(scene);
        customerStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
    }
    
    @FXML
    private void initialize() throws Exception {
        colCustID.setCellValueFactory(cellData -> cellData.getValue().getCustomerId().asObject());
        colUsername.setCellValueFactory(cellData -> cellData.getValue().getCustomerUsername());
        colPassword.setCellValueFactory(cellData -> cellData.getValue().getCustomerPassword());
        colCardNo.setCellValueFactory(cellData -> cellData.getValue().getCustomerCardNo());
        colPoints.setCellValueFactory(cellData -> cellData.getValue().getCustomerPoints().asObject());
        ObservableList<Customers> custList = customersDAO.getAllRecords();
        populateTable(custList);
        
    }

    private void populateTable(ObservableList<Customers> custList) {
        customerTable.setItems(custList);
    }

    @FXML
    private void points(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(customerID.getText().matches("[0-9]+") && newPoints.getText().matches("[0-9]+")) {
            try {
            customersDAO.updatePoints(Integer.parseInt(customerID.getText()), Integer.parseInt(newPoints.getText()));
            customerID.clear();
            newPoints.clear();
            output.setText("Points have been updated.");
            ObservableList<Customers> custList = customersDAO.getAllRecords();
            populateTable(custList);
            } catch (SQLException e) {
                output.setText("Exception occurred in updateCardNo " + e);
                e.printStackTrace();
                throw e;
            }
        } else {
            output.setText("Please input an integer for customer ID and an integer for points");
        }
    }

}
