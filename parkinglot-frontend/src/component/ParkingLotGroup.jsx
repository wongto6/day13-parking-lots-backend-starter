// ParkingLotGroup.jsx
import React, {useContext, useEffect} from 'react';
import ParkingLot from './ParkingLot';
import sampleData from './sampleData.json';
import {ParkingLotContext} from "../App";
import {getAll} from "../api/parkingLot";
import {ACTION} from "../context/parkingLotReducer";

const ParkingLotGroup = () => {

    const {state, dispatch} = useContext(ParkingLotContext)

    useEffect(() => {
        getAll().then((data) => {
            dispatch({type: ACTION.LOAD, payload: data})
        })
    }, []);

    return (
        <div className="parking-lot-group">
            {state.map((parkingLot, index) => (
                <ParkingLot key={index} parkingLot={parkingLot} />
            ))}
        </div>
    );
};

export default ParkingLotGroup;