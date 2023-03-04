import { axiosInstance } from '../_base';
const PATH_USUARIO = '/usuarios/me/detalhe';

export async function usuario() {
  const response = await axiosInstance.get(PATH_USUARIO, {});
  return response.data;
}
