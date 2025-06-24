import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function UserList() {
  const navigate = useNavigate();
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const getUsers = async () => {
      try {
        const response = await axios.get(
          "https://gorest.co.in/public/v2/users"
        );
        // console.log(response.data)
        setUsers(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    getUsers();
  }, []);

  const handleOnDelete = async (id) => {
    try {
        await axios.delete(`https://gorest.co.in/public/v2/users/${id}`,{
            headers:{Authorization:'Bearer 1d79d4c82a4f31effff0eceace06ff7481b08b79c1abcddf463a282f3da8eef9'}
        });
      
      setUsers(users.filter((user) => user.id !== id));
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <button onClick={() => navigate("/")} className="btn btn-secondary mb-3">
        Go to Dashboard
      </button>
      <table className="table table-striped  ">
        <thead>
          <tr>
            <th scope="col">SNO</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Gender</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          {users.map((u, index) => (
            <tr key={index.id}>
              <th scope="row">{index + 1}</th>
              <td>{u.name}</td>
              <td>{u.email}</td>
              <td>{u.gender}</td>
              <td>{u.status}</td>
              <td className="">
                <button
                  type="button"
                  className="btn btn-danger me-2"
                  onClick={() => handleOnDelete(u.id)}
                >
                  Delete
                </button>
                <button type="button" className="btn btn-primary" onClick={() => navigate(`/edit-user/${u.id}`)} >
                  Edit
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}
export default UserList;
