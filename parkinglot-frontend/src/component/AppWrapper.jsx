// AppWrapper.jsx
import React from 'react';
import InputForm from './InputForm';
import ParkingLotGroup from './ParkingLotGroup';
import './styles.css';

const AppWrapper = () => {
    const handlePark = (plateNumber, ticketType) => {
        // Implement park functionality
        console.log(`Parking ${plateNumber} with ${ticketType} ticket`);
    };

    const handleFetch = (plateNumber) => {
        // Implement fetch functionality
        console.log(`Fetching ${plateNumber}`);
    };

    return (
        <div>
            <InputForm onPark={handlePark} onFetch={handleFetch} />
            <ParkingLotGroup />
        </div>
    );
};

export default AppWrapper;