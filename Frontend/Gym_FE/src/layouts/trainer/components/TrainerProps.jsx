import React from "react";
import { Link } from "react-router-dom";
import { dinhDangSo } from "../../../utils/DinhSangSo";

const TrainerProps = ({ trainer }) => {
   return (
      <div className="col-md-3 mt-2">
         <div className="card text-align-center">
            <Link to={`/trainer/${trainer.trainerId}`}>
               <img
                  src={trainer.avatar}
                  className="card-img-top"
                  alt={trainer.name}
                  style={{ height: '300px' }}
               />
            </Link>
            <div className="card-body">
               <h5 className="card-title">{trainer.name}</h5>
               <p className="card-text">{trainer.experience}</p>
               <div className="row mt-2">
                  <div className="col-12 text-end">
                     <Link to={`/trainer/${trainer.trainerId}`} style={{ textDecoration: 'none' }}>
                        <button className="btn btn-primary btn-block">Xem Chi Tiết</button>
                     </Link>
                  </div>
               </div>
            </div>
         </div>
      </div>
   );
};

export default TrainerProps;
