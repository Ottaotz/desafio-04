import { Button } from '../Button';
import './style.css';

export function Usercard({ username, fullName, photo, onClick, buttonContent, dateBirth }) {
  return (
    <div className="card">
      <h1 className="fullname">{fullName}</h1>
      <h3 className="username">{username}</h3>
      <figure>
        <div className="card-image user-profile-image">
          <img src={photo} alt={fullName} />
        </div>
      </figure>
      <p>{`Data de Nascimento: ${dateBirth}`}</p>
      <Button onClick={onClick} content={buttonContent} className="card-Button" />
    </div>
  );
}
