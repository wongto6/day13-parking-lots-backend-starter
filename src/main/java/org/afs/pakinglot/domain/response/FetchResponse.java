package org.afs.pakinglot.domain.response;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.afs.pakinglot.domain.controller.Constants.FEE_UNIT;
import static org.afs.pakinglot.domain.controller.Constants.PARKING_FEE;

public class FetchResponse {

    private Car car;

    private Ticket ticket;

    private Double totalFee;

    public FetchResponse(Car car, Ticket ticket) {
        this.car = car;
        this.ticket = ticket;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime givenDateTime = LocalDateTime.parse(ticket.createTime(), formatter);

        // Get the current date-time
        LocalDateTime now = LocalDateTime.now();

        // Calculate the duration between the two date-times
        Duration duration = Duration.between(givenDateTime, now);

        // Get the duration in minutes

        Long parkedSeconds = duration.toSeconds();

        if (parkedSeconds <= FEE_UNIT) {
            this.totalFee = PARKING_FEE;
        } else {
            this.totalFee = (parkedSeconds / FEE_UNIT) * PARKING_FEE + (parkedSeconds % FEE_UNIT) * PARKING_FEE;
        }

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }
}
