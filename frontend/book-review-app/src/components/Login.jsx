import { useState } from "react"
import "../styles/Login.css"
import axios from "axios"

const Login = () => {

    const [user, setUser] = useState({
        username: '',
        password: ''
    })

    const handleInput = e => {
        const { name, value } = e.target;
        setUser({...user, [name]: value});
    }
 
    const submit = (e) => {
        e.preventDefault();

        //TODO validate username and password (from api)
        axios.post(`https://localhost:7050/api/gebruikers`, {
            gebruikerNaam: user.username,
            password: user.password
        })
        .then(res => console.log(res))
        .catch(err => console.log(err))
        

    }

  return (
    <>
        <div className="main-container">
            <div className="content-container">

                <h2>Login</h2>

                <form action="post" onSubmit={submit}>

                    <div className="form-input">
                        <label>Username</label>
                        <input type="text" name="username" onChange={handleInput} placeholder="username"></input>
                    </div>

                    <div className="form-input">
                        <label>Password</label>
                        <input type="password" name="password" onChange={handleInput} placeholder="password"></input>
                    </div>

                    <button type="submit">Login</button>

                </form>
            </div>

            <div className="splash-container">
                <img src="src\assets\book-wall.jpg" alt="Picture of a wall made of books" />
            </div>
        </div>
    </>
  )
}

export default Login