import axios from 'axios';
// import { getToken } from './TokenService'; // Giả sử bạn có một hàm getToken để lấy token từ local storage

const TrainerRegistrationUrl = "http://localhost:8080/trainer-registrations";

async function getCurrentTrainerRegistrations(userId) {
    const token = localStorage.getItem("token"); // Lấy token từ local storage
    const config = {
        headers: {
           'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    };

    const endpoint = `${TrainerRegistrationUrl}/current/${userId}`;
    try {
        const response = await axios.get(endpoint, config);
        if (response && response.data) {
            console.log("Dữ liệu đăng ký huấn luyện viên hiện tại:", response.data);
            return response.data;
        } else {
            console.log("Không có dữ liệu đăng ký huấn luyện viên hiện tại");
            return null;
        }
    } catch (error) {
        console.error("Gặp lỗi khi lấy dữ liệu đăng ký huấn luyện viên hiện tại:", error);
        return null;
    }
}
async function checkTrainerRegistered(userId, trainerId) {
    const token = localStorage.getItem("token");// Lấy token từ local storage
    const config = {
        headers: {
           'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    };

    const endpoint = `http://localhost:8080/trainer/search/existsByUserIdAndTrainerIdWithSuccessStatus?userId=${userId}&trainerId=${trainerId}`;
    try {
        const response = await axios.get(endpoint, config);
        if (response && response.data) {
            console.log("Kết quả kiểm tra đăng ký huấn luyện viên:", response.data);
            return response.data; // True nếu đã đăng ký, False nếu chưa
        } else {
            console.log("Lỗi khi kiểm tra đăng ký huấn luyện viên");
            return null;
        }
    } catch (error) {
        console.error("Gặp lỗi khi kiểm tra đăng ký huấn luyện viên:", error);
        return null;
    }
}
export { getCurrentTrainerRegistrations,checkTrainerRegistered };
