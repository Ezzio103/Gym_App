import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getPacktById } from '../../../service/PackService';

const CurrentRegistProps = (props) => {
  const [packRegist, setPackRegist] = useState(props.pack);
  const [pack, setPack] = useState({});
  const navigate = useNavigate();
  const token = localStorage.getItem('token');
   
  useEffect(() => {
    const fetchPackData = async () => {
      try {
        const res = await getPacktById(packRegist.packId);
        setPack(res);
        console.log('Dữ liệu gói: ', res);
      } catch (error) {
        console.error("Lỗi khi lấy dữ liệu gói:", error);
      }
    };

    fetchPackData();
  }, [packRegist.packId]);



  return (
    <div className="col-md-3 mt-2">
      <div className="card text-align-center">
        <div className="card-body">
            <Link to={`/#`}>
                <img
                    src={pack.image}
                    className="card-img-top"
                    alt={pack.packName}
                    style={{ height: '300px' }}
                /></Link>
          <Link to={`/#`} style={{textDecoration:'none'}}>
            <h5 className="card-title mt-2">{pack.packName}</h5>
          </Link>
          <p className="card-text">{pack.description}</p>
          <div className="row mt-2">
            {/* <div className="col-2 text-end"> <i className="fas fa-user"></i></div> */}
            <div className="col-12 " style={{marginLeft:"10px"}}><p className="card-text">{pack.packInfo} </p></div>
            
            <div className="col-11 " style={{color:"red" ,marginLeft:"10px"}}>  Thời hạn: {packRegist.expiryDate}</div>
            {/* <div className="col-7 text-end"><p className="card-text">{packRegist.expiryDate} </p></div> */}
          </div>
          <div className="row mt-2" role="group">
            <div className="col-12 text-end">
              <Link to={`/pack/${pack.packId}`} style={{textDecoration:'none', marginRight:"5px"}}>
                <button className="btn btn-primary btn-block">
                  Xem Chi Tiết
                </button>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CurrentRegistProps;


 {/* <Link to={`/#`}>
                <img
                    src={pack.image}
                    className="card-img-top"
                    alt={pack.packName}
                    style={{ height: '300px' }}
                /></Link> */}