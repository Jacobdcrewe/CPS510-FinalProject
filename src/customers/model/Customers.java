/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jdleg
 */
public class Customers {
    private IntegerProperty idProperty;
    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private StringProperty cardNoProperty;
    private IntegerProperty pointsProperty;
    
    public Customers() {
        this.idProperty = new SimpleIntegerProperty();
        this.pointsProperty = new SimpleIntegerProperty();
        this.usernameProperty = new SimpleStringProperty();
        this.passwordProperty = new SimpleStringProperty();
        this.cardNoProperty = new SimpleStringProperty();
    }
    
    public int getCustId() {
        return idProperty.get();
    }
    
    public void setCustomerId(int id) {
        this.idProperty.set(id);
    }
    
    public IntegerProperty getCustomerId() {
        return idProperty;
    }
    
    
    public int getPoints() {
        return pointsProperty.get();
    }
    
    public void setPoints(int points) {
        this.pointsProperty.set(points);
    }
    
    public IntegerProperty getCustomerPoints() {
        return pointsProperty;
    }
    
    
    public String getCardNo() {
        return cardNoProperty.get();
    }
    
    public void setCardNo(String cardNo) {
        this.cardNoProperty.set(cardNo);
    }
    
    public StringProperty getCustomerCardNo() {
        return cardNoProperty;
    }
    
    
    public String getUsername() {
        return usernameProperty.get();
    }
    
    public void setUsername(String username) {
        this.usernameProperty.set(username);
    }
    
    public StringProperty getCustomerUsername() {
        return usernameProperty;
    }
    
    
    public String getPassword() {
        return passwordProperty.get();
    }
    
    public void setPassword(String pass) {
        this.passwordProperty.set(pass);
    }
    
    public StringProperty getCustomerPassword() {
        return passwordProperty;
    }
}
