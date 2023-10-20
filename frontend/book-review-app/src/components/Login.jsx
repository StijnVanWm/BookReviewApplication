import "../styles/Login.css"
const Login = () => {
  return (
    <>
        <div className="content-container">

            <h2>Login</h2>

            <form action="post">

                <div className="form-input">
                    <label>Username</label>
                    <input type="text"></input>
                </div>

                <div className="form-input">
                    <label>Password</label>
                    <input type="password"></input>
                </div>

                <button type="submit">Login</button>

            </form>
        </div>
    
    </>
  )
}

export default Login