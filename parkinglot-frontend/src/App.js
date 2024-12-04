// App.js
import React from 'react';
import ParkingLotGroup from './component/ParkingLotGroup';
import './component/styles.css';
import AppWrapper from "./component/AppWrapper";

const App = () => {
    return (
        <div>
            <AppWrapper />
            <ParkingLotGroup />
        </div>
    );
};

export default App;