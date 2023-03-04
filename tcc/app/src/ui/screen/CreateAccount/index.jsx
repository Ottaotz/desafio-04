import { useNavigate } from 'react-router-dom';
import { cadastrar } from '../../../api/auth/cadastrar';
import { Button, TextInput } from '../../../components';
import { useFormData } from '../../../hooks/useFormData';
import { toastMensagem, TOAST_TYPES } from '../../../toast/toast';
import './style.css';

export function CreateAccount() {
  const navigate = useNavigate();
  const { formInputs, setData } = useFormData();

  function handleChange(event) {
    const { name, value } = event.target;
    setData(name, value);
  }

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      await cadastrar({
        name: formInputs.name,
        email: formInputs.email,
        password: formInputs.password,
        username: formInputs.username,
        dateBirth: formInputs.dateBirth,
        image: formInputs.image,
      });

      toastMensagem('Cadastado com sucesso', TOAST_TYPES.success);
    } catch (error) {}
  }

  function handleClickLogin() {
    navigate('/');
  }

  return (
    <section className="page create-page">
      <Button onClick={handleClickLogin} content="Entrar" css="create-button" />
      <form onSubmit={handleSubmit} className="create-form card">
        <h1 className="form-title">Cadastrar</h1>
        <TextInput
          labelName="Nome Completo"
          name="name"
          autoComplete="off"
          value={formInputs.name}
          onChange={handleChange}
        />
        <TextInput
          labelName="Email"
          name="email"
          autoComplete="off"
          value={formInputs.email}
          onChange={handleChange}
        />
        <TextInput
          labelName="Senha"
          name="password"
          autoComplete="off"
          value={formInputs.password}
          onChange={handleChange}
          type="password"
        />
        <TextInput
          labelName="Apelido"
          name="username"
          autoComplete="off"
          value={formInputs.username}
          onChange={handleChange}
        />
        <TextInput
          labelName="Data de Nascimento"
          name="dateBirth"
          autoComplete="off"
          value={formInputs.dateBirth}
          onChange={handleChange}
          type="date"
        />
        <TextInput
          labelName="Imagem de Perfil"
          name="image"
          autoComplete="off"
          value={formInputs.image}
          onChange={handleChange}
        />
        <Button content="Cadastrar" />
      </form>
    </section>
  );
}
