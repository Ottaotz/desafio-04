import { axiosInstance } from '../_base';
const PATH_COMENTAR = '/comentarios';

export async function comentar({ id, comentario }) {
  const response = await axiosInstance.post(`${PATH_COMENTAR}/${id}/comentar`, { comentario }, {});
  return response.data;
}
