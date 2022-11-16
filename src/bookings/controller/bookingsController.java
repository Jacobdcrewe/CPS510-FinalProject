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

    //initalizing textfields
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

    //initializing table and coloumns
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

    //clears the textfields for adding a booking
    @FXML
    private void clearText(ActionEvent event) {
        destination.clear();
        departure.clear();
        modeOfTransport.clear();
    }

    //inserts the booking from the add booking button 
    @FXML
    private void insertBooking(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            //calls from bookingsDAO the insertbooking method with the values expected to input
            bookingsDAO.insertBooking(departure.getText(), destination.getText(), modeOfTransport.getText());
            
            //clears text fields
            departure.clear();
            destination.clear();
            modeOfTransport.clear();
            
            //lets user know the booking was added
            output.setText("Booking has been added.");
            
            //creates list of all the bookings and then populates the table with the bookings values
            ObservableList<Bookings> bookList = bookingsDAO.getAllRecords();
            populateTable(bookList);
        } catch (SQLException e) {
            //lets user know where there is an error
            output.setText("Exception occurred in Insertion " + e);
            e.printStackTrace();
            throw e;
        }
    }

    
    //searches bookings for specific service number
    @FXML
    private void searchBookings(ActionEvent event) throws ClassNotFoundException, SQLException {
        //creates a list of all values that match the serviceNo
        ObservableList<Bookings> list = bookingsDAO.searchBookings(serviceNo.getText());
        //calls populateTable function
        populateTable(list);
        //clears service number option
        serviceNo.clear();
    }

    //shows all bookings
    @FXML
    private void showAll(ActionEvent event) throws ClassNotFoundException, SQLException {
        //creates a list of all bookings and populates the table with taht
        ObservableList<Bookings> list = bookingsDAO.getAllRecords();
        populateTable(list);
    }

    //updates the departure
    @FXML
    private void updateDep(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //calls the update departure function from bookingsDAO and inputs the values of the service number and the departure value
            bookingsDAO.updateDep(Integer.parseInt(serviceNo.getText()), newDep.getText());
            //clears text fields
            serviceNo.clear();
            newDep.clear();
            
            //gives user output of if the insert command succeeded
            output.setText("Departure has been updated.");
            
            //updates the table with the updated departure
            ObservableList<Bookings> list = bookingsDAO.getAllRecords();
            populateTable(list);
        } catch (SQLException e) {
            //lets user know if error in function
            output.setText("Exception occurred in updatePassword " + e);
            e.printStackTrace();
            throw e;
        }
    }

    
    //updates the destination. same kind of documentation as update departure but with destination
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

    //deletes booking based on service number
    @FXML
    private void delBooking(ActionEvent event) throws ClassNotFoundException, SQLException {
        //checks to see if service number textfield is a number
        if (serviceNo.getText().matches("[0-9]+")) {
            try {
                //calls the delete booking function from bookingsDAO and passes the interger value of the service number
                bookingsDAO.deleteBooking(Integer.parseInt(serviceNo.getText()));
                //clears the textfield
                serviceNo.clear();
                //lets user know if function succeeded
                output.setText("Booking has been deleted.");
                //updates the table
                ObservableList<Bookings> list = bookingsDAO.getAllRecords();
                populateTable(list);
            } catch (SQLException e) {
                //lets user know of error
                output.setText("Exception occurred in delCustomer " + e);
                e.printStackTrace();
                throw e;
            }
        } else {
            //lets user know if they input the wrong character
            output.setText("Please input an integer for customer ID");
        }
    }

    //gets return number of the booking
    @FXML
    private void getReturnNo(ActionEvent event) throws ClassNotFoundException, SQLException {
        //creates list of bookings objects with a service number equal to the return number of the input service number
        ObservableList<Bookings> list = bookingsDAO.getReturnNo(Integer.parseInt(serviceNo.getText()));
        //updates table
        populateTable(list);
    }

    //updates transport type. Same as departure and destination but with transport
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
    
    //provides function of back button
    @FXML
    private void back(ActionEvent event) throws IOException {
        //creates new screen stage
        Stage primaryStage = new Stage();
        //calls the fxml of the stage wanting to be opened
        Parent root = FXMLLoader.load(getClass().getResource("/selectionPage/controller/selectionPageView.fxml"));
        //creates new scene with the fxml code
        Scene scene = new Scene(root);
        //titles the stage
        primaryStage.setTitle("Selection Page");
        //setting the stage to have the scene inside it
        primaryStage.setScene(scene);
        //showing the stage
        primaryStage.show();
        //hiding the current window
        ((Node) (event.getSource())).getScene().getWindow().hide();

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
            for (int i = 0; i < list.size(); i++) {
                innerloop:
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(i).getDep().equals(list.get(j).getDest()) && list.get(i).getDest().equals(list.get(j).getDep())) {
                        bookingsDAO.updateReturnNo(list.get(i).getSerNo(), list.get(j).getSerNo());
                        break innerloop;
                    }
                }
            }
            ObservableList<Bookings> updatedList = bookingsDAO.getAllRecords();
            populateTable(updatedList);
        } catch (SQLException e) {
            System.out.println("Error in sql in return no function " + e);
        }
    }
}
