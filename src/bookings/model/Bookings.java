/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookings.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jdleg
 */
public class Bookings {
    private IntegerProperty serNo;
    private StringProperty depProperty;
    private StringProperty destProperty;
    private StringProperty transProperty;
    private IntegerProperty returnProperty;
    
    public Bookings() {
        this.serNo = new SimpleIntegerProperty();
        this.depProperty = new SimpleStringProperty();
        this.destProperty = new SimpleStringProperty();
        this.transProperty = new SimpleStringProperty();
        this.returnProperty = new SimpleIntegerProperty();
    }


    public int getSerNo() {
        return serNo.get();
    }
    
    public void setServiceNo(int serNo) {
        this.serNo.set(serNo);
    }
    
    public IntegerProperty getBookingSerNo() {
        return serNo;
    }
    
    public String getDep() {
        return depProperty.get();
    }
    
    public void setDep(String dep) {
        this.depProperty.set(dep);
    }
    
    public StringProperty getBookingDep() {
        return depProperty;
    }

    public String getDest() {
        return destProperty.get();
    }
    
    public void setDest(String dest) {
        this.destProperty.set(dest);
    }
    
    public StringProperty getBookingDest() {
        return destProperty;
    }

    public String getTrans() {
        return transProperty.get();
    }
    
    public void setTrans(String trans) {
        this.transProperty.set(trans);
    }
    
    public StringProperty getTransport() {
        return transProperty;
    }

    public int getReturnNo() {
        return returnProperty.get();
    }
    
    public void setReturnNo(int returnNo) {
        this.returnProperty.set(returnNo);
    }
    
    public IntegerProperty getBookingReturnNo() {
        return returnProperty;
    }

    
}
