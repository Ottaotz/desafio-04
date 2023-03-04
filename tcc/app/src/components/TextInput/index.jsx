import './style.css';

export function TextInput({ labelName, name, autoComplete, value, onChange, type, css }) {
  return (
    <>
      <label className="textInput-label">{labelName}</label>
      <input
        placeholder={labelName}
        name={name}
        autoComplete={autoComplete}
        value={value}
        onChange={onChange}
        type={type}
        required
        className={`text-input ${css}`}
      />
    </>
  );
}
