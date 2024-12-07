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
            <h1 className={"title"}>ParkingLots Management System</h1>
            <UserInputWrapper />
            <ParkingLotGroup />
        </ParkingLotContext.Provider>
    );
};

export default App;