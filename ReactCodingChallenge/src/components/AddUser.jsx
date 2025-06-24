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

  const addPost = async () => {
    try {
      await axios.post("https://gorest.co.in/public/v2/users", {
        Name: name,
        Email: email,
        Gender: gender,
        Status: status,
      },
    {
        headers:{Authorization:'Bearer 1d79d4c82a4f31effff0eceace06ff7481b08b79c1abcddf463a282f3da8eef9'}
    });
      setMsg("posted successfully");
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <button onClick={() => navigate("/")}>Go To DashBoard</button>
      <div className="container">
        <div className="row">
          <div className="col-md-6">
            <div className="card mt-5  ">
              <div className="card-header">enter The Details</div>
              <div className="card-body">
                {msg != "" ? (
                  <div className="mb-4">
                    <div className="alert alert-primary">{msg}</div>
                  </div>
                ) : (
                  ""
                )}
                <div className="mb-4">
                  <label> Enter You Name</label>
                  <input
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                  />
                </div>

                <div className="mb-4">
                  <label> Enter You Email</label>
                  <input type="text" onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div className="mb-4">
                  <label> Enter You Gender</label>
                  <input type="text" onChange={(e) => setGender(e.target.value)} />
                </div>
                <div className="mb-4">
                  <label> Enter You Status</label>
                  <input type="text" onChange={(e) => setStatus(e.target.value)} />
                </div>
              </div>
              <div className="card-footer">
                <button className="btn btn-primary" onClick={() => addPost()}>
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
