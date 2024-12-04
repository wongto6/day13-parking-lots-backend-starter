// Ticket.jsx
import React from 'react';

const Ticket = ({ ticket }) => {
    return (
        <div className="ticket">
            <p>{ticket.plateNumber}</p>
            <p className="create-time">{new Date(ticket.createTime).toLocaleString()}</p>
        </div>
    );
};

export default Ticket;