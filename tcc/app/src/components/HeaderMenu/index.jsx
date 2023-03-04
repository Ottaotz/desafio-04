import { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { usuario } from '../../api/pegarUsuario';
import useGlobalUser from '../../context/useGlobalUser';
import './style.css';

export function HeaderMenu() {
  const [user, setUser] = useGlobalUser();
  const navigate = useNavigate();

  useEffect(() => {
    async function getUsuario() {
      try {
        const user = await usuario();
        setUser(user);
      } catch (error) {}
    }

    getUsuario();
  }, []);

  return (
    <nav className="menu">
      <ul>
        <li className="menu-li profile-li">
          <figure className="figure-menu">
            <div className="image-container">
              <img src={user.imagemPerfil} alt="Foto de perfil" />
            </div>
          </figure>
          <Link to={'/profile'} className="see-details">
            See details
          </Link>
        </li>
      </ul>
    </nav>
  );
}
