import Box from '@mui/material/Box';
import Fade from '@mui/material/Fade';
import Modal from '@mui/material/Modal';
import Typography from '@mui/material/Typography';
import * as React from 'react';
import { solicitarAmigo } from '../../api/amizade/solicitar';
import { listarUsuarios } from '../../api/listarUsuarios';
import '../../App.css';
import useGlobalUser from '../../context/useGlobalUser';
import { useFormData } from '../../hooks/useFormData';
import { toastMensagem, TOAST_TYPES } from '../../toast/toast';
import { Button } from '../Button';
import { Loading } from '../Loading';
import { TextInput } from '../TextInput';

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

export function UsersModal({ id, open, handleModalClose }) {
  const [user] = useGlobalUser();
  const { formInputs, setData } = useFormData();
  const [users, setUsers] = React.useState();

  React.useEffect(() => {
    async function getData() {
      try {
        const users = await listarUsuarios({ text: formInputs.buscar });
        setUsers(users);
      } catch (error) {
        toastMensagem(error.response.data.message, TOAST_TYPES.error);
      }
    }

    getData();
  }, [formInputs.buscar]);

  function showUsers() {
    if (users === undefined) return <Loading />;

    return users.content.map((amigo) => (
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
          <Button content="Solicitar Amizade" value={amigo.id} onClick={handleClickSolicitar} />
        </form>
      </div>
    ));
  }

  async function handleClickSolicitar(event) {
    event.preventDefault();
    const { name, value } = event.target;
    try {
      await solicitarAmigo({ id: value });
    } catch (error) {
      toastMensagem(error.response.data.message, TOAST_TYPES.error);
    }
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setData(name, value);
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
              Usuários
            </Typography>

            <Box sx={{ overflowY: 'scroll', width: '100%' }}>
              <form className="form-modal">
                <TextInput
                  labelName="Comentar"
                  name="buscar"
                  autoComplete="off"
                  value={formInputs.buscar}
                  onChange={handleChange}
                />
              </form>
              <Box
                marginTop={'20px'}
                justifyContent={'center'}
                container
                sx={{
                  display: 'flex',
                  flexDirection: 'column',
                }}
              >
                {showUsers()}
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
