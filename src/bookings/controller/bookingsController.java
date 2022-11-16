/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookings.controller;

import bookings.model.Bookings;
import bookings.model.bookingsDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author jdleg
 */
public class bookingsController {

    @FXML
    private TextField departure;
    @FXML
    private TextField destination;
    @FXML
    private TextField modeOfTransport;
    @FXML
    private TextArea output;
    @FXML
    private TextField serviceNo;
    @FXML
    private TextField newDep;
    @FXML
    private TextField newDestination;
    @FXML
    private TextField newTransport;
    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn<Bookings, Integer> colSerNo;
    @FXML
    private TableColumn<Bookings, String> colDep;
    @FXML
    private TableColumn<Bookings, String> colDest;
    @FXML
    private TableColumn<Bookings, String> colTransport;
    @FXML
    private TableColumn<Bookings, Integer> colReturnNo;
    @FXML
    private Button clrBt;
    @FXML
    private Button addBt;
    @FXML
    private Button searchBt;
    @FXML
    private Button backBt;


    @FXML
    private void clearText(ActionEvent event) {
        destination.clear();
        departure.clear();
        modeOfTransport.clear();
    }

    @FXML
    private void insertBooking(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
                bookingsDAO.insertBooking(departure.getText(), destination.getText(), modeOfTransport.getText());
                departure.clear();
                destination.clear();
                modeOfTransport.clear();
                output.setText("Booking has been added.");
                ObservableList<Bookings> bookList = bookingsDAO.getAllRecords();
                populateTable(bookList);
            } catch (SQLException e) {
                output.setText("Exception occurred in Insertion " + e);
                e.printStackTrace();
                throw e;
            }
    }

    @FXML
    private void searchBookings(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Bookings> list = bookingsDAO.searchBookings(serviceNo.getText());
        //calls populateTable function
        populateTable(list);
        serviceNo.clear();
    }

    @FXML
    private void showAll(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Bookings> list = bookingsDAO.getAllRecords();
        populateTable(list);
    }
    @FXML
    private void updateDep(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            bookingsDAO.updateDep(Integer.parseInt(serviceNo.getText()), newDep.getText());
            serviceNo.clear();
            newDep.clear();
            output.setText("Departure has been updated.");
            ObservableList<Bookings> list = bookingsDAO.getAllRecords();
            populateTable(list);
        } catch (SQLException e) {
            output.setText("Exception occurred in updatePassword " + e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void updateDest(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            bookingsDAO.updateDest(Integer.parseInt(serviceNo.getText()), newDestination.getText());
            serviceNo.clear();
            newDestination.clear();
            output.setText("Destination has been updated.");
            ObservableList<Bookings> list = bookingsDAO.getAllRecords();
            populateTable(list);
        } catch (SQLException e) {
            output.setText("Exception occurred in updateDest " + e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void delBooking(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(serviceNo.getText().matches("[0-9]+")) {
            try {
            bookingsDAO.deleteBooking(Integer.parseInt(serviceNo.getText()));
            serviceNo.clear();
            output.setText("Booking has been deleted.");
            ObservableList<Bookings> list = bookingsDAO.getAllRecords();
            populateTable(list);
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
    private void getReturnNo(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Bookings> list = bookingsDAO.getReturnNo(Integer.parseInt(serviceNo.getText()));
        populateTable(list);
    }

    @FXML
    private void updateTrans(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            bookingsDAO.updateTrans(Integer.parseInt(serviceNo.getText()), newTransport.getText());
            serviceNo.clear();
            newTransport.clear();
            output.setText("Transport has been updated.");
            ObservableList<Bookings> list = bookingsDAO.getAllRecords();
            populateTable(list);
        } catch (SQLException e) {
            output.setText("Exception occurred in updateTransport " + e);
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
        colSerNo.setCellValueFactory(cellData -> cellData.getValue().getBookingSerNo().asObject());
        colDep.setCellValueFactory(cellData -> cellData.getValue().getBookingDep());
        colDest.setCellValueFactory(cellData -> cellData.getValue().getBookingDest());
        colTransport.setCellValueFactory(cellData -> cellData.getValue().getTransport());
        colReturnNo.setCellValueFactory(cellData -> cellData.getValue().getBookingReturnNo().asObject());
        ObservableList<Bookings> bookList = bookingsDAO.getAllRecords();
        populateTable(bookList);
        
    }

    private void populateTable(ObservableList<Bookings> bookList) {
        customerTable.setItems(bookList);
    }

    @FXML
    private void createReturnNo(ActionEvent event) throws ClassNotFoundException {
        try {
            ObservableList<Bookings> list = bookingsDAO.getAllRecords();
            for(int i = 0; i < list.size(); i++) {
                innerloop:
                for(int j=0; j<list.size(); j++) {
                    if(list.get(i).getDep().equals(list.get(j).getDest()) && list.get(i).getDest().equals(list.get(j).getDep())){
                        bookingsDAO.updateReturnNo(list.get(i).getSerNo(),list.get(j).getSerNo());
                        break innerloop;
                    }
                }
            }
            ObservableList<Bookings> updatedList = bookingsDAO.getAllRecords();
            populateTable(updatedList);            
        } catch(SQLException e) {
            System.out.println("Error in sql in return no function " + e);
        }
    }
}
