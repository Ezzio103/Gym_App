// ScheduleSessionPopup.js
import React, { useState, useEffect } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import './ScheduleSessionPopup.css'; // Import the custom CSS file for styling

const localizer = momentLocalizer(moment);

const ScheduleSessionPopup = ({ trainerId, userId, closePopup }) => {
    const [availableSlots, setAvailableSlots] = useState([]);
    const [selectedSlot, setSelectedSlot] = useState(null);
    const [registeredSlots, setRegisteredSlots] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [reset, setReset] = useState(true);
    const token = localStorage.getItem('token');

    useEffect(() => {
        fetchAvailableSlots();
        fetchRegisteredSlots();
    }, [trainerId, userId,reset]);

    const fetchAvailableSlots = async () => {
        try {
            const response = await fetch(`http://localhost:8080/trainingslots/available/${trainerId}`);
            const data = await response.json();
            setAvailableSlots(data);
            setIsLoading(false);
        } catch (error) {
            console.error('Error fetching available slots:', error);
            setIsLoading(false);
        }
    };

    const fetchRegisteredSlots = async () => {
        try {
            const response = await fetch(`http://localhost:8080/trainingslots/registered/${userId}`);
            const data = await response.json();
            setRegisteredSlots(data);
        } catch (error) {
            console.error('Error fetching registered slots:', error);
        }
    };

    const handleSlotSelect = (slot) => {
        setSelectedSlot({
            slotId: slot.slotId,
            start_time: slot.start,
            end_time: slot.end
        });
    };

    const handleScheduleSession = async () => {
        if (selectedSlot) {
            try {
                const response = await fetch(`http://localhost:8080/trainingslots/schedule?slotId=${selectedSlot.slotId}&userId=${userId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        Authorization: `Bearer ${token}`,
                    },
                    body: JSON.stringify({
                        slotId: selectedSlot.slotId,
                        userId: userId
                    })
                });
                if (response.ok) {
                    const newRegisteredSlot = JSON.parse(JSON.stringify(selectedSlot));
                    setRegisteredSlots([...registeredSlots, newRegisteredSlot]);
                    setAvailableSlots(availableSlots.filter(slot => slot.slotId !== newRegisteredSlot.slotId));
                    setSelectedSlot(null);
                    setReset(!reset)
                    alert('Session scheduled successfully!');
                } else {
                    alert('Error scheduling session.');
                }
            } catch (error) {
                console.error('Error scheduling session:', error);
                alert('Error scheduling session.');
            }
        }
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    const availableEvents = availableSlots.map(slot => ({
        slotId: slot.slotId,
        start: moment(slot.startTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").toDate(),
        end: moment(slot.endTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").toDate(),
        title: 'Còn trống'
    }));

    return (
        <div className="modal fade show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }}>
            <div className="modal-dialog modal-dialog-scrollable modal-lg">
                <div className="modal-content">
                    <div className="modal-header">
                        <h1 className="modal-title fs-5"><h3>Đăng ký lịch tập</h3></h1>
                        <button type="button" className="btn-close" onClick={closePopup}></button>
                    </div>
                    <div className="modal-body">
                        <Calendar
                            localizer={localizer}
                            events={availableEvents}
                            startAccessor="start"
                            endAccessor="end"
                            style={{ height: 500 }}
                            selectable
                            onSelectEvent={handleSlotSelect}
                            eventPropGetter={(event) => {
                                const isSelected = selectedSlot && event.slotId === selectedSlot.slotId;
                                const backgroundColor = isSelected ? '#00ff00' : '#007bff';
                                const border = isSelected ? '2px solid #ff00ff' : 'none';
                                return { style: { backgroundColor, border } };
                            }}
                        />
                        {selectedSlot && (
                            <button className="btn btn-primary mt-3" onClick={handleScheduleSession}>Schedule Session</button>
                        )}
                        
                        <div>
                            <h3>Danh sách buổi tập đã đăng ký</h3>
                            {registeredSlots.length === 0 && <p>Bạn chưa đăng ký buổi tập.</p>}
                            {registeredSlots.map((slot, index) => (
                                <div key={index}>
                                    <div className='mt-1' style={{display:'inline-block',border:"1px solid blue",borderRadius:'5px',padding:'5px',width:'350px'}}>
                                    <span className=''><b>Bắt đầu: </b> {moment(slot.startTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").format('LLLL')}</span>
                                    <p><b>Kết thúc: </b>{moment(slot.endTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").format('LLLL')}</p></div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ScheduleSessionPopup;

/* import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';

const localizer = momentLocalizer(moment);

const TrainerSchedule = ({ trainerId, userId }) => {
    const [availableSlots, setAvailableSlots] = useState([]);
    const [selectedSlot, setSelectedSlot] = useState(null);
    const [registeredSlots, setRegisteredSlots] = useState([]);
    const [reset, setReset] = useState(true);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetchAvailableSlots();
        fetchRegisteredSlots();
    }, [trainerId, userId, reset]);

    const fetchAvailableSlots = async () => {
        try {
            const response = await fetch(`http://localhost:8080/trainingslots/available/${trainerId}`);
            const data = await response.json();
            setAvailableSlots(data);
            setIsLoading(false);
        } catch (error) {
            console.error('Error fetching available slots:', error);
            setIsLoading(false);
        }
    };

    const fetchRegisteredSlots = async () => {
        try {
            const response = await fetch(`http://localhost:8080/trainingslots/registered/${userId}`);
            const data = await response.json();
            setRegisteredSlots(data);
        } catch (error) {
            console.error('Error fetching registered slots:', error);
        }
    };

    const handleSlotSelect = (slot) => {
        setSelectedSlot({
            slotId: slot.slotId,
            start_time: slot.start,
            end_time: slot.end
        });
    };

    const handleScheduleSession = async () => {
        if (selectedSlot) {
            try {
                const response = await fetch(`http://localhost:8080/trainingslots/schedule?slotId=${selectedSlot.slotId}&userId=${userId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        slotId: selectedSlot.slotId,
                        userId: userId
                    })
                });
                if (response.ok) {
                    const newRegisteredSlot = JSON.parse(JSON.stringify(selectedSlot));
                    setRegisteredSlots([...registeredSlots, newRegisteredSlot]);
                    setAvailableSlots(availableSlots.filter(slot => slot.slotId !== newRegisteredSlot.slotId));
                    setSelectedSlot(null);
                    setReset(!reset);
                    alert('Session scheduled successfully!');
                } else {
                    alert('Error scheduling session.');
                }
            } catch (error) {
                console.error('Error scheduling session:', error);
                alert('Error scheduling session.');
            }
        }
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    const events = availableSlots.map(slot => ({
        slotId: slot.slotId,
        start: moment(slot.startTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").toDate(),
        end: moment(slot.endTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").toDate(),
        title: 'Available'
    }));

    return (
        <div>
            <Calendar
                localizer={localizer}
                events={events}
                startAccessor="start"
                endAccessor="end"
                style={{ height: 500 }}
                selectable
                onSelectEvent={handleSlotSelect}
            />
            {selectedSlot && (
                <button className='btn btn-primary mt-2' onClick={handleScheduleSession}>Schedule Session</button>
            )}
            <div>
                <h3>Registered Slots</h3>
                {registeredSlots.length === 0 && <p>No slots registered.</p>}
                {registeredSlots.map((slot, index) => (
                    <div key={index}>
                        <p>Start: {moment(slot.startTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").format('LLLL')}</p>
                        <p>End: {moment(slot.endTime, "YYYY-MM-DD HH:mm:ss.SSSSSS").format('LLLL')}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default TrainerSchedule;
*/ 