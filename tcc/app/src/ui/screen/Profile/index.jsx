import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { usuario } from '../../../api/pegarUsuario';
import { Button, FriendsModal, Header, InviteModal, Usercard, UsersModal } from '../../../components';
import useGlobalUser from '../../../context/useGlobalUser';
import { toastMensagem, TOAST_TYPES } from '../../../toast/toast';
import './style.css';

export function Profile() {
  const [user, setUser] = useGlobalUser();
  const navigate = useNavigate();
  const [showFriendModal, setShowFriendModal] = useState(false);
  const [showInviteModal, setShowInviteModal] = useState(false);
  const [showUsersModal, setShowUsersModal] = useState(false);

  useEffect(() => {
    async function getUser() {
      if (!showFriendModal && !showInviteModal) {
        try {
          setUser(await usuario());
        } catch (error) {
          toastMensagem('Algo inesperado aconteceu', TOAST_TYPES.error);
        }
      }
    }

    getUser();
  }, [showFriendModal, showInviteModal]);

  async function handleSubmit() {
    navigate('/logout');
  }

  function handleClickFriendsModal() {
    setShowFriendModal(true);
  }

  function handleClickInvitesModal() {
    setShowInviteModal(true);
  }

  function handleClickUsersModal() {
    setShowUsersModal(true);
  }

  return (
    <div className="page profile-container">
      <Header />
      <div className="profle-buttons-group">
        <Button content="Amigos" onClick={handleClickFriendsModal} css="profile-button-friends" />
        <Button content="Solicitações" onClick={handleClickInvitesModal} css="profile-button-friends" />
        <Button content="Buscar Usuários" onClick={handleClickUsersModal} css="profile-button-friends" />
      </div>
      <Usercard
        username={user.apelido ? user.apelido : null}
        fullName={user.nome}
        photo={user.imagemPerfil}
        dateBirth={user.dataNascimento}
        onClick={handleSubmit}
        buttonContent="Logout"
      />
      <FriendsModal open={showFriendModal} handleModalClose={() => setShowFriendModal(false)} />
      <InviteModal open={showInviteModal} handleModalClose={() => setShowInviteModal(false)} />
      <UsersModal open={showUsersModal} handleModalClose={() => setShowUsersModal(false)} />
    </div>
  );
}
