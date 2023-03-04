import { axiosInstance } from '../../_base';
const PATH_REJEIITAR = '/usuarios/amigos';

export async function rejeitarAmigo({ id }) {
  const response = await axiosInstance.put(`${PATH_REJEIITAR}/${id}/rejeitar`, {}, {});
  return response.data;
}
