import axios from "axios";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

interface User {
  id: number;
  name: string;
  username: string;
  email: string;
}

interface Props {
  users: User[];
}

const TableUser = ({ users }: Props) => {
  const [localUsers, setLocalUsers] = useState<User[]>(users);
  const navigate = useNavigate();

  const deleteProduct = async (userId: number) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      try {
        await axios.delete(`http://localhost:8080/user/${userId}`);
        setLocalUsers(localUsers.filter((user) => user.id !== userId));
        alert("User deleted successfully");
        navigate("/");
      } catch (error) {
        alert(`Couldn't delete user with id: ${userId}. Error: ${error}`);
      }
    }
  };
  useEffect(() => {
    setLocalUsers(users);
  }, [users]);

  return (
    <>
      <div className="overflow-x-auto">
        <table className="table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Username</th>
              <th>Email</th>
              <th>Options</th>
            </tr>
          </thead>
          <tbody>
            {localUsers.map((user) => (
              <tr key={user.id}>
                <th>{user.id}</th>
                <td>{user.name}</td>
                <td>{user.username}</td>
                <td>{user.email}</td>
                <td className="flex gap-1">
                  <button
                    className="btn bg-red-700 text-white"
                    onClick={() => deleteProduct(user.id)}
                  >
                    Delete
                  </button>
                  <Link to={`/edit-user/${user.id}`}>
                    <button className="btn bg-blue-700 text-white">Edit</button>
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};

export default TableUser;
