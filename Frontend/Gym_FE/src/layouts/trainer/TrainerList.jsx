import React, { useState, useEffect } from "react";
import { Button, Modal } from 'react-bootstrap';
import TrainerProps from "./components/TrainerProps";
import { GetAllTrainers, searchTrainersByName } from "../../service/TrainerService";
import { PhanTrang } from "../../utils/PhanTrang";

function TrainerList({}) {
   const [trainers, setTrainers] = useState([]);
   const [dataLoading, setDataLoading] = useState(true);
   const [baoLoi, setBaoLoi] = useState(null);
   const [trangHienTai, setTrangHienTai] = useState(1);
   const [tongSoTrang, setTongSoTrang] = useState(0);
   const [search, setSearch] = useState(true);
   const [showModal, setShowModal] = useState(false);
   const [trainerName, setTrainerName] = useState('');

   const handleShow = () => setShowModal(true);
   const handleClose = () => setShowModal(false);

   const handleFilterApply = async () => {
      await searchTrainersByName(trainerName).then(
         (res) => {
            if (res != null) {
               setTrainers(res);
            } else {
               console.log("Không tìm thấy Trainer");
            }
         }
      );
      setSearch(!search);
      setShowModal(false);
   };

   useEffect(() => {
      setTrangHienTai(1);
   }, [search]);

   useEffect(() => {
      GetAllTrainers(trangHienTai).then(
         res => {
            setTrainers(res.trainers);
            setTongSoTrang(res.totalPages);
            setDataLoading(false);
         }
      ).catch(
         error => {
            setBaoLoi(error.message)
         }
      );
   }, [trangHienTai]);

   const phanTrang = (trangHienTai) => {
      setTrangHienTai(trangHienTai)
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

   if (trainers.length === 0) {
      return (
         <div className="container mt-5">
            <div className="d-flex align-items-center justify-content-center">
               <h1>Hiện không tìm thấy trainer!</h1>
            </div>
         </div>
      );
   }

   return (
      <div className="container">
         {/* Modal Filter */}
         <Modal show={showModal} onHide={handleClose}>
            <Modal.Header closeButton>
               <Modal.Title>Filter</Modal.Title>
            </Modal.Header>
            <Modal.Body>
               <div className="mb-3">
                  <label htmlFor="trainerName" className="form-label">Trainer Name</label>
                  <input type="text" className="form-control form-control-sm" id="trainerName" value={trainerName} onChange={(e) => setTrainerName(e.target.value)} />
               </div>
            </Modal.Body>
            <Modal.Footer>
               <Button variant="secondary" onClick={handleClose}>Close</Button>
               <Button variant="primary" onClick={handleFilterApply}>Apply Filter</Button>
            </Modal.Footer>
         </Modal>

         <div className="row">
            <div className="col-8 ">
               <Button variant="btn-primary " onClick={handleShow}>
                  <i className="fas fa-filter"></i> Filter
               </Button>
            </div>
            <div className="col-4 text-end">
               <div className="d-flex">
                  <input
                     className="form-control me-2"
                     type="search"
                     placeholder="Tìm kiếm theo tên"
                     aria-label="Search"
                     value={trainerName}
                     onChange={(e) => setTrainerName(e.target.value)} // Update paskName khi có thay đổi
                  />
                  <button className="btn btn-outline-success" type="button" onClick={handleFilterApply}>
                     <i className="fas fa-search"></i>
                  </button>
               </div>
            </div>
         </div>

         <div className="row mt-4 list-trainer">
            {
               trainers.map((trainer) => {
                  return <TrainerProps key={trainer.trainerId} trainer={trainer} />;
               })
            }
         </div>
         <PhanTrang trangHienTai={trangHienTai} tongSoTrang={tongSoTrang} phanTrang={phanTrang} />
      </div>
   );
}

export default TrainerList;
