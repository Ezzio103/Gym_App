import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getIdUserByToken } from '../../utils/JWTService';
import { get1User, updateProfile } from '../../service/UserService';
// import './Profile.css'; // Import CSS riêng cho component nếu cần

const Profile = () => {
    const navigate = useNavigate();
    const [user, setUser] = useState(null); // State để lưu thông tin người dùng
    const [editMode, setEditMode] = useState(true); // State để xác định chế độ chỉnh sửa

    // Các state mới cho các trường thông tin người dùng
    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');
    const [gender, setGender] = useState('1');
    const [email, setEmail] = useState('');
    const [userName, setUserName] = useState('');
    const [birth, setBirth] = useState('');
    const [age, setAge] = useState(0);

    useEffect(() => {
        // Gọi hàm lấy thông tin người dùng khi component được render
        fetchUserData();
    }, []);

    const fetchUserData = async () => {
        try {
            // Gọi hàm lấy thông tin người dùng với idUser cụ thể
            const userData = await get1User(getIdUserByToken()); 
            setUser(userData);
            // Cập nhật các state mới với thông tin của người dùng
            setName(userData.name);
            setLastName(userData.lastName);
            setPhoneNumber(userData.phoneNumber);
            setAddress(userData.address);
            setGender(userData.gender);
            setEmail(userData.email);
            setUserName(userData.username);
            setBirth(userData.dateOfBirth);
            setAge(userData.age);
        } catch (error) {
            console.error('Error fetching user data:', error);
            // Xử lý lỗi khi gọi API không thành công
        }
    };

    const handleEdit = () => {
        setEditMode(true); // Chuyển sang chế độ chỉnh sửa
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Đặt lại editMode về false sau khi đã cập nhật thành công
        // setEditMode(false);
    
        try {
            // Gọi hàm updateProfile để gửi yêu cầu cập nhật thông tin người dùng
            const response = await updateProfile(userName, email, name, birth, age, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaNkwLyEyzTkzMxP-UkOMaOArlSmqK6O9GFw&s');
            // Xử lý kết quả trả về từ API (response) nếu cần
            console.log('Update profile success:', response);
            // setEditMode(true); // Chuyển về chế độ xem thông tin sau khi cập nhật thành công
        } catch (error) {
            console.error('Error updating profile:', error);
            // Xử lý lỗi khi gọi API không thành công
        }
    };

    return (
        <div className="container emp-profile" style={{ marginTop: "70px" }}>
            <form method="post" onSubmit={handleSubmit}>
                {user && (
                    <div className="row">
                        <div className="col-md-4">
                            <div className="profile-img">
                                <img src={user.avatar} alt="User Avatar" style={{ maxHeight: "250px" }} />
                                {editMode && (
                                    <div className="file btn  btn-primary mt-2" style={{width:'300px'}}>
                                        Change Photo
                                        <input type="file" name="file" />
                                    </div>
                                )}
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="profile-head">
                                <h5>{user.username}</h5>
                                {/* <p className="proile-rating">RANKINGS : <span>8/10</span></p> */}
                                <ul className="nav nav-tabs" id="myTab" role="tablist">
                                    {/* Add tabs if needed */}
                                </ul>
                            </div>
                        </div>
                        <div className="col-md-2 ">
                            {editMode ? (
                                <div className='d-flex'>
                                    <input type="submit" className="btn btn-success me-2" value="Save" />
                                    <button type="button" className="btn btn-secondary " onClick={() => setEditMode(true)}>Cancel</button>
                                </div>
                            ) : (
                                <button type="button" className="btn btn-primary" onClick={handleEdit}>Edit Profile</button>
                            )}
                        </div>
                    </div>
                )}
                {user && (
                    <div className="row ">
                        <div className="col-md-4">
                            {/* Additional info if needed */}
                        </div>
                        <div className="col-md-8">
                            <div className="tab-content profile-tab" id="myTabContent">
                                <div className="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                    <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="name" className="form-label">Name</label>
                                        </div>
                                        <div className="col-md-6">
                                            {editMode ? (
                                                <input type="text" className="form-control" id="name" value={name} onChange={(e) => setName(e.target.value)} />
                                            ) : (
                                                <p>{name}</p>
                                            )}
                                        </div>
                                    </div>
                                    {/* <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="lastName" className="form-label">Last Name</label>
                                        </div>
                                        <div className="col-md-6">
                                            {editMode ? (
                                                <input type="text" className="form-control" id="lastName" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                                            ) : (
                                                <p>{lastName}</p>
                                            )}
                                        </div>
                                    </div> */}
                                    {/* <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="birth" className="form-label">Date of Birth</label>
                                        </div>
                                        <div className="col-md-6">
                                            {editMode ? (
                                                <input
                                                    type="date"
                                                    className="form-control"
                                                    id="birth"
                                                    value={birth ? birth.split('T')[0] : ''}
                                                    onChange={(e) => setBirth(e.target.value)}
                                                />
                                            ) : (
                                                <p>{birth}</p>
                                            )}
                                        </div>
                                    </div> */}
                                    <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="age" className="form-label">Age</label>
                                        </div>
                                        <div className="col-md-6">
                                            {editMode ? (
                                                <input
                                                    type="number"
                                                    className="form-control"
                                                    id="age"
                                                    value={age}
                                                    onChange={(e) => setAge(Number(e.target.value))}
                                                />
                                            ) : (
                                                <p>{age}</p>
                                            )}
                                        </div>
                                    </div>
                                   
                                    {/* <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="phoneNumber" className="form-label">Phone Number</label>
                                        </div>
                                        <div className="col-md-6">
                                            {editMode ? (
                                                <input type="text" className="form-control" id="phoneNumber" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
                                            ) : (
                                                <p>{phoneNumber}</p>
                                            )}
                                        </div>
                                    </div> */}
                                    {/* <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="address" className="form-label">Address</label>
                                        </div>
                                        <div className="col-md-6">
                                            {editMode ? (
                                                <input type="text" className="form-control" id="address" value={address} onChange={(e) => setAddress(e.target.value)} />
                                            ) : (
                                                <p>{address}</p>
                                            )}
                                        </div>
                                    </div> */}
                                    <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="gender" className="form-label">Gender</label>
                                        </div>
                                        <div className="col-md-6">
                                            {editMode ? (
                                                <select className="form-control" id="gender" value={gender} onChange={(e) => setGender(e.target.value)}>
                                                    <option value="1">Male</option>
                                                    <option value="2">Female</option>
                                                </select>
                                            ) : (
                                                <p>{gender === '1' ? 'Male' : 'Female'}</p>
                                            )}
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-md-6">
                                            <label htmlFor="email" className="form-label">Email</label>
                                        </div>
                                        <div className="col-md-6">
                                            {/* {editMode ? (
                                                <input
                                                    type="email"
                                                    className="form-control"
                                                    id="email"
                                                    value={email}
                                                    onChange={(e) => setEmail(e.target.value)}
                                                />
                                            ) : */}
                                             
                                                <p>{email}</p>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                )}
            </form>
        </div>
    );
}

export default Profile;
