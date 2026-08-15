import { useState } from 'react'
// import { Link } from 'react-router-dom'
import api from '../services/api'
import './Register.css'
import { Link, useNavigate } from 'react-router-dom'

function Register() {
  const navigate = useNavigate()

  const [user, setUser] = useState({
    name: '',
    email: '',
    password: '',
    mobileno: ''
  })

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmit = (e) => {
    e.preventDefault()

    api.post('/auth/register', user)
      .then(response => {
        console.log('Registration successful')
        navigate('/login')
      })
      .catch(error => {
        console.error(error)
      })
  }

  return (
    <div className="register-page">

      <div className="register-card">

        <h1>Create Account</h1>
        <p className="register-subtitle">
          Join RetroSwap today
        </p>

        <form onSubmit={handleSubmit}>

          <input
            type="text"
            name="name"
            placeholder="Full Name"
            value={user.name}
            onChange={handleChange}
            required
          />

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

          <input
            type="text"
            name="mobileno"
            placeholder="Mobile Number"
            value={user.mobileno}
            onChange={handleChange}
            required
          />

          <button type="submit">
            Register
          </button>

        </form>

        <p className="login-text">
          Already have an account?
          <Link to="/login"> Login</Link>
        </p>

      </div>

    </div>
  )
}

export default Register