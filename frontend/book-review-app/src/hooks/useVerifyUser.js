import { useContext } from "react"
import { verifyUserContext } from "../context/VerifyUserContext"

const useVerifyUser = () => {
  return useContext(verifyUserContext)
}

export default useVerifyUser