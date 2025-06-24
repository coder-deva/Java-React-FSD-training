import { useNavigate } from "react-router-dom";

function Dashboard() {

    const navigate =useNavigate();
  return (
    <>
      <div className="container">
        <div className="row">
          <div className="card-body d-flex justify-content-center gap-4">
            <button className="btn btn-primary" onClick={()=>navigate('/users')}>User List</button>

            <button className="btn btn-primary" onClick={()=>navigate('/add-user')}>Add User</button>
          </div>
        </div>
      </div>
    </>
  );
}

export default Dashboard;
