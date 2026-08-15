function ProductCard({ product }) {
  return (
    <div className="product-card">
      <img
        src={product.image}
        alt={product.name}
        className="product-image"
      />

      <div className="product-info">
        <h2>{product.name}</h2>
        <p className="price">₹{product.price}</p>
      </div>
    </div>
  )
}

export default ProductCard