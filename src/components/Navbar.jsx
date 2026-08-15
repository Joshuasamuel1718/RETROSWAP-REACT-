import './Navbar.css'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react'

import RetroshopLogo from '../assets/Retroshop-Logo.png'
import sellLogo from '../assets/sellLogo.png'
import userLogo from '../assets/userLogo.png'
import cartLogo from '../assets/cartLogo.jpg'

function Navbar() {

  const navigate = useNavigate()

  // CATEGORY
  const handleCategoryChange = (categoryId) => {

    navigate('/products', {
      state: {
        category: categoryId
      }
    })

  }
  const [searchQuery, setSearchQuery] = useState("");

const handleSearch = (e) => {
    if (e.key === "Enter") {
        const query = e.target.value.trim();

        if (!query) return;

        navigate(`/products?search=${encodeURIComponent(query)}`);
    }
};

  // PROFILE
  const handleProfileClick = () => {

    const token = localStorage.getItem('token')

    if (token) {
      navigate('/profile')
    } else {
      navigate('/register')
    }
  }

  // SELL
  const handleSell = () => {

    const token = localStorage.getItem('token')

    if (token) {
      navigate('/add-product')
    } else {
      navigate('/login')
    }
  }

  // CART
  const handleCart = () => {

    const token = localStorage.getItem('token')

    if (token) {
      navigate('/cart')
    } else {
      navigate('/login')
    }
  }

  return (
    <nav className="navbar">

      {/* LEFT */}

      <div className="navbar-left">

        <div
          className="logo-container"
          onClick={() => navigate('/Home')}
        >

          <img
            src={RetroshopLogo}
            alt="RetroSwap"
            className="logo-img"
          />

          <h2 className="logo-text">
            RetroSwap
          </h2>

        </div>


        {/* CATEGORY */}

        <select
          className="category"
          defaultValue=""
          onChange={(e) => handleCategoryChange(e.target.value)}
        >

          <option value="">
            Category
          </option>

          <option value="1">
            Clothing
          </option>

          <option value="2">
            Laptops
          </option>

          <option value="3">
            Games
          </option>

          <option value="4">
            Books
          </option>

          <option value="5">
            Watches
          </option>

        </select>

      </div>


      {/* SEARCH */}

     <div className="search-box">

  <input
    type="text"
    placeholder="Search products..."
    value={searchQuery}
    onChange={(e) => setSearchQuery(e.target.value)}
    onKeyDown={handleSearch}
  />

  <span className='button'
    onClick={() => {
      if (searchQuery.trim()) {
        navigate(`/products?search=${encodeURIComponent(searchQuery)}`);
      }
    }}
  >
    ⌕
  </span>

</div>


      {/* RIGHT */}

      <div className="navbar-right">

        {/* SELL */}

        <button
          onClick={handleSell}
          className="sell"
        >

          <img
            src={sellLogo}
            alt="Sell"
          />

        </button>


        {/* CART */}

        <button
          onClick={handleCart}
          className="cart"
        >

          <img
            src={cartLogo}
            alt="Cart"
          />

        </button>


        {/* PROFILE */}

        <button
          onClick={handleProfileClick}
          className="profile"
        >

          <img
            src={userLogo}
            alt="Profile"
          />

        </button>

      </div>

    </nav>
  )
}

export default Navbar