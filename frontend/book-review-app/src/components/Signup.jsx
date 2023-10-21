import { useState } from "react";

const Signup = () => {

    const [name, setName] = useState("");
    const [firstName, setFirstName] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [repassword, setRepassword] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");

    const submit = (e) => {
        e.preventDefault();

        //TODO validate fields and call adduser api


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
                                <input type="text" onChange={(e) => setName(e.target.value)} placeholder="name"></input>
                            </div>
        
                            <div className="form-input">
                                <label>First Name</label>
                                <input type="text" onChange={(e) => setFirstName(e.target.value)} placeholder="first name"></input>
                            </div>
                        </div>
                        

                        <div className="form-input">
                            <label>Username</label>
                            <input type="text" onChange={(e) => setUsername(e.target.value)} placeholder="username"></input>
                        </div>

                        <div className="form-input">
                            <label>Email</label>
                            <input type="email" onChange={(e) => setEmail(e.target.value)} placeholder="email"></input>
                        </div>

                        <div className="form-input">
                            <label>Phone</label>
                            <input type="text" onChange={(e) => setPhoneNumber(e.target.value)} placeholder="+32470060000"></input>
                        </div>

                        <div className="form-input">
                            <label>Password</label>
                            <input type="password" onChange={(e) => setPassword(e.target.value)} placeholder="password"></input>
                        </div>

                        <div className="form-input">
                            <label>Repeat password</label>
                            <input type="password" onChange={(e) => setRepassword(e.target.value)} placeholder="password"></input>
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