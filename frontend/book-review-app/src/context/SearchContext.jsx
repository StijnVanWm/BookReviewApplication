import { createContext, useState } from "react"

export const SearchContext = createContext();

const SearchContextProvider = (props) => {

    const [searchInput, setSearchInput] = useState("");

    return <SearchContext.Provider value={{ searchInput, setSearchInput }} {...props}></SearchContext.Provider>
}

export default SearchContextProvider