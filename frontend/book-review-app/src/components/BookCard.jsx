
const BookCard = (props) => {

    const { book: {title, bookCover, category: { name } } } = props;

    return (
        <div {...props} className="w-70 h-96 m-6 relative rounded-xl overflow-hidden border-none bg-white shadow-lg group hover:cursor-pointer">
            <div className="absolute p-4 w-full font-semibold text-lg opacity-0 top-40 text-sky-950 group-hover:opacity-100">
                <p className="uppercase text-2xl">Read reviews...</p>
            </div>
            <div className="w-full h-full group-hover:opacity-30">
                <img className="h-full w-full" src={bookCover} alt={title} />
            </div>
            <div className="bg-white p-6 pt-2 pb-3 pl-4 absolute bottom-0 right-0 left-0 group-hover:opacity-0">
                <span className="uppercase text-sm tracking-wide font-medium text-sky-700">{name}</span>
                <h3 className="mt-1 font-medium tracking-wide text-sky-950">{title}</h3>
            </div>
        </div>
  )
}

export default BookCard