import React from "react";

interface Props {
  type: string;
  placeholder: string;
  name: string;
  value: string;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const InputForm = ({ type, placeholder, name, value, onChange }: Props) => {
  return (
    <label className="input input-bordered flex items-center w-full max-w-xs my-1 rounded-xl bg-neutral-800 border border-black hover:bg-neutral-600">
      <input
        className="text-gray-200 text-sm "
        type={type}
        placeholder={placeholder}
        name={name}
        value={value}
        onChange={onChange}
      />
    </label>
  );
};

export default InputForm;
