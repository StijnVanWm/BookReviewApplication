import { useState } from "react";
import "../styles/Signup.css"

//useFormik

const Signup = () => {

    const [user, setUser] = useState({
        lastName: "",
        firstName: "",
        email: "",
        username: "",
        password: "",
        repassword: "",
        phoneNumber: ""
    });

    const handleInput = e => {
        const { name, value } = e.target;
        setUser({...user, [name]: value});
    }

    const submit = (e) => {
        e.preventDefault();

        //TODO validate input and call addUser api
        console.log(user);

    }


    return (
        <>
            <div className="main-container">
                <div className="content-container">
    
                    <h2>Register</h2>
    
                    <form action="post" onSubmit={submit}>

                        <div className="form-orientation">
                            <div className="form-input">
                                <label>Name</label>
                                <input type="text" name="lastName" onChange={handleInput} placeholder="name"></input>
                            </div>
        
                            <div className="form-input">
                                <label>First Name</label>
                                <input type="text" name="firstName" onChange={handleInput} placeholder="first name"></input>
                            </div>
                        </div>
                        
                        <div className="form-input">
                            <label>Username</label>
                            <input type="text" name="username" onChange={handleInput} placeholder="username"></input>
                        </div>

                        <div className="form-input">
                            <label>Email</label>
                            <input type="email" name="email" onChange={handleInput} placeholder="email"></input>
                        </div>

                        <div className="form-input">
                            <label>Phone</label>
                            <input type="text" name="phoneNumber" onChange={handleInput} placeholder="+32470060000"></input>
                        </div>

                        <div className="form-input">
                            <label>Password</label>
                            <input type="password" name="password" onChange={handleInput} placeholder="password"></input>
                        </div>

                        <div className="form-input">
                            <label>Repeat password</label>
                            <input type="password" name="repassword" onChange={handleInput} placeholder="password"></input>
                        </div>

                        <button type="submit">Sign Up</button>
    
                    </form>
                </div>
    
                <div className="splash-container">
                    <img src="src\assets\book-wall.jpg" alt="Picture of a wall made of books" />
                </div>
            </div>
        </>
    )
}

export default Signup