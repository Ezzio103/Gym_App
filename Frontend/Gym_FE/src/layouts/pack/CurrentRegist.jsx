import React, { useEffect, useState } from 'react';
import { getCurrentPacks } from '../../service/PackRegistrationService';
import { getIdUserByToken } from '../../utils/JWTService';
import CurrentRegistProps from './components/CurrentRegistProps';
import { getCurrentTrainerRegistrations } from '../../service/TrainerRegistrationService';
import CurrentTrainerProps from '../trainer/components/CurrentTrainerProps';

const CurrentRegist = () => {
  const [packRegists, setPackRegists] = useState([]);
  const [trainerRegistrations, setTrainerRegistrations] = useState([]);
  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await getCurrentPacks(getIdUserByToken());
        console.log("Dữ liệu nhận được: ", res);
        setPackRegists(res); // Cập nhật trạng thái với dữ liệu nhận được
        console.log("Dữ liệu cập nhật: ", res); // Log dữ liệu ngay sau khi nhận được
      } catch (error) {
        console.error("Lỗi khi lấy danh sách gói đăng ký:", error);
      }
      try {
        const res = await getCurrentTrainerRegistrations(getIdUserByToken());
        console.log("Dữ liệu nhận được: ", res);
        setTrainerRegistrations(res); // Cập nhật trạng thái với dữ liệu nhận được
        console.log("Dữ liệu cập nhật: ", res); // Log dữ liệu ngay sau khi nhận được
      } catch (error) {
        console.error("Lỗi khi lấy danh sách huấn luyện viên:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="container">
      {/* ... */}
      <div className="row mt-3">
        {/* <div className="col-1"></div> */}
        <div className="col-10"><h1>Danh sách đăng ký</h1></div>
      </div>
      { (
        
        <div className="row mt-4">
          {packRegists.map((pack) => (
            <CurrentRegistProps key={pack.registrationId} pack={pack} />
          ))}
           {trainerRegistrations.map((registration) => (
          <CurrentTrainerProps key={registration.trainerId} trainerId={registration.trainerId} />
        ))}
        </div>
      ) }
    </div>
  );
};

export default CurrentRegist;
