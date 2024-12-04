// App.js
import React from 'react';
import ParkingLotGroup from './component/ParkingLotGroup';
import './component/styles.css';
import UserInputWrapper from "./component/UserInputWrapper";

const App = () => {
    return (
        <div>
            <UserInputWrapper />
            <ParkingLotGroup />
        </div>
    );
};

export default App;