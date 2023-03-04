import { axiosInstance } from '../_base';
const PATH_USUARIO = '/usuarios/pesquisar';

export async function listarUsuarios({ text }) {
  const response = await axiosInstance.get(`${PATH_USUARIO}?text=${text}`, {});
  return response.data;
}
