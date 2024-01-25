import { NavLink, useLocation, useNavigate } from "react-router-dom"
import SearchBar from "./SearchBar"
import NavButton from "./NavButton"
import useVerifyUser from "../hooks/useVerifyUser";
import Cookies from "js-cookie"
import Logo from "../assets/Logo.png"

const NavBar = () => {

  const location = useLocation();
  const { isVerified, setIsVerified } = useVerifyUser();
  const navigate = useNavigate();

  const handleLogout = (e) => {
    e.preventDefault();
    Cookies.remove("bookrev");
    setIsVerified(false);
    navigate('/');
  }

  return (
    
    <nav className="shadow-lg w-full h-20">
        <div className="max-w-full h-full mx-auto px-32 pt-3 flex items-center justify-between">
            <div className="flex-1">
              <div className="w-full lg:w-2/3 xl:w-1/2">
                <img className="w-full h-full object-cover hover:cursor-pointer" onClick={() => navigate('/')} src={Logo} alt="logo" />
              </div>
            </div>
            <div className="flex-1 relative">
              <SearchBar />
            </div>
            <div className="flex-1">
            {
              isVerified  
              ? <div className="w-full h-full flex justify-end items-center gap-x-4">
                  <NavLink className="w-full flex items-center justify-end">
                    <svg className="h-7 w-7 p-1 text-sky-950 hover:text-gray-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="currentColor" d="M313.6 304c-28.7 0-42.5 16-89.6 16-47.1 0-60.8-16-89.6-16C60.2 304 0 364.2 0 438.4V464c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48v-25.6c0-74.2-60.2-134.4-134.4-134.4zM400 464H48v-25.6c0-47.6 38.8-86.4 86.4-86.4 14.6 0 38.3 16 89.6 16 51.7 0 74.9-16 89.6-16 47.6 0 86.4 38.8 86.4 86.4V464zM224 288c79.5 0 144-64.5 144-144S303.5 0 224 0 80 64.5 80 144s64.5 144 144 144zm0-240c52.9 0 96 43.1 96 96s-43.1 96-96 96-96-43.1-96-96 43.1-96 96-96z"></path></svg>
                    <NavButton onClick={handleLogout}>Logout</NavButton>
                  </NavLink>
                </div>
              : <div className="w-full h-full flex justify-end items-center gap-x-4">
                  <NavLink to='/login' replace state={{from: location.pathname}} className="text-lg font-semibold text-sky-950 tracking-wide transition ease-linear hover:text-sky-600">Sign in</NavLink>
                  <NavLink to='/register' className="py-1 px-2 bg-sky-950 rounded-lg transition ease-linear text-white text-lg font-semibold hover:bg-white hover:text-sky-950">Sign up</NavLink>
                </div>
            }
            </div>
        </div>
    </nav>
    
  )
}

export default NavBar