import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Banner from "./components/Banner";
import PackList from "../pack/PackList";
import Sidebar from "./components/Sidebar";
import { getRoleByToken } from "../../utils/JWTService";
import TrainerList from "../trainer/TrainerList";

function HomePage() {
  const [type, setType] = useState(1);
  const role = getRoleByToken();
  const navigate = useNavigate();
  const location = useLocation();

  // Hàm để chuyển đổi type giữa 0 và 1
  const toggleType = () => {
    setType((prevType) => (prevType === 1 ? 0 : 1));
  };

  // Effect để điều khiển việc hiển thị Navbar dựa trên đường dẫn hiện tại
  React.useEffect(() => {
    const { pathname } = location;
    // Có thể thêm điều kiện hiển thị Navbar tại đây nếu cần
  }, [location.pathname]);

  return (
    <div className="container-fluid">
      <div className="row">
        {/* Sidebar chỉ hiển thị khi là ADMIN hoặc STAFF */}
        {["ADMIN", "STAFF"].includes(role) && (
          <div className="col-md-3">
            <Sidebar />
          </div>
        )}

        <div className={`col-md-${role === "ADMIN" || role === "STAFF" ? 9 : 12}`}>
          {/* Hiển thị Banner trên cùng */}
          <Banner />

          {/* Nút để chuyển đổi giữa PackList và TrainerList */}
          <div className="mt-3">
            <div className="row ">
                <div className="col-3"></div>
            <button
              className={`btn ${type === 1 ? "btn-primary" : "btn-secondary"} me-2 col-3 ` }
            //   style={{marginLeft:"45px"}}
              onClick={() => setType(1)}
            >
              Danh sách gói tập
            </button>
            <button
              className={`btn ${type === 0 ? "btn-primary" : "btn-secondary"} col-3`}
              onClick={() => setType(0)}
            >
              Danh sách huấn luyện viên
            </button></div>
          </div>
        <div className="mt-3">
          {/* Hiển thị PackList hoặc TrainerList tùy thuộc vào giá trị của type */}
          {type ? <PackList tuKhoaTimKiem={''} maTheLoai={0} /> : <TrainerList tuKhoaTimKiem={''} maTheLoai={0} />}
        </div></div>
      </div>
    </div>
  );
}

export default HomePage;
