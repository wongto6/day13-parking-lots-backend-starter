// InputForm.jsx
import React, {useContext, useEffect, useState} from 'react';
import {fetchCar, getBoyTypes, park} from "../api/parkingLot";
import {ParkingLotContext} from "../App";
import {ACTION} from "../context/parkingLotReducer";

const UserInput = () => {
    const [plateNumber, setPlateNumber] = useState('');
    const [boyType, setBoyType] = useState('Standard');
    const [parkingBoy, setParkingBoy] = useState([])
    const {dispatch} = useContext(ParkingLotContext)

    const handlePark = () => {
        park(plateNumber, boyType).then((data) => {
            dispatch({type: ACTION.PARK, payload: data})
        })
    };

    const handleFetch = () => {
        fetchCar(plateNumber, boyType).then((data) => {
            dispatch({type: ACTION.FETCH, payload: data});
            alert(`Car Parked at: ${new Date(data.ticket.createTime).toLocaleString()}\nTotal Fee ($4 for 15 minutes): $${data.totalFee}`);
        });
    };

    useEffect(() => {
        getBoyTypes().then((data) => {
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
                value={boyType}
                onChange={(e) => setBoyType(e.target.value)}
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