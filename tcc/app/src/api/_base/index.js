import axios from 'axios';

const BASE_API = 'http://localhost:3000/api';

export const axiosInstance = axios.create({
  baseURL: BASE_API,
  withCredentials: true,
  timeout: 5000,
});
