import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter } from "react-router";
import { RouterProvider } from "react-router/dom";
import {BrowserRouter} from "react-router"
import './index.css'
import App from './App.jsx'
/*
const router = createBrowserRouter([
  {
    path:'/',
    element: <App/>
  }
]);
*/
//<RouterProvider router={router} />
createRoot(document.getElementById('root')).render(
    <StrictMode>
      <BrowserRouter>
        <App/>
      </BrowserRouter>
    </StrictMode>
)
