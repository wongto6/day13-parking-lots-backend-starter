// Ticket.jsx
import React from 'react';

const Ticket = ({ ticket }) => {
    return (
        <div className="ticket">
            <button className="ticket-button">
                <p>{ticket.plateNumber}</p>
                <p className="create-time">{new Date(ticket.createTime).toLocaleString()}</p>
            </button>
        </div>
    );
};

export default Ticket;