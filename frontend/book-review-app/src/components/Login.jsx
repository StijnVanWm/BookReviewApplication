import { useState } from "react"
import "../styles/Login.css"

const Login = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const submit = (e) => {
        e.preventDefault();

        //TODO validate username and password for login call api (not implemented yet)
        console.log(username);
        console.log(password);

    }

  return (
    <>
        <div className="main-container">
            <div className="content-container">

                <h2>Login</h2>

                <form action="post" onSubmit={submit}>

                    <div className="form-input">
                        <label>Username</label>
                        <input type="text" onChange={(e) => setUsername(e.target.value)} placeholder="username"></input>
                    </div>

                    <div className="form-input">
                        <label>Password</label>
                        <input type="password" onChange={(e) => setPassword(e.target.value)} placeholder="password"></input>
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