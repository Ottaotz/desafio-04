import Box from '@mui/material/Box';
import Fade from '@mui/material/Fade';
import Modal from '@mui/material/Modal';
import Typography from '@mui/material/Typography';
import * as React from 'react';
import { comentar } from '../../api/comentar';
import useGlobalUser from '../../context/useGlobalUser';
import { toastMensagem } from '../../toast/toast';
import { Loading } from '../Loading';
import { TextInput } from '../TextInput';
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

export function CommentModal({ id, open, handleModalClose }) {
  const [, , , getGlobalComments] = useGlobalUser();
  const [comment, setComment] = React.useState('');

  getGlobalComments();

  function showComments() {
    if (getGlobalComments() === undefined) return <Loading />;

    return getGlobalComments().postComments.map((comment) => (
      <div className="comment-card">
        <h5>{comment.usuarioNome}</h5>
        <p>{comment.comentario}</p>
      </div>
    ));
  }

  function handleChange(event) {
    const { name, value } = event.target;
    value;
    setComment(value);
  }

  async function onSubmit(event) {
    try {
      await comentar({ id: getGlobalComments().postId, comentario: comment });
    } catch (error) {
      error;
      toastMensagem('algo');
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
              Comentários
            </Typography>

            <Box sx={{ overflowY: 'scroll' }}>
              <form className="form-modal" onSubmit={onSubmit}>
                <TextInput
                  labelName="Comentar"
                  name="comment"
                  autoComplete="off"
                  value={comment}
                  onChange={handleChange}
                />
                <button>Publicar</button>
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
                {showComments()}
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
