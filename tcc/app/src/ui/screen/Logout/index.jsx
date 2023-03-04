import { useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import { sair } from '../../../api/auth/sair';
import useGlobalUser from '../../../context/useGlobalUser';

export function Logout() {
  const [user, setUser] = useGlobalUser();

  useEffect(() => {
    if (!user) {
      goToLogin();
    }
  }, [user]);

  function verifyUser() {
    return localStorage.getItem('user') === null;
  }

  function goToLogin() {
    return <Navigate to="/" />;
  }

  async function logout() {
    try {
      localStorage.clear();
      await sair();
      setUser(null);
    } catch (error) {
      return <Navigate to="/home" />;
    }
  }

  function goTo() {
    return verifyUser() ? goToLogin() : logout();
  }

  return <>{goTo()}</>;
}
