import { jwtDecode } from "jwt-decode";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
// import { getRoleByToken } from "../utils/JWTService";
// import jwtDecode from "jwt-decode";

const RequireLogin = (WrappedComponent) => {
  const WithLoginCheck = (props) => {
    const navigate = useNavigate();

    useEffect(() => {
      const token = localStorage.getItem("token");

      // If not logged in, navigate to /login
      if (!token) {
        navigate("/login");
        return;
      }

      // Decode the token
     
    }, [navigate]);

    return <WrappedComponent {...props} />;
  };

  return WithLoginCheck;
};

export default RequireLogin;
