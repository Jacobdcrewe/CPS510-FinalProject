/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package options.controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import options.model.optionsDAO;

/**
 * FXML Controller class
 *
 * @author jdleg
 */
public class OptionsViewController { 

    @FXML
    private TextArea output;

    @FXML
    private void drop(ActionEvent event) throws ClassNotFoundException, SQLException {
        String[] sql = optionsDAO.dropTables();
        output.clear();
        for (String sql1 : sql) {
            output.appendText(sql1 + "\n\n");
        }
    }

    @FXML
    private void create(ActionEvent event) throws ClassNotFoundException, SQLException {
        String[] sql = optionsDAO.createTables();
        output.clear();
        for (String sql1 : sql) {
            output.appendText(sql1 + "\n\n");
        }
    }

    @FXML
    private void populate(ActionEvent event) throws ClassNotFoundException, SQLException {
        String[] sql = optionsDAO.populateTables();
        output.clear();
        for (String sql1 : sql) {
            output.appendText(sql1 + "\n\n");
        }
    }

    @FXML
    private void query(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<String> out = optionsDAO.within14Days();
        output.setText("All trips within 14 days\n");
        for(int i = 0; i< out.size(); i++) {
            output.appendText(out.get(i)+"\n");
        }
        
        out = optionsDAO.countOfTrips();
        output.appendText("\nNumber of bookings for each trip\n");
        for(int i = 0; i< out.size(); i++) {
            output.appendText(out.get(i)+"\n");
        }
        
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
    
}
