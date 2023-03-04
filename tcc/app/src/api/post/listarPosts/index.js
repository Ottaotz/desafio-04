import { axiosInstance } from '../../_base';
const PATH_LISTAR = '/posts';

export async function listarPosts({ text, page }) {
  const response = await axiosInstance.get(
    `${PATH_LISTAR}?text=${text}&page=${page}&size=8&sort=dataPostagem,desc`,
    { text }
  );
  return response.data;
}
