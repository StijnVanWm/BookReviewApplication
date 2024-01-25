import { createContext, useEffect, useState } from "react"
import { verifyUser } from "../api";
import Cookies from 'js-cookie';



export const verifyUserContext = createContext();

const VerifyUserContextProvider = (props) => {

    const token = Cookies.get('bookrev'); 
    
    const [isVerified, setIsVerified] = useState(false);
    const [userId, setUserId] = useState();
    const [errors, setErrors] = useState(null);

    useEffect(() => {
        (async () => {
            try {
                const response = await verifyUser(token);
                const { data: { verified, userId }} = response;
    
                setIsVerified(verified);
                setUserId(userId);
            } catch(err) {
                setErrors(err);
            }
        })();
    },[])

    return <verifyUserContext.Provider value={{isVerified, setIsVerified, errors, userId}} {...props}></verifyUserContext.Provider>
}

export default VerifyUserContextProvider