import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../../../api/auth/login';
import { Button, TextInput } from '../../../components/';
import useGlobalUser from '../../../context/useGlobalUser';
import { useFormData } from '../../../hooks/useFormData';
import { toastMensagem, TOAST_TYPES } from '../../../toast/toast';
import './style.css';

export function Login() {
  const navigate = useNavigate();
  const { formInputs, setData } = useFormData();
  const [user, setUser] = useGlobalUser();

  function handleChange(event) {
    const { name, value } = event.target;
    setData(name, value);
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      setLogin(await login({ username: formInputs.username, password: formInputs.password }));
    } catch (error) {
      toastMensagem('Login incorreto.', TOAST_TYPES.warning);
    }
  }

  function setLogin(loginUser) {
    setUser(loginUser);
  }

  function handleClickCreateAccount() {
    navigate('/create');
  }

  useEffect(() => {
    if (user) {
      navigate('/home');
    }
  }, [user]);

  return (
    <section className="page login-page">
      <Button onClick={handleClickCreateAccount} content="Cadastrar" css="create-button" />
      <form onSubmit={handleSubmit} className="login-form card">
        <h1 className="form-title">Entrar</h1>
        <TextInput
          labelName="Usuário"
          name="username"
          autoComplete="off"
          value={formInputs.username}
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
        <Button content="Login" />
      </form>
    </section>
  );
}
