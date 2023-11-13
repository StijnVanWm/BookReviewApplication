import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import "./index.css"
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import RootLayout from './navigation/RootLayout.jsx'
import HomePage from './pages/HomePage.jsx'
import BookDetailPage from './pages/BookDetailPage.jsx'

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
        element: <BookDetailPage />
      }

    ]
  }

])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={browserRouter} />
  </React.StrictMode>,
)
