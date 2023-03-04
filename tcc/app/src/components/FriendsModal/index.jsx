import Box from '@mui/material/Box';
import Fade from '@mui/material/Fade';
import Modal from '@mui/material/Modal';
import Typography from '@mui/material/Typography';
import * as React from 'react';
import { removerAmigo } from '../../api/amizade/remover';
import '../../App.css';
import useGlobalUser from '../../context/useGlobalUser';
import { toastMensagem, TOAST_TYPES } from '../../toast/toast';
import { Button } from '../Button';
import { Loading } from '../Loading';

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

export function FriendsModal({ id, open, handleModalClose }) {
  const [user] = useGlobalUser();

  function showFriends() {
    if (user === undefined) return <Loading />;

    return user.amigos.map((amigo) => (
      <div className="friend-card">
        <div className="friend-card-content">
          <div>
            <img src={amigo.imagemPerfil} className="friend-card-image" />
          </div>
          <div className="friend-card-content-text">
            <h5>{amigo.nome}</h5>
            <p>{amigo.apelido}</p>
          </div>
        </div>
        <form>
          <Button content="Desfazer Amizade" value={amigo.id} onClick={handleClickRemove} />
        </form>
      </div>
    ));
  }

  async function handleClickRemove(event) {
    event.preventDefault();
    const { name, value } = event.target;

    try {
      await removerAmigo({ id: value });
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
              Amigos
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
                {showFriends()}
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
