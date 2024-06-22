import axios from 'axios';
import request from './Request';
// import { getToken } from './TokenService'; // Giả sử bạn có một hàm getToken để lấy token từ local storage

const PackRegistrationUrl = "http://localhost:8080/pack-registrations";

// Hàm này thực hiện lấy danh sách các gói tập hiện tại của một người dùng dựa trên userId
async function getCurrentPacks(userId) {
    const token = localStorage.getItem("token"); // Lấy token từ local storage
    const config = {
        headers: {
           'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    };

    // fetch(`${PackRegistrationUrl}/current/${userId}`, {
    //     method: "GET",
    //     headers: {
    //          'Content-Type': 'application/json',
    //         'Authorization': `Bearer ${token}`
    //     },
    // })
    //     .then(async (response) => {
    //         if (response.ok) {
    //             const currentPacks = await response.json(); // Phân tích dữ liệu JSON
    //             // Giả sử dữ liệu là một mảng các đối tượng PackRegistration
          
    //             console.log("Gói đăng ký hiện tại:", currentPacks);
               
    //             // toast.success("Xoá người dùng thành công");
    //             // props.setKeyCountReload(Math.random());
    //         } else {
    //             alert("Lỗi response");
    //             // toast.error("Lỗi khi xoá người dùng");
    //         }
    //     })
    //     .catch((error) => {
    //         alert("Lỗi ");
    //         console.log(error);
    //     })

    const endpoint = `${PackRegistrationUrl}/current/${userId}`;
    try {
        const response = await axios.get(endpoint, config);
        if (response && response.data) {
            // console.log(response)
            // console.log(response)
            console.log( "res data ",response.data);
            return response.data;
        } else {
            console.log("Không có dữ liệu gói tập hiện tại");
            return null;
        }
    } catch (error) {
        console.error("Gặp lỗi khi lấy dữ liệu gói tập hiện tại:", error);
        return null;
    }
}

// Hàm này kiểm tra xem người dùng đã đăng ký gói tập nào chưa
async function checkRegistered(userId, packId) {
        const token = localStorage.getItem("token"); // Lấy token từ local storage
        const config = {
        headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
            }
        };
        // http://localhost:8080/pack-registration/search/existsByUserIdAndPackIdWithSuccessStatus?userId=1&packId=2

        const endpoint = `http://localhost:8080/pack-registration/search/existsByUserIdAndPackIdWithSuccessStatus?userId=${userId}&packId=${packId}`;
        // console.log(endpoint)
        try {
            const response = await axios.get(endpoint, config);
            if (response && response.data) {
                // console.log(response.data)
            return response.data; // True nếu đã đăng ký, False nếu chưa
            } else {
            console.log("Lỗi khi kiểm tra đăng ký");
            return null;
            }
            } catch (error) {
        console.error("Gặp lỗi khi kiểm tra đăng ký:", error);
            return null;
    }
    }


export { getCurrentPacks,checkRegistered };
