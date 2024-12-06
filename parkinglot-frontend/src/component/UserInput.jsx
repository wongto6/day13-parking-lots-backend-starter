// InputForm.jsx
import React, {useEffect, useState} from 'react';
import {getBoyTypes} from "../api/parkingLot";

const UserInput = ({ onPark, onFetch }) => {
    const [plateNumber, setPlateNumber] = useState('');
    const [ticketType, setTicketType] = useState('Standard');
    const [parkingBoy, setParkingBoy] = useState([])

    const handlePark = () => {
        onPark(plateNumber, ticketType);
    };

    const handleFetch = () => {
        onFetch(plateNumber);
    };

    useEffect(() => {
        getBoyTypes().then((data)=>{
            setParkingBoy(data)
        })
    }, []);

    return (
        <div className="input-container">
            <input
                type="text"
                placeholder="Enter plate number"
                value={plateNumber}
                onChange={(e) => setPlateNumber(e.target.value)}
            />
            <select
                value={ticketType}
                onChange={(e) => setTicketType(e.target.value)}
            >
                {parkingBoy.map((boy, index) => (
                    <option key={index} value={boy}>{boy}</option>
                ))}
            </select>
            <button onClick={handlePark}>Park</button>
            <button onClick={handleFetch}>Fetch</button>
        </div>
    );
};

export default UserInput;