import React, { useState } from 'react';
import "./Feedback.css";
import { getIdUserByToken } from '../../utils/JWTService';

const Feedback = () => {
  const [content, setContent] = useState('');
  const userId = getIdUserByToken();

  const handleSubmit = async () => {
    const feedbackDto = {
      userId,
      content,
    };

    try {
      const response = await fetch('http://localhost:8080/feedbacks/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(feedbackDto),
      });

      if (response.ok) {
        alert('Feedback submitted successfully');
        setContent(''); // Clear the textarea after successful submission
      } else {
        alert('Failed to submit feedback');
      }
    } catch (error) {
      console.error('Error submitting feedback:', error);
      alert('An error occurred while submitting feedback');
    }
  };

  return (
    <div className='container-fluid'>
      <div className='row parent-box'>
        <div className="feedback-form">
          <h3 className='mt-2'>Feedback</h3>
          <textarea
            type="text"
            className='message'
            placeholder='Nhập nội dung'
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
          <button className="form-btn" onClick={handleSubmit}>Gửi</button>
        </div>
      </div>
    </div>
  );
};

export default Feedback;
