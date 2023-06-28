import axios from 'axios';

const API_URL = 'http://localhost:8080/api/user';

export const getAllUsers = async () => {
  const response = await axios.get(`${API_URL}/get/all`);
  return response.data;
};

export const getUser = async (id) => {
  const response = await axios.get(`${API_URL}/get/${id}`);
  return response.data;
};

export const createUser = async (user) => {
  const response = await axios.post(`${API_URL}/create`, user);
  return response.data;
};

export const updateUser = async (user) => {
  const response = await axios.put(`${API_URL}/update`, user);
  return response.data;
};

export const deleteUser = async (id) => {
  const response = await axios.delete(`${API_URL}/delete/${id}`);
  return response.data;
};
