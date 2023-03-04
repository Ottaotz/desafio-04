import { HeaderMenu } from '../HeaderMenu';
import './style.css';

export function Header() {
  return (
    <div className="header-container">
      <h1>
        <a href="/home">NekoCat</a>
      </h1>
      <HeaderMenu />
    </div>
  );
}
