import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getTrainerById } from '../../../service/TrainerService';
// import { getTrainerById } from '../../../service/TrainerService'; // Giả sử bạn có một service để lấy thông tin huấn luyện viên

const CurrentTrainerProps = ({ trainerId }) => {
  const [trainer, setTrainer] = useState({});

  useEffect(() => {
    const fetchTrainerData = async () => {
      try {
        const res = await getTrainerById(trainerId);
        setTrainer(res);
        console.log('Dữ liệu huấn luyện viên: ', res);
      } catch (error) {
        console.error("Lỗi khi lấy dữ liệu huấn luyện viên:", error);
      }
    };

    fetchTrainerData();
  }, [trainerId]);

  return (
    <div className="col-md-3 mt-2">
      <div className="card text-align-center">
        <div className="card-body">
          <Link to={`/trainer/${trainer.trainerId}`}>
            <img
              src={trainer.avatar}
              className="card-img-top"
              alt={trainer.name}
              style={{ height: '300px' }}
            />
          </Link>
          <Link to={`/trainer/${trainer.trainerId}`} style={{ textDecoration: 'none' }}>
            <h5 className="card-title mt-2">{trainer.name}</h5>
          </Link>
          <p className="card-text">{trainer.experience}</p>
          <div className="row mt-2">
            <div className="col-12" style={{ marginLeft: "10px" }}>
              <p className="card-text">{trainer.email}</p>
            </div>
          </div>
          <div className="row mt-2" role="group">
            <div className="col-12 text-end">
              <Link to={`/trainer/${trainer.trainerId}`} style={{ textDecoration: 'none', marginRight: "5px" }}>
                <button className="btn btn-primary btn-block">
                  Xem Chi Tiết
                </button>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CurrentTrainerProps;
