package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.ParkingBoy;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final Double PARKING_FEE = 4.0;
    public static final Integer FEE_UNIT = 900;

    static ParkingLot thePlazaPark = new ParkingLot(1, "The Plaza Lot", 9);
    static ParkingLot cityMallGarage = new ParkingLot(2, "City Mall Garage", 12);
    static ParkingLot officeTowerParking = new ParkingLot(3, "Office Tower Parking", 9);

    public static List<ParkingLot> parkingLots = List.of(thePlazaPark, cityMallGarage, officeTowerParking);

    static ParkingBoy standardBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
    static ParkingBoy smartBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
    static ParkingBoy superBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());

    public static Map<String, ParkingBoy> boyTypeToBoy;

    static {
        boyTypeToBoy = new HashMap<>();
        boyTypeToBoy.put("Standard", standardBoy);
        boyTypeToBoy.put("Smart", smartBoy);
        boyTypeToBoy.put("Super", superBoy);
    }

}
