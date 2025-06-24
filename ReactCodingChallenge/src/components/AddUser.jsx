import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function AddUser() {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [gender, setGender] = useState("");
  const [status, setStatus] = useState("");
  const [msg, setMsg] = useState("");

  const addUser = async () => {
    try {
      await axios.post(
        "https://gorest.co.in/public/v2/users",
        {
          name: name,
          email: email,
          gender: gender,
          status: status,
        },
        {
          headers: {
            Authorization:
              "Bearer 1d79d4c82a4f31effff0eceace06ff7481b08b79c1abcddf463a282f3da8eef9",
            "Content-Type": "application/json",
          },
        }
      );
      setMsg("User Added successfully");

      setName("");
      setEmail("");
      setGender("");
      setStatus("");
    } catch (error) {
      console.log(error);
      setMsg("failed to add user");
    }
  };

  return (
    <>
      <button onClick={() => navigate("/")}>Go To DashBoard</button>
      <div className="container">
        <div className="row">
          <div className="col-md-6">
            <div className="card mt-5">
              <div className="card-header">Enter The Details</div>
              <div className="card-body">
                {msg !== "" ? (
                  <div className="mb-4">
                    <div className="alert alert-primary">{msg}</div>
                  </div>
                ) : (
                  ""
                )}

                <div className="mb-4">
                  <label>Enter Your Name</label>
                  <input
                    type="text"
                    
                    onChange={(e) => setName(e.target.value)}
                  />
                </div>

                <div className="mb-4">
                  <label>Enter Your Email</label>
                  <input
                    type="text"
                  
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>

                <div className="mb-4">
                  <select
                    className="form-control"
                    onChange={(e) => setGender(e.target.value)}
                  >
                    <option value="">Select Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                  </select>
                </div>

                <div className="mb-4">
                  <select
                    className="form-control"
                    onChange={(e) => setStatus(e.target.value)}
                  >
                    <option value="">Select Status</option>
                    <option value="active">Active</option>
                    <option value="inactive">Inactive</option>
                  </select>
                </div>
              </div>

              <div className="card-footer">
                <button className="btn btn-primary" onClick={addUser}>
                  Add To Post
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AddUser;