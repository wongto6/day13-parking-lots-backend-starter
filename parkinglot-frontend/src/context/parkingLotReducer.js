export const ACTION = {
    PARK: "PARK",
    FETCH: "FETCH",
    LOAD: "LOAD"
}

export const parkingLotReducer = (state, action) => {

    switch (action.type) {
        case ACTION.PARK: {
            const createdReturn = action.payload
            return [...state, {id: createdReturn.id, text: createdReturn.text, done: createdReturn.done}]
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