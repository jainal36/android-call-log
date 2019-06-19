package com.hiya.homework.mobilecalllog.data.model;

public class CallLogItem {
    private long id;
    private String phoneNumber;
    private String callDuration;
    private String direction;//incoming, outgoing,
    private String callDate;

    public CallLogItem(long id, String phoneNumber, String callDuration, String direction, String callDate) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.callDuration = callDuration;
        this.direction = direction;
        this.callDate = callDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        CallLogItem callLogItem = (CallLogItem) obj;
        return callLogItem.id == this.id;
    }

    @Override
    public String toString() {
        return "phone number = "+ phoneNumber + "callDate = "+callDate + "direction = "+direction;
    }
}
