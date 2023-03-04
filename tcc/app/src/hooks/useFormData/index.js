import { useState } from 'react';

export function useFormData() {
  const [formInputs, setFormInputs] = useState({
    username: '',
    password: '',
    buscar: '',
  });

  function setData(name, value) {
    setFormInputs((previous) => ({ ...previous, [name]: value }));
  }

  return { formInputs, setData };
}
