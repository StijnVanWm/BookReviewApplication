import { useFormik } from "formik"
import { RegisterSchema } from "../schemas";
import Title from "../components/Title";
import AuthButton from "../components/AuthButton";
import { sendDataToApi } from "../api";
import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { Toaster } from "react-hot-toast";
import useToast from "../hooks/useToast";

const RegisterPage = () => {

    const [succesfullRegister, setSuccesfullRegister] = useState(false);
    const [newUsername, setNewUsername] = useState("");
    const { notifySuccess, notifyFailure } = useToast(); 

    const navigate = useNavigate();

    

    const onSubmit = async () => {

        try {

            const { confirmPassword, ...vals } = values;
            const response = await sendDataToApi('/users', vals);

            const { data } = response;

            if(response.status === 201) {
                setNewUsername(data.username);
                setSuccesfullRegister(true);
            }


        } catch(err) {
            notifyFailure("Failed to add user");
        }
    }



    const { values, errors, touched, isSubmitting, handleBlur, handleChange, handleSubmit } = useFormik({
        initialValues: {
            username: '',
            password: '',
            confirmPassword: '',
            name: '',
            firstName: '',
            email: '',
            phoneNumber: ''
        },
        validationSchema: RegisterSchema,
        onSubmit: onSubmit

    });


    if(succesfullRegister) {
        notifySuccess(`User ${newUsername} has successfully been added`);
        setTimeout(() => {
            navigate('/login')
        }, 1000);
    }

    return (
        <div className={`w-full h-screen grid grid-cols-1 lg:grid-cols-2 items-center`}>
            <Toaster />
            <div className={succesfullRegister ? 
                `flex flex-col items-start w-3/4 m-auto gap-y-6 opacity-30`:
                `flex flex-col items-start w-3/4 m-auto gap-y-6`
            }>

                <Title>Sign up</Title>

                <form onSubmit={handleSubmit} className="pr-8 pt-6 pb-8 mb-4 w-full">

                    <div className="mb-4">
                        <label className="block text-sky-950 text-sm font-bold mb-2"
                        >Username</label>
                        <input className={`${errors.username && touched.username ? 'border-red-600 border-2' : 'border-none'}
                            shadow appearance-none border-b-2 rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`}
                        type="text" name="username" value={values.username} onChange={handleChange} onBlur={handleBlur} placeholder="username" />
                        {errors.username && touched.username && <span className="text-red-600 text-xs">{errors.username}</span>}
                    </div>

                    <div className="w-full h-fit flex gap-2 justify-between">
                        <div className="mb-4 w-1/2">
                            <label className="block text-sky-950 text-sm font-bold mb-2"
                            >Name</label>
                            <input className={`${errors.name && touched.name ? 'border-red-600 border-2' : 'border-none'}
                                shadow appearance-none border-b-2 rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`}
                            type="text" name="name" value={values.name} onChange={handleChange} onBlur={handleBlur} placeholder="name" />
                            {errors.name && touched.name && <span className="text-red-600 text-xs">{errors.name}</span>}
                        </div>

                        <div className="mb-4 w-1/2">
                            <label className="block text-sky-950 text-sm font-bold mb-2"
                            >Firstname</label>
                            <input className={`${errors.firstName && touched.firstName ? 'border-red-600 border-2' : 'border-none'}
                                shadow appearance-none border-b-2 rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`}
                            type="text" name="firstName" value={values.firstName} onChange={handleChange} onBlur={handleBlur} placeholder="firstname" />
                            {errors.firstName && touched.firstName && <span className="text-red-600 text-xs">{errors.firstName}</span>}
                        </div>
                    </div>

                    <div className="w-full h-fit flex gap-2 justify-between">
                        <div className="mb-6 w-1/2">
                            <label className="block text-sky-950 text-sm font-bold mb-2"
                            >Password</label>
                            <input className={`${errors.password && touched.password ? 'border-red-600 border-2' : 'border-none'}
                                shadow appearance-none border-b-2  rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`} 
                            type="password" name="password" value={values.password} onChange={handleChange} onBlur={handleBlur} placeholder="**********" />
                            {errors.password && touched.password && <span className="text-red-600 text-xs">{errors.password}</span>}
                        </div>

                        <div className="mb-6 w-1/2">
                            <label className="block text-sky-950 text-sm font-bold mb-2"
                            >Confirm Password</label>
                            <input className={`${errors.confirmPassword && touched.confirmPassword ? 'border-red-600 border-2' : 'border-none'}
                                shadow appearance-none border-b-2  rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`} 
                            type="password" name="confirmPassword" value={values.confirmPassword} onChange={handleChange} onBlur={handleBlur} placeholder="**********" />
                            {errors.confirmPassword && touched.confirmPassword && <span className="text-red-600 text-xs">{errors.confirmPassword}</span>}
                        </div>
                    </div>

                    <div className="w-full h-fit flex justify-between gap-2">
                        <div className="mb-6 w-1/2">
                            <label className="block text-sky-950 text-sm font-bold mb-2"
                            >Email</label>
                            <input className={`${errors.email && touched.email ? 'border-red-600 border-2' : 'border-none'}
                                shadow appearance-none border-b-2  rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`} 
                            type="email" name="email" value={values.email} onChange={handleChange} onBlur={handleBlur} placeholder="Email" />
                            {errors.email && touched.email && <span className="text-red-600 text-xs">{errors.email}</span>}
                        </div>

                        <div className="mb-6 w-1/2">
                            <label className="block text-sky-950 text-sm font-bold mb-2"
                            >Phone</label>
                            <input className={`${errors.phoneNumber && touched.phoneNumber ? 'border-red-600 border-2' : 'border-none'}
                                shadow appearance-none border-b-2  rounded w-full py-2 px-3 text-gray-700 leading-tight outline-none`} 
                            type="text" name="phoneNumber" value={values.phoneNumber} onChange={handleChange} onBlur={handleBlur} placeholder="0465892447" />
                            {errors.phoneNumber && touched.phoneNumber && <span className="text-red-600 text-xs">{errors.phoneNumber}</span>}
                        </div>
                </div>

                <div className="w-full flex justify-between items-center">
                    <AuthButton type="submit" disabled={isSubmitting}>Sign up!</AuthButton>
                    <NavLink to='/login' className="text-sky-950 inline ml-10 hover:cursor-pointer font-light">login here</NavLink>
                </div>

                </form>
            </div>

            <div className="w-full h-full hidden lg:block rounded-bl-full overflow-hidden">
                <img className="object-cover w-full h-full" src="src\assets\book-wall.jpg" alt="Picture of a wall made of books" />
            </div>
        </div>
    )
}

export default RegisterPage