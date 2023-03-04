import { axiosInstance } from '../../_base';
const PATH_ADICIONAR = '/usuarios/amigos';

export async function adicionarAmigo({ id }) {
  const response = await axiosInstance.put(`${PATH_ADICIONAR}/${id}/adicionar`, {}, {});
  return response.data;
}
