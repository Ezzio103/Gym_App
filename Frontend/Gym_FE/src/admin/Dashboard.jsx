import React, { useEffect, useState } from 'react';
import { Line, Bar } from 'react-chartjs-2';
import axios from 'axios';
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, BarElement, Title, Tooltip, Legend } from 'chart.js';
import './css/dashboardcss.css'; // Ensure this file exists and contains the styles
import RequireAdmin from './RequireAdmin';

// Register required components
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, BarElement, Title, Tooltip, Legend);

const Dashboard = () => {
    const [revenueData, setRevenueData] = useState([]);
    const [registrationData, setRegistrationData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [selectedMonths, setSelectedMonths] = useState(6);
    const token = localStorage.getItem("token");
    const config = {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    };

    const fetchStatistics = (months) => {
        setIsLoading(true);
        // Fetch revenue data
        axios.get(`http://localhost:8080/statistics/revenue/monthly?months=${months}`, config)
            .then(response => {
                setRevenueData(response.data);
                setIsLoading(false);
            })
            .catch(error => {
                console.error('There was an error fetching the revenue data!', error);
                setIsLoading(false);
            });

        // Fetch registration data
        axios.get(`http://localhost:8080/statistics/registrations/monthly?months=${months}`, config)
            .then(response => {
                setRegistrationData(response.data);
                setIsLoading(false);
            })
            .catch(error => {
                console.error('There was an error fetching the registration data!', error);
                setIsLoading(false);
            });
    };

    useEffect(() => {
        fetchStatistics(selectedMonths);
    }, [selectedMonths]);

    const handleMonthsChange = (event) => {
        setSelectedMonths(event.target.value);
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="App container">
            
            {/* <div className="100vh" style={{justifyContent:'start',alignItems:'flex-start'}}>aaa</div> */}
            {/* <div style={{display:'block',textAlign:'end'}}></div> */}
            <div className="dropdown" style={{alignSelf:'self-end'}}>
                <label htmlFor="monthsSelect me-2" style={{marginRight:'10px'}}><strong> Chọn thời gian thống kê: </strong> </label>
                <select id="monthsSelect " value={selectedMonths} onChange={handleMonthsChange} style={{borderRadius:'5px'}}>
                    
                    <option value="3">3 tháng</option>
                    <option value="6">6 tháng</option>
                    <option value="12">1 năm</option>
                </select>
            </div>
            

            <div className="dataCard revenueCard">
                <Line
                    data={{
                        labels: revenueData.map(data => data.label),
                        datasets: [
                            {
                                label: 'Doanh thu',
                                data: revenueData.map(data => data.value),
                                backgroundColor: '#064FF0',
                                borderColor: '#064FF0',
                            },
                        ],
                    }}
                    options={{
                        maintainAspectRatio: false,
                        responsive: true,
                        elements: {
                            line: {
                                tension: 0,
                            },
                        },
                        plugins: {
                            title: {
                                display: true,
                                align: 'start',
                                text: 'Doanh thu hàng tháng',
                                font: {
                                    size: 20,
                                },
                                color: 'black',
                            },
                        },
                    }}
                />
            </div>

            <div className="dataCard customerCard mt-3">
                <Bar
                    data={{
                        labels: registrationData.map(data => data.label),
                        datasets: [
                            {
                                label: 'Đăng ký mới',
                                data: registrationData.map(data => data.value),
                                backgroundColor: '#064FF0',
                                borderColor: '#064FF0',
                            },
                            {
                                label: 'Gia hạn',
                                data: registrationData.map(data => data.value2),
                                backgroundColor: '#FF3030',
                                borderColor: '#FF3030',
                            },
                        ],
                    }}
                    options={{
                        maintainAspectRatio: false,
                        responsive: true,
                        plugins: {
                            title: {
                                display: true,
                                align: 'start',
                                text: 'Đăng ký mới và Gia hạn',
                                font: {
                                    size: 20,
                                },
                                color: 'black',
                            },
                        },
                    }}
                />
            </div>
        </div>
    );
};

const DashboardPage = RequireAdmin(Dashboard);
export default DashboardPage;
