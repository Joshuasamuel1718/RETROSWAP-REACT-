import { useState } from 'react'
import api from '../services/api'
import './AddProduct.css'

function AddProduct() {

  const [product, setProduct] = useState({
    details: '',
    description: '',
    name: '',
    category: '',
    color: '',
    quantity: 1,
    price: ''
  })

  const [file, setFile] = useState(null)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  const handleChange = (e) => {
    const { name, value } = e.target

    setProduct({
      ...product,
      [name]:
        name === 'category' ||
        name === 'quantity' ||
        name === 'price'
          ? Number(value)
          : value
    })
  }

  const handleFileChange = (e) => {
    setFile(e.target.files[0])
  }

  const handleSubmit = async (e) => {
    e.preventDefault()

    setMessage('')
    setError('')

    if (!file) {
      setError('Please select an image')
      return
    }

    const formData = new FormData()

formData.append(
  'product',
  new Blob(
    [JSON.stringify(product)],
    { type: 'application/json' }
  )
)
   formData.append('image', file)

    try {

      await api.post('/products', formData)

      setMessage('Product added successfully!')

      setProduct({
        details: '',
        description: '',
        name: '',
        category: '',
        color: '',
        quantity: 1,
        price: ''
      })

      setFile(null)

    } catch (error) {

      console.error(error)
      setError('Failed to add product')

    }
  }

  return (
    <div className="add-product-page">

      <div className="add-product-card">

        <h1>Sell Your Product</h1>

        <p className="add-product-subtitle">
          Add your product to RetroSwap
        </p>

        <form onSubmit={handleSubmit}>

          <input
            type="text"
            name="name"
            placeholder="Product Name"
            value={product.name}
            onChange={handleChange}
            required
          />

          <textarea
            name="description"
            placeholder="Description"
            value={product.description}
            onChange={handleChange}
            required
          />

          <textarea
            name="details"
            placeholder="Product Details"
            value={product.details}
            onChange={handleChange}
            required
          />

          <select
            name="category"
            value={product.category}
            onChange={handleChange}
            required
          >
            <option value="">
              Select Category
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

          <input
            type="text"
            name="color"
            placeholder="Color"
            value={product.color}
            onChange={handleChange}
          />

          <input
            type="number"
            name="quantity"
            placeholder="Quantity"
            min="1"
            value={product.quantity}
            onChange={handleChange}
            required
          />

          <input
            type="number"
            name="price"
            placeholder="Price"
            min="0"
            step="0.01"
            value={product.price}
            onChange={handleChange}
            required
          />

          <div className="image-input">

            <label>
              Product Image
            </label>

            <input
              type="file"
              accept="image/*"
              onChange={handleFileChange}
              required
            />

          </div>

          {message && (
            <p className="success-message">
              {message}
            </p>
          )}

          {error && (
            <p className="error-message">
              {error}
            </p>
          )}

          <button type="submit">
            ADD PRODUCT
          </button>

        </form>

      </div>

    </div>
  )
}

export default AddProduct