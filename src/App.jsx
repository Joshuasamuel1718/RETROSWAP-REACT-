import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Navbar from "./components/Navbar";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Profile from "./pages/Profile";
import Cart from "./pages/Cart";
import AddProduct from "./pages/AddProduct";
import Orders from "./pages/Orders";
import Register from './pages/Register'
import ProductDetails from './pages/ProductDetails'
import Checkout from "./pages/Checkout";
import Products from "./pages/products";
function App() {
  return (
    <BrowserRouter>

      
      <Navbar />

      <Routes>

      
        <Route
          path="/"
          element={<Navigate to="/home" replace />}
        />

        <Route
          path="/Home"
          element={<Home />}
        />
<Route path="/product/:id"
 element={<ProductDetails />} />
<Route path="/checkout" element={<Checkout />} />
        <Route
          path="/login"
          element={<Login />}
        />
    <Route
        path="/register"
        element={<Register />}
    />
        <Route
          path="/profile"
          element={<Profile />}
        />
         <Route
          path="/products"
          element={<Products />}
        />

        <Route
          path="/cart"
          element={<Cart />}
        />

        <Route
          path="/add-product"
          element={<AddProduct />}
        />

        <Route
          path="/orders"
          element={<Orders />}
        />

      </Routes>

    </BrowserRouter>
  );
}

export default App;