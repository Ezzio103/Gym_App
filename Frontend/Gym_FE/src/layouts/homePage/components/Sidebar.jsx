import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { get1User } from '../../../service/UserService';
import { getIdUserByToken, getRoleByToken, logout } from '../../../utils/JWTService';

const Sidebar = ({ onLogout }) => {
  const location = useLocation();
  const [activeLink, setActiveLink] = useState(location.pathname);
  const [username, setUsername] = useState();
  const navigate = useNavigate();

  const handleLinkClick = (path) => {
    setActiveLink(path);
  };

  const handleLogout = () => {
    logout(navigate);
    onLogout();
    navigate("/login")
  };

  useEffect(() => {
    get1User(getIdUserByToken()).then(res => {
      setUsername(res.username);
    });

    if (getRoleByToken() === 'STAFF' ) {
      setActiveLink('/pack');
    } else if(!['ADMIN', 'STAFF'].includes(getRoleByToken())) {
      navigate('/login');
    }
  }, []);

  return (
    <div className="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style={{ width: '280px', height: '100vh' }}>
      <Link to="/" className="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
        <svg className="bi me-2" width="40" height="32">
          <use xlinkHref="#bootstrap" />
        </svg>
        <span className="fs-4">Gym Manager</span>
      </Link>
      <hr />
      <ul className="nav nav-pills flex-column mb-auto">
        {getRoleByToken() === 'ADMIN' && (
          <li className="nav-item">
            <Link
              to="/"
              className={`nav-link ${activeLink === '/' ? 'active' : 'text-white'}`}
              aria-current="page"
              onClick={() => handleLinkClick('/')}
            >
              <svg className="bi me-2" width="16" height="16">
                <use xlinkHref="#home" />
              </svg>
              Dashboard
            </Link>
          </li>
        )}
        <li>
          <Link
            to="/pack"
            className={`nav-link ${activeLink === '/pack' ? 'active' : 'text-white'}`}
            onClick={() => handleLinkClick('/pack')}
          >
            <svg className="bi me-2" width="16" height="16">
              <use xlinkHref="#speedometer2" />
            </svg>
            Gói tập
          </Link>
        </li>
        <li>
          <Link
            to="/users"
            className={`nav-link ${activeLink === '/users' ? 'active' : 'text-white'}`}
            onClick={() => handleLinkClick('/users')}
          >
            <svg className="bi me-2" width="16" height="16">
              <use xlinkHref="#table" />
            </svg>
            Người dùng
          </Link>
        </li>
        <li>
          <Link
            to="/trainer"
            className={`nav-link ${activeLink === '/trainer' ? 'active' : 'text-white'}`}
            onClick={() => handleLinkClick('/trainer')}
          >
            <svg className="bi me-2" width="16" height="16">
              <use xlinkHref="#grid" />
            </svg>
            Huấn luyện viên
          </Link>
        </li>
        <li>
          <Link
            to="/equipment"
            className={`nav-link ${activeLink === '/equipment' ? 'active' : 'text-white'}`}
            onClick={() => handleLinkClick('/equipment')}
          >
            <svg className="bi me-2" width="16" height="16">
              <use xlinkHref="#people-circle" />
            </svg>
            Thiết bị
          </Link>
        </li>
        <li>
          <Link
            to="/room"
            className={`nav-link ${activeLink === '/room' ? 'active' : 'text-white'}`}
            onClick={() => handleLinkClick('/room')}
          >
            <svg className="bi me-2" width="16" height="16">
              <use xlinkHref="#people-circle" />
            </svg>
            Phòng tập
          </Link>
        </li>
        <li>
          <Link
            to="/feedback-staff"
            className={`nav-link ${activeLink === '/feedback-staff' ? 'active' : 'text-white'}`}
            onClick={() => handleLinkClick('/feedback-staff')}
          >
            <svg className="bi me-2" width="16" height="16">
              <use xlinkHref="#people-circle" />
            </svg>
            Phản hồi
          </Link>
        </li>
      </ul>
      <hr />
      <div className="dropdown">
        <Link to="#" className="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
          <img src="https://github.com/mdo.png" alt="" width="32" height="32" className="rounded-circle me-2" />
          <strong>{username}</strong>
        </Link>
        <ul className="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
          <li><Link className="dropdown-item" to="/profile">Profile</Link></li>
          <li><hr className="dropdown-divider" /></li>
          <li><button className="dropdown-item" onClick={handleLogout}>Sign out</button></li>
        </ul>
      </div>
    </div>
  );
};

export default Sidebar;
