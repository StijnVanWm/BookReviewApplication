import { Outlet } from "react-router-dom"
import NavBar from "../components/NavBar"
import VerifyUserContextProvider from "../context/VerifyUserContext"


const RootLayout = () => {
  return (
    <div>
        <VerifyUserContextProvider>
          <NavBar />
        </VerifyUserContextProvider>
        <Outlet />
    </div>
  )
}

export default RootLayout