import axios from "axios";
import { useEffect, useState } from "react"
import BookCard from "../components/BookCard";
import Loading from "../components/Loading";
import { useNavigate } from "react-router-dom";

const HomePage = () => {

  const [books, setBooks] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    fetchBooks()
  },[])

  const fetchBooks = async () => {

    try {
      const response = await axios.get(`${import.meta.env.VITE_API_KEY}/books`);
      const { data } = response;
      setBooks(data);
    }
    catch(err) {
      alert(err)
    }
    finally {
      setTimeout(() => {
        setIsLoading(false);
      }, 500);
    }

  }

  if(isLoading) {
    return (
      <Loading />
    )
  }

  return (
    <div className="bg-gray-100 p-16 flex flex-col justify-center items-center">
      <h1 className="text-6xl text-sky-950 font-bold w-fit tracking-wider">BOOKOO</h1>
      <div className="w-full mt-12 flex justify-center items-center flex-wrap">
        {
          books.map(book => (
            <BookCard key={book.id} book={book} onClick={() => navigate(`book/${book.id}`)}/>
          ))
        }
      </div>
    </div>
  )
}

export default HomePage