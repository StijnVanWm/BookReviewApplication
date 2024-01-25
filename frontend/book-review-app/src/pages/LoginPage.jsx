import AuthButton from "../components/AuthButton";
import Title from "../components/Title";
import { useFormik } from "formik";
import { loginSchema } from "../schemas";
import { NavLink, useLocation, useNavigate } from "react-router-dom";
import { sendDataToApi } from "../api";
import { Toaster  } from "react-hot-toast";
import useToast from "../hooks/useToast";
import Cookies from 'js-cookie';

const LoginPage = () => {
    
    const navigate = useNavigate();
    const location = useLocation();
    const { notifySuccess, notifyFailure } = useToast();

    const onSubmit = async () => {
        
        try {
            const response = await sendDataToApi('/auth/login', values);
            const { data: { accessToken } } = response;
            
            //TODO SET SECURE
            Cookies.set('bookrev', accessToken)
            notifySuccess("Login successfull");

            setTimeout(() => {
                location.state?.from
                ? navigate(location.state.from)
                : navigate('/')
            }, 1000);

        } catch(err) {
            notifyFailure("Something went wrong logging in");
        }
        
    }

    const { values, errors, touched, isSubmitting, handleBlur, handleChange, handleSubmit } = useFormik({
        initialValues: {
            username: '',
            password: ''
        },
        validationSchema: loginSchema,
        onSubmit: onSubmit
    })




  return (
     <div className="w-full h-screen grid grid-cols-1 lg:grid-cols-2 items-center">

        <Toaster />

            <div className="flex flex-col items-start w-1/2 m-auto gap-y-6">

                <Title>LOGIN</Title>

                <form onSubmit={handleSubmit} className="pr-8 pt-6 pb-8 mb-4 w-80">

                    <div className="mb-4">
                        <label className="block text-sky-950 text-sm font-bold mb-2"
                        >Username</label>
                        <input className={`${errors.username && touched.username ? 'border-red-600 border-2' : 'border-none'}
                            shadow appearance-none border-b-2 rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`}
                        type="text" name="username" value={values.username} onChange={handleChange} onBlur={handleBlur} placeholder="username" />
                        {errors.username && touched.username && <span className="text-red-600 text-xs">{errors.username}</span>}
                    </div>


                    <div className="mb-6">
                        <label className="block text-sky-950 text-sm font-bold mb-2"
                        >Password</label>
                        <input className={`${errors.password && touched.password ? 'border-red-600 border-2' : 'border-none'}
                            shadow appearance-none border-b-2  rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`} 
                        type="password" name="password" value={values.password} onChange={handleChange} onBlur={handleBlur} placeholder="**********" />
                        {errors.password && touched.password && <span className="text-red-600 text-xs">{errors.password}</span>}
                    </div>

                    <AuthButton type="submit" disabled={isSubmitting}>Login</AuthButton>
                    <NavLink to={'/register'} className="text-sky-950 inline ml-10 hover:cursor-pointer font-light">Sign up here</NavLink>

                </form>
            </div>

            <div className="w-full h-full hidden lg:block rounded-bl-full overflow-hidden">
                <img className="object-cover w-full h-full" src="src\assets\book-wall.jpg" alt="Picture of a wall made of books" />
            </div>
        </div>
  )
}

export default LoginPage