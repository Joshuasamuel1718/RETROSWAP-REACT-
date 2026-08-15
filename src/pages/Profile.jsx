import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../services/api'
import './Profile.css'
import EditProfile from '../components/EditProfile';
function Profile() {

  const [user, setUser] = useState(null)
  const navigate = useNavigate()
  const [showEdit, setShowEdit] = useState(false);

  useEffect(() => {
    api.get('/user/profile')
      .then(response => {
        setUser(response.data)
      })
      .catch(error => {
        console.error(error)
      })
  }, [])

  const logout = () => {
    localStorage.removeItem('token')
    navigate('/login')
  }

  if (!user) {
    return <p>Loading...</p>
  }

  return (
    <div className="profile-page">

      <div className="profile-card">

        <div className="profile-icon">
          👤
        </div>

        <h1>My Account</h1>

        <p><strong>Name:</strong> {user.name}</p>
        <p><strong>Email:</strong> {user.email}</p>
        <p><strong>Mobile:</strong> {user.mobileno}</p>

       <div className="profile-actions">

  <button onClick={() => setShowEdit(true)}>
    👤 Edit Profile
  </button>

  <button onClick={() => navigate('/cart')}>
    🛒 My Cart
  </button>

  <button onClick={() => navigate('/orders')}>
    📦 My Orders
  </button>

  <button onClick={() => navigate('/add-product')}>
    🏷️ Sell an Item
  </button>

  <button onClick={() => navigate('/my-products')}>
    📋 My Listings
  </button>

  <button onClick={logout} className="logout-button">
    🚪 Logout
  </button>
{showEdit && (
    <EditProfile
        user={user}
        onClose={() => setShowEdit(false)}
    />
)}
</div>
      </div>

    </div>
  )
}

export default Profile