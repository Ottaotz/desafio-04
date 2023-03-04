import { axiosInstance } from '../../_base';
const PATH_LOGIN = '/login';

export async function login({ username, password }) {
  const response = await axiosInstance.post(
    PATH_LOGIN,
    {},
    {
      auth: {
        username,
        password,
      },
    }
  );
  return response.data;
}
