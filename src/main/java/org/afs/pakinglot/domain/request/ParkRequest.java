package org.afs.pakinglot.domain.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParkRequest {
    private String plateNumber;
    private String boyType;

    public ParkRequest(String plateNumber, String boyType){
        this.plateNumber = plateNumber;
        this.boyType = boyType;
    }

    // Getters and setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBoyType() {
        return boyType;
    }

    public void setBoyType(String boyName) {
        this.boyType = boyName;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}