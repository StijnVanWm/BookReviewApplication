import axios from "axios";

const baseUrl = import.meta.env.VITE_API_KEY;

export const fetchAllBooks = async () => {

    const response = await axios.get(`${baseUrl}/books`);
    return response;

}

export const fetchBook = async (bookId) => {

    return await axios.get(`${baseUrl}/books/${bookId}`)
    
}

export const fetchBookReviews = async (bookId, pageNo, pageSize) => {
    
    return await axios.get(`${baseUrl}/books/${bookId}/reviews`, { params: { pageNo: pageNo, pageSize: pageSize }});
}

export const postReview = async (bookId, review) => {

    return await axios.post(`${baseUrl}/books/${bookId}/reviews`, { ...review })
}

export const sendDataToApi = async (route, values) => {
    const response = await axios.post(baseUrl+route, values);
    return response;
}

export const verifyUser = async (token) => {
    const response = await axios.get(`${baseUrl}/auth/verify`, { headers: { Authorization: token }})
    return response;
}