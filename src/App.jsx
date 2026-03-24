import { useState } from 'react'
import Dashboard from "./components/dashboard/Dashboard"
import Header from './components/home/Header'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { Route, Routes } from "react-router";

function App() {

  return (
    <>
      <Routes>
        <Route path="/home" element={<Header/>} />
        <Route path="/dashboard" element={<Dashboard/>} />
      </Routes>
    </>
  )
}

export default App
