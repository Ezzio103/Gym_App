import { jwtDecode } from "jwt-decode";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getRoleByToken } from "../utils/JWTService";
// import jwtDecode from "jwt-decode";

const RequireAdmin = (WrappedComponent) => {
  const WithAdminCheck = (props) => {
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
      const role = getRoleByToken();

      // Check for admin role
      if (role !== "ADMIN" ) {
        if(role === "STAFF"){
        console.log(role)
        navigate("/pack");}
      else{
        navigate("/403-error")
      }}
    }, [navigate]);

    return <WrappedComponent {...props} />;
  };

  return WithAdminCheck;
};

export default RequireAdmin;
