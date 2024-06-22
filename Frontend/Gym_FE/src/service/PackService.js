import axios from 'axios'
import React from 'react'
import request from './Request';

const PackUrl= "http://localhost:8080/pack"

//  const GetAllPacks =  async () => await axios.get(PackUrl)


// Hàm này thực hiện lấy tất cả các bài đăng dựa trên trang và kích thước trang
 async function GetAllPacks(page) {
    console.log(page)
    const endpoint = `http://localhost:8080/pack?page=${ page - 1}&size=4`;
    console.log(endpoint)
   
    const response = await request(endpoint);
   
   
    const totalPages = response.page.totalPages;
    const totalPacks = response.page.totalElements;
    const packs = [];
    for (const packData of response._embedded.packs) {
       const packId = packData.packId;
       const packName = packData.packName;
       const packInfo = packData.packInfo;
       
       const image = packData.image;
       const price = packData.price;
 
  
 
       
      packs.push({packId : packId, packName : packName , packInfo : packInfo,  price : price ,image : image});
       
    }
 
    return {packs : packs, totalPages : totalPages, totalPacks : totalPacks};
 }


 // Hàm này thực hiện lấy thông tin một bài đăng dựa trên id
 async function getPacktById(packId) {
    const endpoint = `http://localhost:8080/pack/${packId}`;
    const response = await request(endpoint);
 
    // Kiểm tra nếu có dữ liệu pack trước khi xử lý
    if (response) {
       const packId = response.packId;
       const packName = response.packName;
        const packInfo = response.packInfo;
       
        const image = response.image;
        const price = response.price;
        return {packId : packId, packName : packName, packInfo : packInfo , price : price, image : image}
   
    } else {
       // Trả về null nếu không có dữ liệu
       console.log("khong co du lieu response pack");
       return null;
    }
 }
 async function searchPacksByName(keyword) {
   try {
     const encodedKeyword = encodeURIComponent(keyword); // Encode for special characters
     const endpoint = `http://localhost:8080/pack/search/findByPackNameContaining?packName=${encodedKeyword}`;
 
     const response = await axios.get(endpoint);
 
     // Handle successful response
     if (response.data && response.data._embedded && response.data._embedded.packs) {
       const packs = [];
       for (const packData of response.data._embedded.packs) {
         const packId = packData.packId;
         const packName = packData.packName;
         const packInfo = packData.packInfo;
         
         const image = packData.image;
         const price = packData.price;
 
         packs.push({
           packId,
           packName,
           packInfo,
           
           image,
           price,
         });
       }
       return Promise.all(packs); // Return only the packs array for cleaner usage
     } else {
       console.log("No packs found matching the search keyword.");
       return { packs: [] }; // Return an empty packs array to indicate no results
     }
   } catch (error) {
     console.error("Error while searching packs:", error);
     return { packs: [] }; // Return an empty packs array for error handling
   }
 }
 export { GetAllPacks , getPacktById, searchPacksByName};