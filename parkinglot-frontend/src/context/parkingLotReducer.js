export const ACTION = {
    PARK: "PARK",
    FETCH: "FETCH",
    LOAD: "LOAD"
}

export const parkingLotReducer = (state, action) => {

    switch (action.type) {
        case ACTION.PARK: {
            const newTicket = action.payload;
            console.log(action.payload)
            return state.map(parkingLot => {
                if (parkingLot.id === newTicket.parkingLot) {
                    return {
                        ...parkingLot,
                        tickets: [...(parkingLot.tickets || []), newTicket],
                        availableCapacity: parkingLot.availableCapacity - 1,
                        full: parkingLot.availableCapacity - 1 === 0,
                        availablePositionRate: (parkingLot.availableCapacity - 1) / parkingLot.capacity
                    };
                }
                return parkingLot;
            });
        }
        case ACTION.FETCH: {
            const ticketToRemove = action.payload.ticket;
            return state.map(parkingLot => {
                if (parkingLot.id === ticketToRemove.parkingLot) {
                    const updatedTickets = parkingLot.tickets.filter(ticket => ticket.plateNumber !== ticketToRemove.plateNumber);
                    return {
                        ...parkingLot,
                        tickets: updatedTickets,
                        availableCapacity: parkingLot.availableCapacity + 1,
                        full: false,
                        availablePositionRate: (parkingLot.availableCapacity + 1) / parkingLot.capacity
                    };
                }
                return parkingLot;
            });
        }
        case ACTION.LOAD: {
            return action.payload
        }
        default:
            return state
    }
};