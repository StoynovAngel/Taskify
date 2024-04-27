import { useEffect, useState } from "react";
import NavBar from "../layout/NavBar";
import TableUser from "../home/TableUser";
import axios from "axios";

const Home = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const fetchUsers = async () => {
      axios
        .get("http://localhost:8080/user")
        .then(({ data }) => {
          setUsers(data);
        })
        .catch((error) => {
          console.error("Couldn't show the users", error);
        });
    };
    fetchUsers();
  }, []);

  return (
    <>
      <NavBar />
      <TableUser users={users} />
    </>
  );
};

export default Home;
