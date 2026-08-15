import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import api from '../services/api'
import './Login.css'

function Login() {

  const [user, setUser] = useState({
    email: '',
    password: ''
  })
const [error, setError] = useState('')
  const navigate = useNavigate()

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value
    })
  }
const handleSubmit = async (e) => {
  e.preventDefault()

  console.log("LOGIN SUBMITTED")

  setError('')

  try {

    const response = await api.post('/auth/login', user)

    console.log("LOGIN SUCCESS")
    console.log("Token:", response.data)

    localStorage.setItem('token', response.data)

    navigate('/Home')

  } catch (error) {

    console.log("LOGIN FAILED")
    console.log(error)

    setError('Email or password is incorrect')

  }
}
  return (
    <div className="login-page">

      <div className="login-card">

        <h1>Welcome Back</h1>

        <p className="login-subtitle">
          Login to your RetroSwap account
        </p>

        <form onSubmit={handleSubmit}>

          <input
            type="email"
            name="email"
            placeholder="Email"
            value={user.email}
            onChange={handleChange}
            required
          />

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={user.password}
            onChange={handleChange}
            required
          />
{error && (
  <p className="login-error">
    {error}
  </p>
)}
          <button type="submit">
            Login
          </button>

        </form>

        <p className="register-text">
          Don't have an account?
          <Link to="/register"> Register</Link>
        </p>

      </div>

    </div>
  )
}

export default Login