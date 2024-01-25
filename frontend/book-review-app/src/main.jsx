import ReactDOM from 'react-dom/client'
import "./index.css"
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import RootLayout from './navigation/RootLayout.jsx'
import HomePage from './pages/HomePage.jsx'
import BookDetailPage from './pages/BookDetailPage.jsx'
import LoginPage from './pages/LoginPage.jsx'
import RegisterPage from './pages/RegisterPage.jsx'
import SearchContextProvider from './context/SearchContext.jsx'
import VerifyUserContextProvider from './context/VerifyUserContext.jsx'
import ToastContextProvider from './context/ToastContext.jsx'

const browserRouter = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />
      },
      {
        path: '/book/:id',
        element:(
          <VerifyUserContextProvider>
            <BookDetailPage />
          </VerifyUserContextProvider>
        ) 
      },
    ]
  },
  {
    path: '/login',
    element: <LoginPage />
  },
  {
    path: '/Register',
    element: <RegisterPage />
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <ToastContextProvider>
    <SearchContextProvider>
      <RouterProvider router={browserRouter} />
    </SearchContextProvider>
  </ToastContextProvider>
)
