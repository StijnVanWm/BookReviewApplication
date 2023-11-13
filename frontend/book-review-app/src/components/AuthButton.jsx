
const AuthButton = (props) => {

    return (
        <>
            <button {...props}
                className="rounded-lg px-9 py-2 bg-sky-950 my-1 text-lg text-white font-semibold tracking-wider"
            >
                {props.children} 
            </button>
        </>
    )
}

export default AuthButton