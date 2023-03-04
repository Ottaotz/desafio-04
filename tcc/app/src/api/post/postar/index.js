import { axiosInstance } from '../../_base';
const PATH_POSTAR = '/posts/';

export async function postar({ imagem, legenda, visualizacao }) {
  const response = await axiosInstance.post(PATH_POSTAR, { imagem, legenda, visualizacao }, {});
  return response.data;
}
