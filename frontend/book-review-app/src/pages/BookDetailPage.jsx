import { useEffect, useState } from "react"
import { useParams } from "react-router-dom";
import DetailPageTitle from "../components/DetailPageTitle";
import Loading from "../components/Loading";
import NavButton from "../components/NavButton";
import ReviewListItem from "../components/ReviewListItem";
import AddReviewForm from "../forms/AddReviewForm";
import { fetchBook, fetchBookReviews } from "../api";
import FormLoading from "../components/FormLoading";
import useVerifyUser from "../hooks/useVerifyUser";




const BookDetailPage = () => {

    const [book, setBook] = useState({});
    const [reviews, setReviews] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [commentsLoading, setCommentsLoading] = useState(true);
    const [currentPageNo, setCurrentPageNo] = useState(0);
    const [isLastPage, setIsLastPage] = useState(false);
    const [isFirstPage, setIsFirstPage] = useState(true);
    const [addReviewWindow, setAddReviewWindow] = useState(false);
    const { isVerified } = useVerifyUser();

    const { id } = useParams()

    useEffect(() => {
        fetchBookDetails(id);
    }, [])

    useEffect(() => {
        fetchReviews(id);
    }, [currentPageNo])


    const fetchBookDetails = async (bookId) => {

        try {
            const bookResponse = await fetchBook(bookId);
            setBook(bookResponse.data);
            
        } catch (err) {
            alert(err)
        }
        finally {
            setTimeout(() => {
                setIsLoading(false);
            }, 500)
        }

    }

    const fetchReviews = async (bookId) => {

        const pageSize = 3;

        setCommentsLoading(true);

        try {

            const reviewResponse = await fetchBookReviews(bookId, currentPageNo, pageSize);
            const { data: { lastPage, pageNo } } = reviewResponse;

            setIsFirstPage(pageNo === 0);
            setIsLastPage(lastPage);

           setReviews(reviewResponse.data.content);
        } catch(err) {
            alert(err)
        } finally {
            setTimeout(() => {
                setCommentsLoading(false);
            }, 150);
        }

    }

    if(isLoading) {
        return <Loading />
    }

    return (
        <>
            {
                addReviewWindow && <AddReviewForm closeWindow={() =>{
                    setAddReviewWindow(false);
                    fetchReviews(book.id);
                }} bookId={book.id} />
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
                    <div className="w-full shadow-xl rounded-lg flex flex-col p-4 pt-3 pb-3 bg-white relative">
                        { !isVerified && <span className="absolute right-5 -top-7 text-sm text-sky-600">Sign in to write a review</span>}
                        <div className="flex justify-between mb-2 items-center">
                            <h4 className="text-sky-950 font-medium text-lg mb-2">Reviews</h4>
                            <button disabled={!isVerified} onClick={() => setAddReviewWindow(true)} className="py-1 px-2 bg-sky-950 rounded-lg transition ease-linear text-white text-base hover:bg-white hover:text-sky-950 disabled:opacity-30 disabled:cursor-default disabled:hover:bg-sky-950 disabled:hover:text-white">add review</button>
                        </div>
                        <div className="w-full h-full">
                            {
                                commentsLoading 
                                ? <FormLoading />
                                : <ul className="w-full h-full">
                                    {
                                        reviews.map(review => (
                                        <ReviewListItem key={review.id} review={review} />
                                        ))
                                    }
                                  </ul>
                            }
                        </div>
                        <div className="flex justify-between px-1">
                            <NavButton onClick={() => setCurrentPageNo(prev => prev - 1)} disabled={isFirstPage}>Previous</NavButton>
                            <NavButton onClick={() => setCurrentPageNo(prev => prev + 1)} disabled={isLastPage}>Next</NavButton>
                        </div>
                    </div>
                </div>     
            </div>
        </>
    )
}

export default BookDetailPage