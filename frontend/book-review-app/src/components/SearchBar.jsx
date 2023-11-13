
const SearchBar = () => {
  return (
    <>
      <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 absolute left-2 top-3 text-gray-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
        </svg>
      <input type="text" id="searchbar" placeholder="Search for a book"
          className="w-full p-3 pb-2.5 pl-10 text-sm text-sky-950 border border-t-0 border-l-0 border-r-0 border-b-2 outline-none rounded-lg shadow-sm"
      />
    </>
  )
}

export default SearchBar