package models;

import java.util.Date;

public class Token {

    private int tokenNumber;

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private Date entryTime;

    private Vehicle vehicle;

    private ParkingSlot parkingSlot;

    public int getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(int tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public Gate getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Gate generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Operator getGeneratedby() {
        return generatedby;
    }

    public void setGeneratedby(Operator generatedby) {
        this.generatedby = generatedby;
    }

    private Gate generatedAt;

    private Operator generatedby;


    public int getId() {
        return 0;
    }
}
