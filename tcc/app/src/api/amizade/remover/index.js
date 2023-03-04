import { axiosInstance } from '../../_base';
const PATH_REMOVER = '/usuarios/amigos';

export async function removerAmigo({ id }) {
  const response = await axiosInstance.put(`${PATH_REMOVER}/${id}/remover`, {});
  return response.data;
}
