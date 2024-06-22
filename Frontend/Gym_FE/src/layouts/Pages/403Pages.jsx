// import { Button } from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";
// import useScrollToTop from "../../hooks/ScrollToTop"; // Assuming ScrollToTop hook is implemented

const Error403Page = () => {
//   useScrollToTop(); // Call the hook to scroll to the top on mount

  return (
    <div className="container text-center text-black" style={{ height: "85vh" }}>
      <p className="fw-bolder" style={{ fontSize: "200px" }}>
        403!
      </p>
      <p className="fs-2">Bạn không có quyền vào trang này</p>
      <Link to="/">
        <button className="btn btn-primary" >Về trang chủ</button>
      </Link>
    </div>
  );
};

export default Error403Page; // Assuming export is needed
