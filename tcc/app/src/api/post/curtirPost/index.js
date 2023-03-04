import { axiosInstance } from '../../_base';
const PATH_CURTIR = '/posts';

export async function curtirPosts({ id }) {
  const response = await axiosInstance.put(`${PATH_CURTIR}/${id}/curtir`, {}, {});
  return response.data;
}
