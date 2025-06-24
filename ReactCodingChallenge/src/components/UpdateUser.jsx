import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function UpdateUser() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [gender, setGender] = useState("");
  const [status, setStatus] = useState("");
  const [msg, setMsg] = useState("");

  useEffect(() => {
    const getUser = async () => {
      try {
        const res = await axios.get(
          `https://gorest.co.in/public/v2/users/${id}`
        );
        setName(res.data.name);
        setEmail(res.data.email);
        setGender(res.data.gender);
        setStatus(res.data.status);
      } catch (error) {
        console.log(error);
      }
    };
    getUser();
  }, [id]);

  const updateUser = async () => {
    try {
      const response = await axios.put(
        `https://gorest.co.in/public/v2/users/${id}`,
        {
          'name':name,
          'email':email,
          'gender':gender,
          'status':status,
        },
        {
          headers: {
            Authorization:
              "Bearer 8d8801ee9d1bf1774331a5c70bfa485cab227ffcea43ea683c437b9b1fc3fea5",
          },
        }
      );

      console.log(response);

      setMsg("User updated successfully");
    } catch (err) {
      console.log(err);
      setMsg("Error in updating");
    }
  };

  return (
    <div className="container mt-4">
      <button
        onClick={() => navigate("/users")}
        className="btn btn-secondary mb-3"
      >
        Back to User List
      </button>
      <h2>Update User</h2>
      {msg !== "" ? (
        <div className="mb-4">
          <div className="alert alert-primary">{msg}</div>
        </div>
      ) : (
        ""
      )}

      <div className="mb-3">
        <label>Name</label>
        <input
          name="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          className="form-control"
        />
      </div>

      <div className="mb-3">
        <label>Email</label>
        <input
          name="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="form-control"
        />
      </div>

      <div className="mb-3">
        <label>Gender</label>
        <select
          name="gender"
          value={gender}
          onChange={(e) => setGender(e.target.value)}
          className="form-control"
        >
          <option value="">Select Gender</option>
          <option value="male">Male</option>
          <option value="female">Female</option>
        </select>
      </div>

      <div className="mb-3">
        <label>Status</label>
        <select
          name="status"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
          className="form-control"
        >
          <option value="">Select Status</option>
          <option value="active">Active</option>
          <option value="inactive">Inactive</option>
        </select>
      </div>

      <button className="btn btn-success" onClick={updateUser}>
        Update User
      </button>
    </div>
  );
}

export default UpdateUser;
