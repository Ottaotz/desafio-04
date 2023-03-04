import { axiosInstance } from '../../_base';
const PATH_CADASTRO = '/usuarios';

export async function cadastrar({ name, email, password, username, dateBirth, image }) {
  username, password;
  const response = await axiosInstance.post(
    PATH_CADASTRO,
    {
      nome: name,
      email,
      senha: password,
      apelido: username,
      dataNascimento: dateBirth,
      imagemPerfil: image,
    },
    {}
  );
  return response.data;
}
