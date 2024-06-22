import React, { useEffect, useState } from 'react';
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
    }, [trainerId, userId,reset]);

    const fetchAvailableSlots = async () => {
        try {
            const response = await fetch(`http://localhost:8080/trainingslots/available/${trainerId}`);
            const data = await response.json();
            setAvailableSlots(data);
            console.log(data)
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
            slotId : slot.slotId,
            start_time: slot.start,
            end_time: slot.end
        });
        console.log(selectedSlot);
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
                    setAvailableSlots(availableSlots.filter(slot => slot.start_time !== newRegisteredSlot.start_time));
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
         slotId : slot.slotId,
        start: new Date(slot.startTime),
        end: new Date(slot.endTime),
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
                        <p>Start: {new Date(slot.startTime).toLocaleString()}</p>
                        <p>End: {new Date(slot.endTime).toLocaleString()}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default TrainerSchedule;
