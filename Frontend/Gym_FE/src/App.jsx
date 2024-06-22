import { useEffect, useState } from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Login from './layouts/user/Login';
import ListUser from './layouts/user/ListUser';
import SignUp from './layouts/user/SignUp';
import AccountActive from './layouts/user/AccountActive';
import HomePage from './layouts/homePage/HomePage';
import Navbar from './layouts/header/Navbar';
import Profile from './layouts/user/Profile';
import PackDetail from './layouts/pack/PackDetail';
import PackManager from './admin/PackManager';
import EquipmentManager from './admin/EquipmentManager';
import RoomManager from './admin/RoomManager';
import TrainerManager from './admin/TrainerManager';
import PaymentSuccess from './layouts/vnpay/PaymentSuccess';
import CurrentRegist from './layouts/pack/CurrentRegist';
import Feedback from './layouts/Pages/Feedback';
import FeedbackList from './layouts/staff/FeedbackList';
import Error403Page from './layouts/Pages/403Pages';
import DashboardPage from './admin/Dashboard';
import Sidebar from './layouts/homePage/components/Sidebar';
import { getIdUserByToken, getRoleByToken } from './utils/JWTService';
import TrainerDetail from './layouts/trainer/TrainerDetail';
import TrainerSchedule from './layouts/user/CalendarPT';

function App() {
  const [role, setRole] = useState(getRoleByToken());
  const [update, setUpdate] = useState(false);

  useEffect(() => {
    setRole(getRoleByToken());
  }, [update]);

  const handleLoginSuccess = () => {
    setRole(getRoleByToken());
  };

  const handleLogout = () => {
    setRole(null);
    setUpdate(!update);
  };

  return (
    <BrowserRouter>
      {!['ADMIN', 'STAFF'].includes(role) && (
        <>
          <Navbar />
          <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/pack/:packId' element={<PackDetail />} />
            <Route path='/trainer/:trainerId' element={<TrainerDetail />} />
            <Route path='/profile' element={<Profile />} />
            <Route path='/payment-success' element={<PaymentSuccess />} />
            <Route path='/current-regist' element={<CurrentRegist />} />
            <Route path='/feedback' element={<Feedback />} />
          </Routes>
        </>
      )}
      <Routes>
      <Route path='/schedule' element={<TrainerSchedule  trainerId={1} userId={getIdUserByToken()}/>} />
        <Route path='/login' element={<Login onLoginSuccess={handleLoginSuccess} />} />
        <Route path='/sign-up' element={<SignUp />} />
        <Route path='/account-active/:email/:maKichHoat' element={<AccountActive />} />
        <Route path='/403-error' element={<Error403Page />} />
      </Routes>
      {['ADMIN', 'STAFF'].includes(role) && (
        <div className='row overflow-hidden w-100'>
          <div className='col-2 col-md-3 col-lg-2'>
            <Sidebar onLogout={handleLogout} />
          </div>
          <div className='col-10 col-md-9 col-lg-10'>
            <Routes>
            
              <Route path='/pack' element={<PackManager />} />
              <Route path='/equipment' element={<EquipmentManager />} />
              <Route path='/room' element={<RoomManager />} />
              <Route path='/trainer' element={<TrainerManager />} />
              <Route path='/feedback-staff' element={<FeedbackList />} />
              <Route path='/profile' element={<Profile />} />
              <Route path='/users' element={<ListUser />} />
              <Route path='/' element={<DashboardPage />} />
            </Routes>
          </div>
        </div>
      )}
    </BrowserRouter>
  );
}

export default App;
