/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.controller;

import customers.model.Customers;
import customers.model.customersDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jdleg
 */
public class LoginViewController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button signUpBt;
    @FXML
    private Button exitBt;
    @FXML
    private Button loginBt;



    @FXML
    private void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        try {
            ObservableList<Customers> custList = customersDAO.getAllRecords();
            boolean admin = false;
            boolean user = false;
            for(int i = 0; i < custList.size(); i++) {
                if(username.getText().equals("admin") && password.getText().equals("admin")) {
                    admin = true;
                    break;
                } else if(custList.get(i).getUsername().equals(username.getText()) && custList.get(i).getPassword().equals(password.getText())) {
                    user = true;
                    break;
                }
            }
            
            if(admin) {
                Stage customerStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/selectionPage/controller/selectionPageView.fxml"));
                Scene scene = new Scene(root);
                customerStage.setTitle("Selection Page");
                customerStage.setScene(scene);
                customerStage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            
            if(user) {
                System.out.println("User is signing in!");
            }
            
        } catch(IOException e) {
            System.out.println("Error in login funciton " + e);
            e.printStackTrace();
            throw e;
        } catch(SQLException e) {
            System.out.println("Error in sql in login funciton " + e);
        }
        
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void signUp(ActionEvent event) throws IOException {
        Stage customerStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/signup/controller/signupView.fxml"));
            Scene scene = new Scene(root);
            customerStage.setTitle("Create Account");
            customerStage.setScene(scene);
            customerStage.show();
    }

    @FXML
    private void openOptions(ActionEvent event) throws IOException {
        Stage customerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/options/controller/optionsView.fxml"));
        Scene scene = new Scene(root);
        customerStage.setTitle("Table Management");
        customerStage.setScene(scene);
        customerStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    
}
