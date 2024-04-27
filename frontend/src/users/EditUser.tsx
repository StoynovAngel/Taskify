import { useEffect, useState } from "react";
import { useNavigate, Link, useParams } from "react-router-dom";
import axios from "axios";
import InputForm from "../reusable/InputForm";

type User = {
  name: string;
  username: string;
  email: string;
};

const EditUser = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [user, setUser] = useState<User>({
    name: "",
    username: "",
    email: "",
  });

  useEffect(() => {
    const fetchUser = async () => {
      if (!id) return;
      try {
        const response = await axios.get(`http://localhost:8080/user/${id}`);
        setUser(response.data);
      } catch (error) {
        console.error("Couldn't fetch the user", error);
      }
    };
    fetchUser();
  }, [id]);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setUser((prev) => ({
      ...prev,
      [name]: value,
    }));
  };
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    axios
      .put<User>(`http://localhost:8080/user/${id}`, user)
      .then(() => {
        navigate("/");
      })
      .catch((error) => {
        console.error("Couldn't show the users", error);
      });
  };

  return (
    <div className="bg-gradient-to-r from-neutral-700 to-zinc-700">
      <Link to="/" className="absolute top-0 right-0 m-4">
        <button className="text-md text-black bg-white hover:bg-yellow-500 border-none font-bold rounded-full p-3">
          Home
        </button>
      </Link>

      <div className="flex items-center justify-center  min-h-screen">
        <form
          onSubmit={handleSubmit}
          className="bg-neutral-900 p-8 rounded-xl flex flex-col"
        >
          <h1 className="text-2xl text-white font-semibold">Edit User:</h1>
          <InputForm
            type="text"
            placeholder="Enter a name: "
            name="name"
            value={user.name}
            onChange={handleChange}
          />
          <InputForm
            type="text"
            placeholder="Enter a username: "
            name="username"
            value={user.username}
            onChange={handleChange}
          />
          <InputForm
            type="text"
            placeholder="Enter an email: "
            name="email"
            value={user.email}
            onChange={handleChange}
          />
          <button
            type="submit"
            className="bg-yellow-500 hover:bg-yellow-600 mt-4 text-white font-bold py-2 px-4 rounded"
          >
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default EditUser;
