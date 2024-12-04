package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.request.FetchRequest;
import org.afs.pakinglot.domain.request.ParkRequest;
import org.afs.pakinglot.domain.response.FetchResponse;
import org.afs.pakinglot.domain.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/parkinglot")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping(path = "/parkingBoys")
    public List<String> getAllParkingBoys() {
        return parkingLotService.getAllParkingBoys();
    }

    @GetMapping(path = "/parkedCars")
    public List<String> getParkedCars(@RequestParam int parkingLotId) {
        return parkingLotService.getParkedCars(parkingLotId);
    }

    @GetMapping(path = "/allParkedCars")
    public List<ParkingLot> getALLParkedCars() {
        return parkingLotService.getAllParkedCars();
    }

    @PostMapping(path = "/park")
    public Ticket park(@RequestBody ParkRequest request) {
        return parkingLotService.park(request);
    }

    @PostMapping(path = "/fetch")
    public FetchResponse fetch(@RequestBody FetchRequest request) {
        return parkingLotService.fetch(request);
    }
}
