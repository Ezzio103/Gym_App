import React, { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap';
import { GetAllFeedbacks } from '../../service/FeedbackService';
import { PhanTrang } from '../../utils/PhanTrang';
import FeedbackProps from './components/FeedbackProps';
import "./css/FeedbackList.css"
function FeedbackList() {
    const [feedbacks, setFeedbacks] = useState([]);
    const [dataLoading, setDataLoading] = useState(true);
    const [baoLoi, setBaoLoi] = useState(null);
    const [trangHienTai, setTrangHienTai] = useState(1);
    const [tongSoTrang, setTongSoTrang] = useState(0);
    const [tongSoFeedbacks, setTongSoFeedbacks] = useState(0);
    const [showModal, setShowModal] = useState(false);

    const handleShow = () => setShowModal(true);
    const handleClose = () => setShowModal(false);

    useEffect(() => {
        setTrangHienTai(1);
    }, []);

    useEffect(() => {
        GetAllFeedbacks(trangHienTai).then(
            res => {
                setFeedbacks(res.feedbacks);
                setTongSoTrang(res.totalPages);
                setTongSoFeedbacks(res.totalFeedbacks);
                setDataLoading(false);
            }
        ).catch(
            error => {
                setBaoLoi(error.message);
            }
        );
    }, [trangHienTai]);

    const phanTrang = (trangHienTai) => {
        setTrangHienTai(trangHienTai);
    };

    if (dataLoading) {
        return (
            <div className="loading-container">
                <h1>Data Loading</h1>
            </div>
        );
    }

    if (baoLoi) {
        return (
            <div>
                <h1>ERROR</h1>
            </div>
        );
    }

    if (feedbacks.length === 0) {
        return (
            <div className="container mt-5">
                <div className="d-flex align-items-center justify-content-center">
                    <h1>Hiện không có feedback nào!</h1>
                </div>
            </div>
        );
    }

    return (
        <div className="container parent-box1">
            <div className="row mt-2">
                <div className="col-1"></div>
                <div className="col-10"><h1>Xem phản hồi</h1></div>
            </div>

          

            <div className="row mt-4  content-box1">
                <div className="col-8">
                {
                    feedbacks.map((feedback) => (
                        <FeedbackProps key={feedback.feedbackId} feedback={feedback} />
                    ))
                }</div>
            </div>

            <PhanTrang trangHienTai={trangHienTai} tongSoTrang={tongSoTrang} phanTrang={phanTrang} />
        </div>
    );
}

export default FeedbackList;
