import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { getRoleByToken } from "../../utils/JWTService";

const Login = ({ onLoginSuccess }) => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')
    const navigate = useNavigate();

    const handleLogin = () => {
        const loginRequest = {
            username: username,
            password: password
        };

        fetch('http://localhost:8080/account/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginRequest)
        }).then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Đăng nhập thất bại!')
            }
        }).then(data => {
            const { jwt } = data;
            localStorage.setItem('token', jwt);
            setError('Đăng nhập thành công!');
            onLoginSuccess(); // Notify the App component of the successful login
            // if()
            navigate("/");
        }).catch(error => {
            console.error('Đăng nhập thất bại: ', error);
            setError('Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.');
        });
    }

    return (
        <div className='container-fluid row justify-content-center'>
            <div className="form-signin col-4" style={{marginTop:"100px"}}>
                <h1 className="h3 mb-3 font-weight-normal">Đăng nhập</h1>
                <label className="sr-only">Tên đăng nhập</label>
                <input type="username" id="username" className="form-control mb-2" placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <label className="sr-only">Password</label>
                <input type="password" id="inputPassword" className="form-control mb-2" placeholder="Password" required
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <div className=" mb-3 ">
                    Chưa có tài khoản?
                <Link to={`/sign-up`} style={{ textDecoration: 'none', marginRight: "5px" }}>
                
                  Đăng ký
                
              </Link>
                </div>
                <button className="btn btn-lg btn-primary btn-block" type="button"
                    onClick={handleLogin}
                >Đăng nhập</button>
                {error && <div style={{ color: 'red' }}>{error}</div>}
            </div>
        </div>
    );
}

export default Login;
