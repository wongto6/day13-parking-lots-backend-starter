package org.afs.pakinglot.domain.service;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.request.FetchRequest;
import org.afs.pakinglot.domain.request.ParkRequest;
import org.afs.pakinglot.domain.response.FetchResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static org.afs.pakinglot.domain.controller.Constants.boyTypeToBoy;
import static org.afs.pakinglot.domain.controller.Constants.parkingLots;

@Service
public class ParkingLotService {

    private static final Pattern PLATE_PATTERN = Pattern.compile("^[A-Z]{2}-\\d{4}$");

    public List<String> getAllParkingBoys() {
        return boyTypeToBoy.keySet().stream().toList();
    }

    public List<String> getParkedCars(int parkingLotId) {
        ParkingLot parkingLot = parkingLots.stream()
                .filter(lot -> lot.getId() == parkingLotId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid parking lot ID"));

        return parkingLot.getTickets().stream()
                .map(Ticket::plateNumber)
                .toList();
    }

    public List<ParkingLot> getAllParkedCars() {
        return parkingLots;
    }

    public Ticket park(ParkRequest request) {

        if(isPlateNumberValid(request.getPlateNumber())){
            throw new IllegalArgumentException();
        }

        Car car = new Car(request.getPlateNumber());
        return boyTypeToBoy.get(request.getBoyType()).park(car);
    }

    public FetchResponse fetch(FetchRequest request) {

        if(isPlateNumberValid(request.getPlateNumber())){
            throw new IllegalArgumentException();
        }

        Ticket ticket = boyTypeToBoy.get(request.getBoyType()).getTicketByPlateNumber(request.getPlateNumber());
        Car car = boyTypeToBoy.get(request.getBoyType()).fetch(ticket);
        return new FetchResponse(car, ticket);
    }
    public boolean isPlateNumberValid(String plateNumber){
        return !PLATE_PATTERN.matcher(plateNumber).matches();
    }

}
