import React from "react";
import "./Banner.css"; // Tạo một file CSS riêng cho styling

function Banner() {
    return (
        <div className="banner-container mb-2 ">
            <div className="container-fluid py-5 d-flex justify-content-center align-items-center banner-content">
                <div>
                    <h3 className="display-5 fw-bold banner-text-custom">
                        Gym Pro
                    </h3>
                    <p className="">Gym Manager</p>
                </div>
            </div>
        </div>
    );
}

export default Banner;
