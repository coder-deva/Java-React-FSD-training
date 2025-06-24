import { BrowserRouter, Route, Routes } from "react-router-dom"
import Dashboard from "./Dashboard"
import UserList from "./components/UserList"
import AddUser from "./components/AddUser"

function App(){
  return(

  <>
  <BrowserRouter>
  <Routes>
    <Route path="/" element={<Dashboard/>}/>
    <Route path="/users" element={<UserList/>}/>
    <Route path="/add-user" element={<AddUser/>}/>
    


  </Routes>



  
  </BrowserRouter>
  </>
  )
}

export default App