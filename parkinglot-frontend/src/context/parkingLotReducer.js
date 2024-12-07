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
            return state.filter(item => item.id !== action.payload)
        }
        case ACTION.LOAD: {
            return action.payload
        }
        default:
            return state
    }
};