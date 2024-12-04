package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.request.FetchRequest;
import org.afs.pakinglot.domain.request.ParkRequest;
import org.afs.pakinglot.domain.response.FetchResponse;
import org.afs.pakinglot.domain.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class ParkingLotControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ParkingLotService parkingLotService;

    @InjectMocks
    private ParkingLotController parkingLotController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(parkingLotController).build();
    }

    @Test
    void should_return_ListOfParkingBoys_when_getAllParkingBoys_given_parkingBoys() throws Exception {
        List<String> parkingBoys = List.of("Super", "Standard", "Smart");
        when(parkingLotService.getAllParkingBoys()).thenReturn(parkingBoys);

        mockMvc.perform(get("/parkinglot/parkingBoys"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Super\", \"Standard\", \"Smart\"]"));

        verify(parkingLotService, times(1)).getAllParkingBoys();
    }

    @Test
    void should_return_ListOfParkedCars_when_getParkedCars_given_parkingLotId() throws Exception {
        int parkingLotId = 1;
        when(parkingLotService.getParkedCars(parkingLotId)).thenReturn(new ArrayList<>());


        mockMvc.perform(get("/parkinglot/parkedCars")
                        .param("parkingLotId", String.valueOf(parkingLotId)))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(parkingLotService, times(1)).getParkedCars(parkingLotId);
    }

    @Test
    void should_return_AllParkingLots_when_getParkedCars_given_all() throws Exception {
        List<ParkingLot> parkingLots = List.of(
                new ParkingLot(1, "The Plaza Lot", 9),
                new ParkingLot(2, "City Mall Garage", 12),
                new ParkingLot(3, "Office Tower Parking", 9)
        );
        when(parkingLotService.getAllParkedCars()).thenReturn(parkingLots);

        mockMvc.perform(get("/parkinglot/allParkedCars"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"The Plaza Lot\",\"tickets\":[],\"capacity\":9,\"full\":false,\"availablePositionRate\":1.0,\"availableCapacity\":9},{\"id\":2,\"name\":\"City Mall Garage\",\"tickets\":[],\"capacity\":12,\"full\":false,\"availablePositionRate\":1.0,\"availableCapacity\":12},{\"id\":3,\"name\":\"Office Tower Parking\",\"tickets\":[],\"capacity\":9,\"full\":false,\"availablePositionRate\":1.0,\"availableCapacity\":9}]"));

        verify(parkingLotService, times(1)).getAllParkedCars();
    }

    @Test
    void should_return_Ticket_when_park_given_parkRequest() throws Exception {
        ParkRequest request = new ParkRequest("AB-1234", "Standard");
        Ticket ticket = new Ticket("AB-1234", 1, 1, "2024-12-04T17:52:37");
        when(parkingLotService.park(any(ParkRequest.class))).thenReturn(ticket);

        String expectedJson = "{\"plateNumber\":\"AB-1234\",\"position\":1,\"parkingLot\":1,\"createTime\":\"2024-12-04T17:52:37\"}";

        mockMvc.perform(post("/parkinglot/park")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(parkingLotService, times(1)).park(any(ParkRequest.class));
    }

    @Test
    void should_return_FetchResponse_when_fetch_given_fetchRequest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ParkRequest parkRequest = new ParkRequest("AB-1234", "Standard");
        Ticket ticket = new Ticket("AB-1234", 1, 1, "2024-12-04T17:52:37");
        when(parkingLotService.park(any(ParkRequest.class))).thenReturn(ticket);
        mockMvc.perform(post("/parkinglot/park")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkRequest)));

        FetchRequest request = new FetchRequest("AB-1234", "Standard");
        FetchResponse response = new FetchResponse(new Car("AB-1234"), ticket);
        response.setTotalFee(4.0); // Assuming you have a setter for totalFee

        when(parkingLotService.fetch(any(FetchRequest.class))).thenReturn(response);

        String expectedJson = "{\"car\":{\"plateNumber\":\"AB-1234\"},\"ticket\":{\"plateNumber\":\"AB-1234\",\"position\":1,\"parkingLot\":1,\"createTime\":\"2024-12-04T17:52:37\"},\"totalFee\":4.0}";

        mockMvc.perform(post("/parkinglot/fetch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(parkingLotService, times(1)).fetch(any(FetchRequest.class));
    }
}
