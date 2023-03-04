import { axiosInstance } from '../../_base';
const PATH_DESCURTIR = '/posts';

export async function descurtirPosts({ id }) {
  const response = await axiosInstance.put(`${PATH_DESCURTIR}/${id}/descurtir`, {}, {});
  return response.data;
}
