package org.afs.pakinglot.domain.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FetchRequest {

    private String plateNumber;
    private String boyType;

    public FetchRequest(String plateNumber, String boyType){
        this.plateNumber = plateNumber;
        this.boyType = boyType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBoyType() {
        return boyType;
    }

    public void setBoyType(String boyType) {
        this.boyType = boyType;
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
