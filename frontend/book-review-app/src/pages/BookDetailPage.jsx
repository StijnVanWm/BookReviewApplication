import axios from "axios"
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom";
import DetailPageTitle from "../components/DetailPageTitle";
import Loading from "../components/Loading";
import NavButton from "../components/NavButton";
import ReviewListItem from "../components/ReviewListItem";
import AddReviewForm from "../forms/AddReviewForm";




const BookDetailPage = () => {

    const [book, setBook] = useState({});
    const [reviews, setReviews] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [addReviewWindow, setAddReviewWindow] = useState(false);

    const { id } = useParams()

    useEffect(() => {
        fetchBooksAndReviews(id);
    }, [])


    const fetchBooksAndReviews = async (bookId) => {

        try {
            const bookResponse = await axios.get(`${import.meta.env.VITE_API_KEY}/books/${bookId}`)
            const reviewResponse = await axios.get(`${import.meta.env.VITE_API_KEY}/books/${bookId}/reviews`)

            setBook(bookResponse.data);
            setReviews(reviewResponse.data);
            
        } catch (err) {
            alert(err)
        }
        finally {
            setTimeout(() => {
                setIsLoading(false);
            }, 500)
        }

    }

    if(isLoading) {
        return <Loading />
    }

    return (
        <>
            {
                addReviewWindow && <AddReviewForm closeWindow={() => setAddReviewWindow(false)} bookId={book.id} />
            }
            <div className={`w-full max-h-[calc(100vh-5rem)] h-[calc(100vh-5rem)] px-16 py-14 bg-gray-100 relative ${addReviewWindow ? 'opacity-20' : 'opacity-100'}`}>
                <div className="w-full h-36 flex flex-col gap-y-3">
                    <DetailPageTitle>{book.title}</DetailPageTitle>
                    <p className="text-sky-950 font-medium text-xl">{book.author?.firstName} {book.author?.name}</p>
                    <p className="text-sky-950 text-lg">{book.publisher?.name}</p>
                </div>
                <div className="mt-6 w-full flex gap-x-8">
                    <div className="w-96 h-full rounded-xl overflow-hidden">
                        <img className="w-full h-full" src={book.bookCover} alt={book.title} />
                    </div>
                    <div className="w-full shadow-xl rounded-lg flex flex-col p-4 pt-3 pb-3 bg-white">
                        <div className="flex justify-between mb-2 items-center">
                            <h4 className="text-sky-950 font-medium text-lg mb-2">Reviews</h4>
                            <button onClick={() => setAddReviewWindow(true)} className="py-1 px-2 bg-sky-950 rounded-lg transition ease-linear text-white text-base hover:bg-white hover:text-sky-950">add review</button>
                        </div>
                        <div className="w-full h-full">
                            <ul className="w-full h-full">
                                {
                                    reviews.map(review => (
                                        <ReviewListItem key={review.id} review={review} />
                                    ))
                                }
                            </ul>
                        </div>
                        <div className="flex justify-between px-1">
                            <NavButton>Previous</NavButton>
                            <NavButton>Next</NavButton>
                        </div>
                    </div>
                </div>     
            </div>
        </>
    )
}

export default BookDetailPage