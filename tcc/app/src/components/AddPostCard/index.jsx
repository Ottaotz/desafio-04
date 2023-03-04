import { postar } from '../../api/post/postar';
import defaultPost from '../../assets/images/defaultPost.jpg';
import { useFormData } from '../../hooks/useFormData';
import { toastMensagem, TOAST_TYPES } from '../../toast/toast';
import { Button } from '../Button';
import { TextInput } from '../TextInput';
import './style.css';

export function AddPostCard({ image, text, likes, username, handleClick, handleClickComments, id, index }) {
  const { formInputs, setData } = useFormData();

  function handleChange(event) {
    const { name, value } = event.target;
    setData(name, value);
  }

  async function onSubmit(event) {
    event.preventDefault();
    formInputs.addText;
    try {
      await postar({ imagem: formInputs.addImage, legenda: formInputs.addText, visualizacao: 'PUBLICO' });
    } catch (error) {
      error;
      toastMensagem(error.response.data.message, TOAST_TYPES.warning);
    }
  }

  return (
    <div className="post-card">
      <form className="add-post-card-form">
        <TextInput
          labelName="Legenda"
          name="addText"
          autoComplete="off"
          value={formInputs.addText}
          onChange={handleChange}
          css="input-post-card"
        />
        <TextInput
          labelName="Imagem"
          name="addImage"
          autoComplete="off"
          value={formInputs.addImage}
          onChange={handleChange}
          css="input-post-card"
        />
      </form>
      <figure>
        <div className="add-card-image">
          <img
            src={formInputs.addImage ? formInputs.addImage : defaultPost}
            alt="imagem do post"
            className="add-image"
          />
        </div>
      </figure>
      <div className="add-post-button-container">
        <form onSubmit={onSubmit}>
          <Button content="Postar" onClick={handleClick} />
        </form>
      </div>
    </div>
  );
}
