/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectionPage.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jdleg
 */
public class SelectionPageViewController {

    @FXML
    private Button createBookingBt;
    @FXML
    private Button cancelBookingBt;
    @FXML
    private Button tourBookingBt;
    @FXML
    private Button customersBt;
    @FXML
    private Button tableMgmtBt;
    @FXML
    private Button backBt;
 
    @FXML
    private void createBooking(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/bookings/controller/bookingsView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Bookings");
        primaryStage.setScene(scene);
        primaryStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void cancelBooking(ActionEvent event) {
    }

    @FXML
    private void tourBooking(ActionEvent event) {
    }

    @FXML
    private void customersManager(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/customers/controller/customersView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Customers");
        primaryStage.setScene(scene);
        primaryStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/login/controller/loginView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void tablesMgmt(ActionEvent event) {
    }
    
}
