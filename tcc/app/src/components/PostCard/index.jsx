import './style.css';

export function PostCard({ image, text, likes, username, handleClickLikes, handleClickComments, id, index }) {
  return (
    <div className="post-card">
      <p>{username}</p>
      <figure className="image">
        <div className="card-image">
          <img src={image} alt="imagem do post" className="image" />
        </div>
      </figure>
      <div className="post-button-container">
        <button className="post-button" onClick={() => handleClickLikes(id)}>
          {likes} Curtidas
        </button>
        <button className="post-button" onClick={() => handleClickComments(index)}>
          Comentar
        </button>
      </div>
      <p>{text}</p>
    </div>
  );
}
