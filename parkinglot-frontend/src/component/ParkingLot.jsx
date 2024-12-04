// ParkingLot.jsx
import React from 'react';
import Ticket from './Ticket';

const ParkingLot = ({ parkingLot }) => {
    const sortedTickets = parkingLot.tickets.sort((a, b) => a.position - b.position);
    const ticketsWithEmptyBoxes = Array.from({ length: parkingLot.capacity }, (_, index) => {
        const ticket = sortedTickets.find(ticket => ticket.position === index + 1);
        return ticket ? <Ticket key={index} ticket={ticket} /> : <div key={index} className="ticket empty-box"></div>;
    });

    return (
        <div className="parking-lot">
            <h2>{parkingLot.name}</h2>
            <div className="tickets">
                {ticketsWithEmptyBoxes}
            </div>
        </div>
    );
};

export default ParkingLot;