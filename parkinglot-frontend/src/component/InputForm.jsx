// InputForm.jsx
import React, { useState } from 'react';

const InputForm = ({ onPark, onFetch }) => {
    const [plateNumber, setPlateNumber] = useState('');
    const [ticketType, setTicketType] = useState('Standard');

    const handlePark = () => {
        onPark(plateNumber, ticketType);
    };

    const handleFetch = () => {
        onFetch(plateNumber);
    };

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
                <option value="Standard">Standard</option>
                <option value="Smart">Smart</option>
                <option value="Super">Super</option>
            </select>
            <button onClick={handlePark}>Park</button>
            <button onClick={handleFetch}>Fetch</button>
        </div>
    );
};

export default InputForm;