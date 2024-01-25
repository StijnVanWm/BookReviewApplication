import * as yup from 'yup';

export const loginSchema = yup.object().shape({
    username: yup.string().required("Must enter a username!"),
    password: yup.string().required("Must enter a password!")
})

export const RegisterSchema = yup.object().shape({
    username: yup.string().required("Must enter a username!"),
    name: yup.string().required("Must enter your name!"),
    firstName: yup.string().required("Must enter your first name"),
    password: yup.string().required("Must enter a password!").min(8, "Password must be at least 8 characters."),
    confirmPassword: yup.string().oneOf([yup.ref('password'), null], "Passwords must match!").required("Must enter a confirmation password"),
    email: yup.string().email().required(),
    phoneNumber: yup.string().length(10, "Not a valid phonenumber").required("Must enter a phonenumber.")
})