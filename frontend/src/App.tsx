import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import AddUser from "./users/AddUser";
import EditUser from "./users/EditUser";
function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/add-user" element={<AddUser />}></Route>
          <Route path="/edit-user/:id" element={<EditUser />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
