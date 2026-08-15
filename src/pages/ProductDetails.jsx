import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import api from '../services/api';
import './ProductDetails.css';

function ProductDetails() {

  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {

    api.get(`/products/${id}`)
      .then(response => {
        setProduct(response.data);
      })
      .catch(error => {
        console.error("Failed to load product:", error);
      });

  }, [id]);

  if (!product) {
    return null;
  }

  const increaseQuantity = () => {
    setQuantity(quantity + 1);
  };

  const decreaseQuantity = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  return (
    <div
      className="product-overlay"
      onClick={() => navigate('/products')}
    >

      <div
        className="product-box"
        onClick={(e) => e.stopPropagation()}
      >

        <img
          src={product.image}
          alt={product.name}
        />

        <div className="product-info">

          <h1>{product.name}</h1>

          <strong className="price">
            ₹{product.price}
          </strong>

          <p>
            {product.description}
          </p>

          <div className="quantity">

            <span>Quantity</span>

            <button onClick={decreaseQuantity}>
              −
            </button>

            <span>{quantity}</span>

            <button onClick={increaseQuantity}>
              +
            </button>

          </div>

<button
  className="add-cart"
  onClick={() => {

    const token = localStorage.getItem("token");

    // Not logged in
    if (!token) {
      navigate("/login");
      return;
    }

    // Logged in → add to cart
    const data = {
      productId: product.id,
      quantity: quantity
    };

    api.post("/cart", data)
      .then(() => {
        navigate("/Cart");
      })
      .catch(error => {
        console.error("Failed to add product to cart:", error);
      });

  }}
>
  Add to Cart
</button>

        </div>

      </div>

    </div>
  );
}

export default ProductDetails;