import { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import api from "../services/api";
import "./Products.css";

function Products() {

  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  const [products, setProducts] = useState([]);

  const [searchQuery, setSearchQuery] = useState("");

  const [category, setCategory] = useState("");
  const [color, setColor] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");

  const [quantities, setQuantities] = useState({});
  const [loading, setLoading] = useState(false);
  const [isSearching, setIsSearching] = useState(false);


  // --------------------------------------------------
  // LOAD PRODUCTS / AI SEARCH FROM URL
  // --------------------------------------------------

  useEffect(() => {

    // Get the COMPLETE natural-language query from the Navbar URL.
    // Do not extract category, price, color, etc. in React.
    const query = searchParams.get("search");

    if (query && query.trim()) {

      setSearchQuery(query);
      searchProducts(query);

    } else {

      setSearchQuery("");
      setIsSearching(false);
      loadProducts();

    }

  }, [searchParams]);


  // --------------------------------------------------
  // LOAD ALL PRODUCTS
  // --------------------------------------------------

  const loadProducts = async () => {

    try {

      setLoading(true);

      const response = await api.get("/products");

      setProducts(response.data);

    } catch (error) {

      console.error("Failed to load products:", error);

      setProducts([]);

    } finally {

      setLoading(false);

    }

  };


  // --------------------------------------------------
  // AI SEARCH
  // The FULL natural-language query is sent to the backend.
  // Example:
  // "show me watches under 2000"
  // -> /ai-search?query=show%20me%20watches%20under%202000
  // The backend/Groq handles understanding the query.
  // --------------------------------------------------

  const searchProducts = async (query) => {

    const fullQuery = query?.trim();

    if (!fullQuery) {

      setIsSearching(false);
      loadProducts();

      return;

    }

    try {

      setLoading(true);
      setIsSearching(true);

      console.log("Sending full AI search query:", fullQuery);

 const response = await api.post(
  "/ai-test",
  fullQuery,
  {
    headers: {
      "Content-Type": "text/plain"
    }
  }
);

      console.log("AI Search Results:", response.data);

      setProducts(Array.isArray(response.data) ? response.data : []);

    } catch (error) {

      console.error("AI search failed:", error);

      setProducts([]);

    } finally {

      setLoading(false);

    }

  };


  // --------------------------------------------------
  // CATEGORY FILTER
  // --------------------------------------------------

  const handleCategoryChange = (value) => {

    setCategory(value);

    if (value === "") {

      loadProducts();

      return;

    }

    filterProducts(
      value,
      color,
      minPrice,
      maxPrice
    );

  };


  // --------------------------------------------------
  // COLOR FILTER
  // --------------------------------------------------

  const handleColorChange = (value) => {

    setColor(value);

    filterProducts(
      category,
      value,
      minPrice,
      maxPrice
    );

  };


  // --------------------------------------------------
  // PRICE FILTER
  // --------------------------------------------------

  const handleMinPriceChange = (e) => {

    const value = e.target.value;

    setMinPrice(value);

    filterProducts(
      category,
      color,
      value,
      maxPrice
    );

  };


  const handleMaxPriceChange = (e) => {

    const value = e.target.value;

    setMaxPrice(value);

    filterProducts(
      category,
      color,
      minPrice,
      value
    );

  };


  // --------------------------------------------------
  // NORMAL FILTER
  // --------------------------------------------------

  const filterProducts = async (
    selectedCategory,
    selectedColor,
    minimumPrice,
    maximumPrice
  ) => {

    try {

      setLoading(true);

      setIsSearching(false);

      const params = {};

      if (selectedCategory) {
        params.category = selectedCategory;
      }

      if (selectedColor) {
        params.color = selectedColor;
      }

      if (minimumPrice) {
        params.minPrice = minimumPrice;
      }

      if (maximumPrice) {
        params.maxPrice = maximumPrice;
      }

      const response = await api.get(
        "/products/filter",
        {
          params
        }
      );

      setProducts(response.data);

    } catch (error) {

      console.error("Filter failed:", error);

      setProducts([]);

    } finally {

      setLoading(false);

    }

  };


  // --------------------------------------------------
  // CLEAR FILTERS
  // --------------------------------------------------

  const clearFilters = () => {

    setCategory("");
    setColor("");
    setMinPrice("");
    setMaxPrice("");

    setSearchQuery("");
    setIsSearching(false);

    // Remove ?search=... from URL
    navigate("/products");

    loadProducts();

  };


  // --------------------------------------------------
  // QUANTITY
  // --------------------------------------------------

  const increaseQuantity = (id) => {

    setQuantities((previous) => ({

      ...previous,

      [id]: (previous[id] || 1) + 1

    }));

  };


  const decreaseQuantity = (id) => {

    setQuantities((previous) => {

      const currentQuantity =
        previous[id] || 1;

      if (currentQuantity <= 1) {
        return previous;
      }

      return {

        ...previous,

        [id]: currentQuantity - 1

      };

    });

  };


  // --------------------------------------------------
  // ADD TO CART
  // --------------------------------------------------

  const addToCart = (product) => {

    const quantity =
      quantities[product.id] || 1;

    const existingCart =
      JSON.parse(
        localStorage.getItem("cart")
      ) || [];


    const existingProduct =
      existingCart.find(
        (item) => item.id === product.id
      );


    if (existingProduct) {

      existingProduct.quantity += quantity;

    } else {

      existingCart.push({

        ...product,

        quantity: quantity

      });

    }


    localStorage.setItem(
      "cart",
      JSON.stringify(existingCart)
    );


    alert("Product added to cart");

  };


  // --------------------------------------------------
  // PRODUCT DETAILS
  // --------------------------------------------------

  const openProduct = (product) => {

    navigate(`/product/${product.id}`);

  };


  // --------------------------------------------------
  // UI
  // --------------------------------------------------

  return (

    <div className="products-page">


      {/* --------------------------------------------- */}
      {/* CONTENT */}
      {/* --------------------------------------------- */}

      <div className="products-layout">


        {/* ------------------------------------------- */}
        {/* SIDEBAR */}
        {/* ------------------------------------------- */}

     

        {/* ------------------------------------------- */}
        {/* PRODUCTS */}
        {/* ------------------------------------------- */}

        <main className="products-main">


          {/* RESULT HEADER */}

          <div className="products-header">

            <div>

              {isSearching ? (

                <>

                  <h1>
                    Search Results
                  </h1>

                  <p>
                    Showing results for "
                    {searchQuery}"
                  </p>

                </>

              ) : (

                <h1>
                  All Products
                </h1>

              )}

            </div>


            <span>
              {products.length} products
            </span>

          </div>


          {/* LOADING */}

          {loading ? (

            <div className="message-box">

              <h2>
                Loading...
              </h2>

            </div>


          ) : products.length === 0 ? (

            /* NO PRODUCTS */

            <div className="message-box">

              <div className="empty-icon">
                🔍
              </div>

              <h2>
                No products found
              </h2>

              <p>
                Try searching for another
                product or change your
                filters.
              </p>

              <button
                onClick={clearFilters}
              >
                View All Products
              </button>

            </div>


          ) : (

            /* PRODUCT GRID */

            <div className="product-grid">

              {products.map((product) => {

                const quantity =
                  quantities[product.id] || 1;


                return (

                  <div
                    className="product-card"
                    key={product.id}
                  >


                    {/* IMAGE */}

                    <div
                      className="product-image-container"
                      onClick={() =>
                        openProduct(product)
                      }
                    >

                      <img
                        src={product.image}
                        alt={product.name}
                        className="product-image"
                      />

                    </div>


                    {/* DETAILS */}

                    <div className="product-info">


                      <h2>
                        {product.name}
                      </h2>


                      <p className="product-description">
                        {product.description}
                      </p>


                      <div className="product-bottom">

                        <span className="product-price">

                          ₹
                          {Number(
                            product.price
                          ).toFixed(2)}

                        </span>

                      </div>


                      {/* QUANTITY */}

                      <div className="quantity-section">

                        <button
                          onClick={() =>
                            decreaseQuantity(
                              product.id
                            )
                          }
                        >
                          −
                        </button>


                        <span>
                          {quantity}
                        </span>


                        <button
                          onClick={() =>
                            increaseQuantity(
                              product.id
                            )
                          }
                        >
                          +
                        </button>

                      </div>


                      {/* ADD CART */}

                      <button
                        className="add-cart-button"
                        onClick={() =>
                          addToCart(product)
                        }
                      >
                        Add to Cart
                      </button>


                    </div>

                  </div>

                );

              })}

            </div>

          )}

        </main>

      </div>

    </div>

  );

}

export default Products;