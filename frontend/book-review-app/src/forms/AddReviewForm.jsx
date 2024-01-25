import { useState } from "react";
import FormLoading from "../components/FormLoading";
import { postReview } from "../api";
import useVerifyUser from "../hooks/useVerifyUser";
import useToast from "../hooks/useToast";
import PropTypes from "prop-types"

const AddReviewForm = (props) => {
    
    const { closeWindow, bookId } = props;
    const { userId } = useVerifyUser();
    const { notifyFailure } = useToast();

    const [isLoading, setIsLoading] = useState(false)
    const [reviewData, setReviewData] = useState({
        score: 4,
        description: '',
        userId: userId
    })

    const handleInput = (e) => {
        const { name, value } = e.target;
        setReviewData({...reviewData, [name]: value})
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        sendDataToApi();
    }

    const sendDataToApi = async () => {

        setIsLoading(true);
   
        try {
            const response = await postReview(bookId, reviewData);
            const { status } = response;

            if(status === 201) {
                closeWindow();
            }
        }
        catch(err) {
            notifyFailure(err);
        }
        finally {
            setIsLoading(false);
        }
    }


    if(isLoading) {
        return (
            <div className="w-full max-h-[calc(100vh-5rem)] h-[calc(100vh-5rem)] flex flex-col justify-center items-center absolute z-10">
                 <div className="w-3/5 h-1/2 relative shadow-2xl rounded-xl p-8 bg-white">
                    <FormLoading />
                 </div>
            </div> 
        ) 
            
    }


    return (
        <div className="w-full max-h-[calc(100vh-5rem)] h-[calc(100vh-5rem)] flex flex-col justify-center items-center absolute z-10">
            <form onSubmit={handleSubmit} className="w-3/5 h-1/2 relative shadow-2xl rounded-xl p-8 bg-white">
                <button onClick={closeWindow} className="w-6 h-6 rounded-full bg-sky-600 flex justify-center items-center text-white absolute -right-2 -top-3 transition ease-linear hover:bg-white hover:text-sky-950">
                    <svg className="h-4 w-4" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" />
                    </svg>
                </button>
                <div className="flex mb-4 justify-between">
                    <h1 className="text-sky-950 font-semibold text-lg">Your Review</h1>
                </div>
                <div className="flex h-52">
                    <textarea onChange={handleInput} placeholder="Message" value={reviewData.description} name="description" className="w-full h-full rounded-lg bg-gray-100 p-2 resize-none focus:outline-none focus:shadow-outline"></textarea>
                </div>
                <button type="submit" className="bg-sky-950 text-white text-base px-4 py-1 rounded-lg tracking-wide mt-6 float-right transition ease-linear hover:bg-white hover:text-sky-950">Submit</button>
            </form>
        </div>
    )
}


AddReviewForm.propTypes = {
    closeWindow: PropTypes.func,
    bookId: PropTypes.number
}

export default AddReviewForm