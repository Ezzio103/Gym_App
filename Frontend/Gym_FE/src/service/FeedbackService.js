import axios from 'axios';
import request from './Request';

const FeedbackUrl = "http://localhost:8080/feedback";

// Hàm này thực hiện lấy danh sách feedback dựa trên trang và kích thước trang
async function GetAllFeedbacks(page) {
    const endpoint = `${FeedbackUrl}?page=${page - 1}&size=5`;
    const response = await request(endpoint);

    if (response) {
        const totalPages = response.page.totalPages;
        const totalFeedbacks = response.page.totalElements;

        // Mapping through each feedback to fetch user info and assign to userId
        const feedbacks = await Promise.all(response._embedded.feedbacks.map(async (feedback) => {
            const userResponse = await axios.get(feedback._links.user.href);
            const user = userResponse.data; // Assuming user data contains userId

            return {
                feedbackId: feedback.feedbackId,
                userId: user.userId,
                username : user.username,
                content: feedback.content,
                time: feedback.time
            };
        }));

        return { feedbacks: feedbacks, totalPages: totalPages, totalFeedbacks: totalFeedbacks };
    } else {
        console.error("Error retrieving feedbacks");
        return null;
    }
}

export { GetAllFeedbacks };
