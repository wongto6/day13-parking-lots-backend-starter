// AppWrapper.jsx
import React from 'react';
import UserInput from './UserInput';
import ParkingLotGroup from './ParkingLotGroup';
import './styles.css';

const UserInputWrapper = () => {
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
            <UserInput onPark={handlePark} onFetch={handleFetch} />
        </div>
    );
};

export default UserInputWrapper;