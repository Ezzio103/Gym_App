import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { dinhDangSo } from "../../utils/DinhSangSo";
import { getIdUserByToken } from "../../utils/JWTService";
import { getPacktById } from "../../service/PackService";
import { checkRegistered } from "../../service/PackRegistrationService";

function PackDetail() {
  const { packId } = useParams();
  let packIdNumber = 0;

  try {
    packIdNumber = parseInt(packId + "");
    if (Number.isNaN(packIdNumber)) {
      packIdNumber = 0;
    }
  } catch (error) {
    packIdNumber = 0;
    console.error("Error:", error);
  }

  const [applicants, setApplicants] = useState([]);
  const [pack, setPack] = useState(null);
  const [dangTaiDuLieu, setDangTaiDuLieu] = useState(true);
  const [baoLoi, setBaoLoi] = useState(null);
  const userId = getIdUserByToken();
  const [url, setUrl] = useState("");
  const [checkRegisted, setCheckRegisted] = useState(false);

  // State to manage selected duration
  const [selectedDuration, setSelectedDuration] = useState(1); // Default to 1 months

  const handlePayment = async (e) => {
    e.preventDefault(); // Prevent default form submission

    const token = localStorage.getItem("token");

    const paymentUrl = `http://localhost:8080/vnpay/create-payment?userId=${userId}&id=${pack.packId}&duration=${selectedDuration}&type=pack`;

    fetch(paymentUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.text()) // Assume the response is the redirect URL
      .then((url) => {
        window.location.href = url; // Redirect the user to the payment URL
      })
      .catch((error) => {
        alert("Gặp lỗi trong quá trình đăng ký!");
        console.error("Error during payment process:", error);
      });
  };

  useEffect(() => {
    getPacktById(packId)
      .then((pack) => {
        setPack(pack);
        setDangTaiDuLieu(false);
      })
      .catch((error) => {
        setBaoLoi(error.message);
        setDangTaiDuLieu(false);
      });

    checkRegistered(getIdUserByToken(), packId).then((res) => {
      setCheckRegisted(res);
    });
  }, [packId]);

  const handleDurationChange = (event) => {
    setSelectedDuration(parseInt(event.target.value));
  };

  if (dangTaiDuLieu) {
    return (
      <div className="loading-container">
        <h1>Data Loading</h1>
      </div>
    );
  }

  if (baoLoi) {
    return (
      <div>
        <h1>Gặp lỗi khi tải chi tiết gói tập!</h1>
      </div>
    );
  }

  if (!pack) {
    return (
      <div>
        <h1>Gói tập không tồn tại!</h1>
      </div>
    );
  }

  return (
    <div className="container">
      <div className="row mt-4 mb-4">
        {/* Pack details and image */}
        <div className="col-4">
          <img
            src={pack.image}
            className="card-img-top"
            alt={pack.packInfo}
            style={{ maxWidth: "250px" }}
          />
        </div>
        <div className="col-8">
          <h1>{pack.packName}</h1>
          <div className="row mt-5">
            <div className="col-2">
              <h4>
                <i className="fa-solid fa-money-bill-wave"></i>
              </h4>
            </div>
            <div className="col-9 text-end">
              <h4>
                <strong>{dinhDangSo(pack.price) + "đ"}</strong>/tháng
              </h4>
            </div>
          </div>
          <div className="mt-4" style={{ fontSize: "25px" }}>
            <strong>Mô tả</strong>
          </div>
          <hr />
          <div dangerouslySetInnerHTML={{ __html: pack.packInfo }} />
          <hr />
          {/* Duration selection */}
      <div className="row mt-4">
        <div className="col-12">
          <div className="mb-2"><strong>Chọn thời gian đăng ký</strong></div>
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
              }` }
              style={{width:'83px'}}
              onClick={() => setSelectedDuration(12)}
            >
              1 Năm
            </button>
          </div>
          <div className="mt-3">
            <strong>Tổng số tiền thanh toán:</strong>{" "}
            {dinhDangSo(pack.price * selectedDuration) + "đ"}
          </div>
        </div>
      </div>

      {/* Apply or Renew button */}
      <div className="row mt-5">
        <div className="col-12">
          <button
            type="button"
            style={{width:"150px",height:"50px", fontSize:'20px'}}
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

      
    </div>
  );
}

export default PackDetail;
