package com.example.test;

public class Pizza {

    private final String orderId;
    private final String studentID;
    private final String type;
    private final String toopings;
    private final String pickupTime;
    private String status;

    public Pizza(String orderId, String studentID, String type, String toopings,
            String pickupTime, String status) {
        this.orderId = orderId;
        this.studentID = studentID;
        this.type = type;
        this.toopings = toopings;
        this.pickupTime = pickupTime;
        this.status = status;;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getType() {
        return type;
    }

    public String getToopings() {
        return toopings;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
