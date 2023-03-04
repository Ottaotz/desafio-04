import './style.css';

export function Button({ onClick, content, css, value }) {
  return (
    <button value={value} onClick={onClick} className={css}>
      {content}
    </button>
  );
}
