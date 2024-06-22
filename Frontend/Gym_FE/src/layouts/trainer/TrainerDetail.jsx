// TrainerDetail.js
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { dinhDangSo } from "../../utils/DinhSangSo";
import { getTrainerById } from "../../service/TrainerService";
import { getIdUserByToken } from "../../utils/JWTService";
import { checkRegistered } from "../../service/PackRegistrationService";
import './css/TrainerListStyle.css';
import ScheduleSessionPopup from './components/SchedulePopup';
import { checkTrainerRegistered } from "../../service/TrainerRegistrationService";

function TrainerDetail() {
  const { trainerId } = useParams();
  let trainerIdNumber = 0;

  try {
    trainerIdNumber = parseInt(trainerId + "");
    if (Number.isNaN(trainerIdNumber)) {
      trainerIdNumber = 0;
    }
  } catch (error) {
    trainerIdNumber = 0;
    console.error("Error:", error);
  }

  const [trainer, setTrainer] = useState(null);
  const [dataLoading, setDataLoading] = useState(true);
  const [baoLoi, setBaoLoi] = useState(null);
  const userId = getIdUserByToken();
  const [checkRegisted, setCheckRegisted] = useState(false);
  const [showSchedulePopup, setShowSchedulePopup] = useState(false);

  // State to manage selected duration
  const [selectedDuration, setSelectedDuration] = useState(1); // Default to 1 months
  const token = localStorage.getItem("token");

  const handlePayment = async (e) => {
    e.preventDefault(); // Prevent default form submission

    // Example logic for payment handling
    // Replace with your actual payment logic
    const paymentUrl = `http://localhost:8080/vnpay/create-payment?userId=${userId}&id=${trainer.trainerId}&duration=${selectedDuration}&type=trainer`;
    fetch(paymentUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.text())
      .then((url) => {
        // Redirect to payment gateway or handle response
        window.location.href = url;
      })
      .catch((error) => {
        console.error("Error during payment:", error);
        alert("Gặp lỗi trong quá trình thanh toán!");
      });
  };

  useEffect(() => {
    getTrainerById(trainerIdNumber)
      .then((trainer) => {
        setTrainer(trainer);
        setDataLoading(false);
      })
      .catch((error) => {
        setBaoLoi(error.message);
        setDataLoading(false);
      });

    checkRegistered(userId, trainerIdNumber).then((res) => {
      setCheckRegisted(res);
    });
  }, [trainerIdNumber, userId]);

  const handleDurationChange = (event) => {
    setSelectedDuration(parseInt(event.target.value));
  };

  if (dataLoading) {
    return (
      <div className="loading-container">
        <h1>Data Loading</h1>
      </div>
    );
  }

  if (baoLoi) {
    return (
      <div>
        <h1>Gặp lỗi khi tải chi tiết trainer!</h1>
      </div>
    );
  }

  if (!trainer) {
    return (
      <div>
        <h1>Trainer không tồn tại!</h1>
      </div>
    );
  }

  return (
    <div className="container">
      <div className="row mt-4">
        {/* Trainer details and image */}
        <div className="col-4">
          <img
            src={trainer.avatar}
            className="card-img-top"
            alt={trainer.name}
            style={{ maxWidth: "250px" }}
          />
        </div>
        <div className="col-8">
          <h1>{trainer.name}</h1>{checkRegisted && 
          <div style={{ textAlign: 'end', color: 'blue', fontSize: '30px' }} onClick={() => setShowSchedulePopup(true)}>
            <i className="fa-regular fa-calendar-days"></i>
          </div>}
          {/* Price display */}
          <div className="row mt-5">
            <div className="col-2">
              <h4>
                <i className="fa-solid fa-money-bill-wave"></i>
              </h4>
            </div>
            <div className="col-9 text-end">
              <h4>
                <strong>{dinhDangSo(trainer.price) + "đ"}</strong>/tháng
              </h4>
            </div>
          </div>
          <div className="mt-4">
            <strong>Experience</strong>
          </div>
          <hr />
          <div>{trainer.experience}</div>
          <hr />
          <div className="row mt-3">
            <div className="col-2">
              <h4>
                <i className="fa-solid fa-phone"></i>
              </h4>
            </div>
            <div className="col-9 text-end">
              <h4>{trainer.phone}</h4>
            </div>
          </div>
          <div className="row mt-3">
            <div className="col-2">
              <h4>
                <i className="fa-solid fa-envelope"></i>
              </h4>
            </div>
            <div className="col-9 text-end">
              <h4>{trainer.email}</h4>
            </div>
          </div>
          {/* Duration selection */}
          <div className="row mt-4">
            <div className="col-12">
              <div className="mb-2">
                <strong>Chọn thời gian đăng ký</strong>
              </div>
              <div className="d-flex justify-content-start">
                <button
                  type="button"
                  className={`btn btn-outline-primary me-2 ${
                    selectedDuration === 1 ? "active" : ""
                  }`}
                  onClick={() => setSelectedDuration(1)}
                >
                  1 Tháng
                </button>
                <button
                  type="button"
                  className={`btn btn-outline-primary me-2 ${
                    selectedDuration === 3 ? "active" : ""
                  }`}
                  onClick={() => setSelectedDuration(3)}
                >
                  3 Tháng
                </button>
                <button
                  type="button"
                  className={`btn btn-outline-primary ${
                    selectedDuration === 12 ? "active" : ""
                  }`}
                  style={{ width: "83px" }}
                  onClick={() => setSelectedDuration(12)}
                >
                  1 Năm
                </button>
              </div>
              <div className="mt-3">
                <strong>Tổng số tiền thanh toán:</strong>{" "}
                {dinhDangSo(trainer.price * selectedDuration) + "đ"}
              </div>
            </div>
          </div>
          {/* Apply or Renew button */}
          <div className="row mt-5">
            <div className="col-12">
              <button
                type="button"
                style={{ width: "150px", height: "50px", fontSize: "20px" }}
                className={`btn ${
                  checkRegisted ? "btn-danger" : "btn-primary"
                }`}
                onClick={handlePayment}
              >
                {checkRegisted ? "Gia hạn" : "Đăng ký"}
              </button>
            </div>
          </div>
        </div>
      </div>
      {showSchedulePopup && (
        <ScheduleSessionPopup
          trainerId={trainerId}
          userId={userId}
          closePopup={() => setShowSchedulePopup(false)}
        />
      )}
    </div>
  );
}

export default TrainerDetail;
