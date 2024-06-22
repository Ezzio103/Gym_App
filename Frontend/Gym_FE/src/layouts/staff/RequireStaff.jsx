import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import jwtDecode from "jwt-decode";

const RequireStaff = (WrappedComponent) => {
  const WithStaffCheck = (props) => {
    const navigate = useNavigate();

    useEffect(() => {
      const token = localStorage.getItem("token");

      // If not logged in, navigate to /login
      if (!token) {
        navigate("/login");
        return;
      }

      // Decode the token
      const decodedToken = jwtDecode(token);

      // Get role from the token
      const role = decodedToken.role;

      // Check for admin role
      if (['ADMIN', 'STAFF'].includes(role)) {
        navigate("/error-403");
      }
    }, [navigate]);

    return <WrappedComponent {...props} />;
  };

  return WithStaffCheck;
};

export default RequireStaff;
