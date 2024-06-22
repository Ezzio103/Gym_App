import React from 'react';
import "../css/FeedbackProps.css"

const FeedbackProps = ({ feedback }) => {
  // Chuyển đổi thời gian từ chuỗi ISO 8601 sang định dạng phù hợp
  const formatTime = (isoTimeString) => {
    const date = new Date(isoTimeString);
    const formattedTime = date.toLocaleString(); // Sử dụng toLocaleString() để format ngày giờ
    return formattedTime;
  };

  return (
    <div className='mt-2 item'>
      <div><strong>{feedback.username}</strong></div>
      <div style={{marginLeft:"10px"}}>
        <div>{feedback.content}</div>
        <div style={{ color:"#888"}}>Thời gian: {formatTime(feedback.time)}</div>
      </div>
    </div>
  );
};

export default FeedbackProps;
