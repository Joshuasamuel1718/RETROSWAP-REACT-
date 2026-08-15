import { useEffect, useState } from 'react'
import api from '../services/api'
import './home.css'
import { useNavigate, useLocation } from "react-router-dom"

import dressBanner from '../assets/dress-banner.jpg'
import watchbanner from '../assets/watchbanner.jpg'
import laptopbanner from '../assets/Laptopbanner.jpg'
import gamebanner from '../assets/Gamebanner.jpg'
import bookbanner from '../assets/Bookbanner.jpg'

function Home() {

  const navigate = useNavigate()
  const location = useLocation()

  const [products, setProducts] = useState([])

  useEffect(() => {

    api.get('/products')
      .then(response => {
        console.log('Products:', response.data)
        setProducts(response.data)
      })
      .catch(error => {
        console.error('Failed to load products:', error)
      })

  }, [])


  // SCROLL TO SELECTED CATEGORY
  useEffect(() => {

    const categoryId = location.state?.category

    if (!categoryId) {
      return
    }

    const timer = setTimeout(() => {

      const element = document.getElementById(
        `category-${categoryId}`
      )

      if (element) {
        element.scrollIntoView({
          behavior: 'smooth',
          block: 'start'
        })
      }

    }, 100)

    return () => clearTimeout(timer)

  }, [location.state])


  const categories = [
    {
      id: 1,
      name: 'Clothing',
      banner: dressBanner
    },
    {
      id: 2,
      name: 'Laptops',
      banner: laptopbanner
    },
    {
      id: 3,
      name: 'Games',
      banner: gamebanner
    },
    {
      id: 4,
      name: 'Books',
      banner: bookbanner
    },
    {
      id: 5,
      name: 'Watches',
      banner: watchbanner
    }
  ]


  return (
    <div className="products-page">

      {categories.map(category => {

        const categoryProducts = products.filter(
          product => product.category === category.id
        )

        if (categoryProducts.length === 0) {
          return null
        }

        return (
          <section
            className="category-section"
            id={`category-${category.id}`}
            key={category.id}
          >

            {/* CATEGORY BANNER */}

            <div className="category-banner">

              <img
                src={category.banner}
                alt={category.name}
              />

            </div>


            {/* PRODUCTS */}

            <div className="products-row">

              {categoryProducts.map(product => (

                <div
                  className="product-card"
                  key={product.id}
                  onClick={() =>
                    navigate(`/product/${product.id}`)
                  }
                >

                  <img
                    src={product.image}
                    alt={product.name}
                  />

                  <h3>
                    {product.name}
                  </h3>

                  <strong className="price">
                    ₹{product.price}
                  </strong>

                </div>

              ))}

            </div>

          </section>
        )

      })}

    </div>
  )
}

export default Home