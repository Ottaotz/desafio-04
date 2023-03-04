import Box from '@mui/material/Box';
import Fade from '@mui/material/Fade';
import Modal from '@mui/material/Modal';
import Typography from '@mui/material/Typography';
import * as React from 'react';
import { adicionarAmigo } from '../../api/amizade/adicionar';
import { rejeitarAmigo } from '../../api/amizade/rejeitar';
import useGlobalUser from '../../context/useGlobalUser';
import { toastMensagem, TOAST_TYPES } from '../../toast/toast';
import { Button } from '../Button';
import { Loading } from '../Loading';
import './style.css';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
};

export function InviteModal({ id, open, handleModalClose }) {
  const [user] = useGlobalUser();

  function showInvites() {
    if (user === undefined) return <Loading />;

    return user.solicitacoes.map((amigo) => (
      <div className="invite-card">
        <div className="invite-card-content">
          <div>
            <img src={amigo.imagemPerfil} className="invite-card-image" />
          </div>
          <div className="invite-card-content-text">
            <h5>{amigo.nome}</h5>
            <p>{amigo.apelido}</p>
          </div>
        </div>
        <form className="invite-modal-form">
          <Button content="Aceitar" value={amigo.id} onClick={handleClickAccept} css="accept-button" />
          <Button content="Rejeitar" value={amigo.id} onClick={handleClickReject} css="reject-button" />
        </form>
      </div>
    ));
  }

  async function handleClickAccept(event) {
    event.preventDefault();
    const { name, value } = event.target;
    try {
      await adicionarAmigo({ id: value });
    } catch (error) {
      toastMensagem(error.response.data.message, TOAST_TYPES.error);
    }
  }

  async function handleClickReject(event) {
    event.preventDefault();
    const { name, value } = event.target;
    try {
      await rejeitarAmigo({ id: value });
    } catch (error) {
      toastMensagem(error.response.data.message, TOAST_TYPES.error);
    }
  }

  return (
    <div>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleModalClose}
        closeAfterTransition
      >
        <Fade in={open} className="modal">
          <Box sx={style} padding="0 60px">
            <Typography
              textAlign="center"
              marginBottom="2em"
              id="transition-modal-title"
              variant="h6"
              component="h2"
            >
              Solicitações
            </Typography>

            <Box sx={{ overflowY: 'scroll', width: '100%' }}>
              <Box
                marginTop={'20px'}
                justifyContent={'center'}
                container
                sx={{
                  display: 'flex',
                  flexDirection: 'column',
                }}
              >
                {showInvites()}
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
