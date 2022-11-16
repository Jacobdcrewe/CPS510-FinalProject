/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup.controller;

import customers.model.customersDAO;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jdleg
 */
public class SignupViewController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField cardNo;
    
    
    @FXML
    private void createAccount(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!username.getText().equals("") && !password.getText().equals("") && cardNo.getText().length() == 16) {
            try {
                customersDAO.insertCustomer(username.getText(), password.getText(), cardNo.getText());
                username.clear();
                password.clear();
                cardNo.clear();
                System.out.println("Customer has been added.");
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (SQLException e) {
                System.out.println("Exception occurred in Insertion " + e);
                e.printStackTrace();
                throw e;
            }
        } else if (username.getText().equals("") || password.getText().equals("") || cardNo.getText().equals("")) {
            System.out.println("Please fill in the username, password, and card number.");
        } else if (cardNo.getText().length() < 16 || cardNo.getText().length() > 16) {
            System.out.println("Please input a 16 digit card number.");
        } else {
            System.out.println("Error");
        }
    }
    
}
