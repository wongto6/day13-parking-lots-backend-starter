// ParkingLotGroup.jsx
import React from 'react';
import ParkingLot from './ParkingLot';
import sampleData from './sampleData.json';

const ParkingLotGroup = () => {
    return (
        <div className="parking-lot-group">
            {sampleData.map((parkingLot, index) => (
                <ParkingLot key={index} parkingLot={parkingLot} />
            ))}
        </div>
    );
};

export default ParkingLotGroup;