import { useState } from "react";
import axios from "axios";
import AuthButton from "../components/AuthButton";
import Title from "../components/Title";

const LoginPage = () => {

    const [user, setUser] = useState({
        username: '',
        password: ''
    });


    const submit = (e) => {
        e.preventDefault();
        authenticateUser();
    }

    const authenticateUser = async() => {

        try {

            const response = await axios.post("http://localhost:8080/api/auth/login", {...user})
            
            const { data } = response;

            console.log(data);
        }
        catch(err) {
            console.error(err);
        }
    }


  return (
     <div className="w-full h-screen grid grid-cols-1 lg:grid-cols-2 items-center">

            <div className="flex flex-col items-start w-1/2 m-auto gap-y-6">

                <Title>LOGIN</Title>

                <form action="post" onSubmit={submit} className="pr-8 pt-6 pb-8 mb-4 w-80">

                    <div className="mb-4">
                        <label className="block text-sky-950 text-sm font-bold mb-2"
                        >Username</label>
                        <input className="shadow appearance-none border-none border-b-2  rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none" 
                        type="text" onChange={e => setUser({...user, username: e.target.value})} placeholder="username"></input>
                    </div>

                    <div className="mb-6">
                        <label className="block text-sky-950 text-sm font-bold mb-2"
                        >Password</label>
                        <input className="shadow appearance-none border-none border-b-2  rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none" 
                        type="password" onChange={e => setUser({...user, password: e.target.value})} placeholder="**********"></input>
                    </div>

                    <AuthButton onClick={authenticateUser}>Login</AuthButton>
                    <p className="text-sky-950 inline ml-10 hover:cursor-pointer font-light">Sign up here</p>

                </form>
            </div>

            <div className="w-full h-full hidden lg:block rounded-bl-full overflow-hidden">
                <img className="obj-cover w-full h-full" src="src\assets\book-wall.jpg" alt="Picture of a wall made of books" />
            </div>
        </div>
  )
}

export default LoginPage