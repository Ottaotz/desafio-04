import { axiosInstance } from '../../_base';
const PATH_SAIR = '/logout';

export async function sair() {
  const response = await axiosInstance.get(PATH_SAIR, {});
  return response.data;
}
