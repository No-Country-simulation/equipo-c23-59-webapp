
import { Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './views/Home'
import Login from './views/Login'

function App() {


  return (
    <>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
      </Routes>
    </>
  )
}

export default App
