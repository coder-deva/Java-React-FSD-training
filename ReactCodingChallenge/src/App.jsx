import { BrowserRouter, Route, Routes } from "react-router-dom"
import Dashboard from "./Dashboard"
import UserList from "./components/UserList"
import AddUser from "./components/AddUser"
import UpdateUser from "./components/UpdateUser"

function App(){
  return(

  <>
  <BrowserRouter>
  <Routes>
    <Route path="/" element={<Dashboard/>}/>
    <Route path="/users" element={<UserList/>}/>
    <Route path="/add-user" element={<AddUser/>}/>
    <Route path="/edit-user/:id" element={<UpdateUser />} />
    


  </Routes>



  
  </BrowserRouter>
  </>
  )
}

export default App