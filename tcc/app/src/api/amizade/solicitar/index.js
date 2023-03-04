import { axiosInstance } from '../../_base';
const PATH_SOLICITAR = '/usuarios/amigos/';

export async function solicitarAmigo({ id }) {
  const response = await axiosInstance.put(`${PATH_SOLICITAR}/${id}/solicitar`, {}, {});
  return response.data;
}
