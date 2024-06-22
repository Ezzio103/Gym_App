import React, { useEffect, useState } from "react";


import { Link, useNavigate } from "react-router-dom";

import { jwtDecode } from "jwt-decode";

import { dinhDangSo } from "../../../utils/DinhSangSo";

// interface PackPropsInterface{
//     pack: PackModel;
//     postId : number;
// }

const PackProps = (props) => {
    const maSach  = props.pack.packId;
    const [pack, setPack] = useState(props.pack);
    
    const [danhSachAnh, setDanhSachAnh] = useState('');
   const [dangTaiDuLieu,setDangTaiDuLieu] = useState(true);
   const [baoLoi, setBaoLoi] = useState(null);
   const navigate = useNavigate();
   const token = localStorage.getItem('token');
   
   const addIntoCart =  ()=>{
//     if (!token) {
//         navigate("/dang-nhap");
//         return;
//     } else {
//         // Giải mã token
//         const decodedToken = jwtDecode(token) as JwtPayload;
//         await addItemIntoCart(decodedToken.id,maSach);
//    }
}

    // useEffect(()=>{
    //     layToanBoAnhCuaMotSach(maSach).then(
    //         hinhAnhData => {
    //             setDanhSachAnh(hinhAnhData);
    //             setDangTaiDuLieu(false);
    //             // console.log(danhSachAnh)
               
    //         }
    //     ).catch(
    //         error =>{
    //             setBaoLoi(error.message)
    //         }
    //     );
    // },[])
//    if(dangTaiDuLieu){
//     return(
//         <div> <h1> Dang tai du lieu</h1> </div>
//     )
//    }
//    if(baoLoi){
//     return(
//         <div>
//             <h1>Gap loi</h1>
//         </div>
//     )
//    }
//    let duLieuAnh:string = '';
   
//    if(danhSachAnh[0] && danhSachAnh[0].duLieuAnh){
//     duLieuAnh = danhSachAnh[0].duLieuAnh;
//    }
   // 2*
    return (
        <div className="col-md-3 mt-2">
            <div className="card text-align-center">
                <Link to={`/#`}>
                <img
                    src={pack.image}
                    className="card-img-top"
                    alt={pack.packName}
                    style={{ height: '300px' }}
                /></Link>
                <div className="card-body">
                    <Link to={`/#`} style={{textDecoration:' none'}}>
                    <h5 className="card-title" > {pack.packName}</h5>
                    </Link>
                    <p className="card-text">{pack.description}</p>
                   
                   
                    <div className="row mt-2">
                    <div className="col-2 text-end"><i class="fa-solid fa-file-lines"></i></div>
                    <div className="col-10 text-end"><p className="card-text">{pack.packInfo}</p></div>
                        <div className="col-2 text-end mt-2"><i class="fa-solid fa-money-bill-wave"></i></div>
                        <div className="col-10 text-end mt-2"><p className="card-text"><strong>{dinhDangSo(pack.price)}đ/tháng</strong></p></div>
                   
                    </div>
                   
                    <div className="row mt-2" role="group">
                       
                        <div className="col-12 text-end">
                        <Link to={`/pack/${props.pack.packId}`} style={{textDecoration:' none',marginRight:"5px"}}>
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
export default PackProps;