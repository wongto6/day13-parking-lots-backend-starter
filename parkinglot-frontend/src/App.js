// App.js
import React, {createContext, useReducer} from 'react';
import ParkingLotGroup from './component/ParkingLotGroup';
import './component/styles.css';
import UserInputWrapper from "./component/UserInputWrapper";
import {parkingLotReducer} from "./context/parkingLotReducer";

export const ParkingLotContext = createContext();

const App = () => {

    const [state, dispatch] = useReducer(parkingLotReducer, []);

    return (
        <ParkingLotContext.Provider value={{state, dispatch}}>
            <UserInputWrapper />
            <ParkingLotGroup />
        </ParkingLotContext.Provider>
    );
};

export default App;