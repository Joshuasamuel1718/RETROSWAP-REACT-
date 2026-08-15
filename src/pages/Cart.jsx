import { useEffect, useState } from 'react'
import api from '../services/api'
import './Cart.css'
import { useNavigate } from "react-router-dom";

function Cart() {

    const [cartItems, setCartItems] = useState([])
    const [loading, setLoading] = useState(true)
const navigate = useNavigate();

const handleCheckout = () => {
    navigate("/checkout");
};

    // =========================
    // GET CART
    // =========================

    const loadCart = async () => {

        try {

            const response = await api.get('/cart')

            console.log('Cart:', response.data)

            setCartItems(response.data)

        } catch (error) {

            console.error('Failed to load cart:', error)

        } finally {

            setLoading(false)

        }
    }


    useEffect(() => {

        loadCart()

    }, [])


    // =========================
    // UPDATE QUANTITY
    // =========================

   const updateQuantity = async (item, newQuantity) => {

    if (newQuantity < 1) {
        return
    }

    try {

        const request = {
            cartItemId: item.id,
            quantity: newQuantity
        }

        await api.put('/cart', request)

        loadCart()

    } catch (error) {

        console.error('Failed to update quantity:', error)

    }
}

    // =========================
    // DELETE ITEM
    // =========================

    const removeItem = async (id) => {

        try {

            await api.delete(`/cart/${id}`)

            setCartItems(
                cartItems.filter(item => item.id !== id)
            )

        } catch (error) {

            console.error('Failed to remove item:', error)

        }
    }


    // =========================
    // TOTAL
    // =========================

    const totalPrice = cartItems.reduce(
        (total, item) =>
            total +
            item.product.price * item.quantity,
        0
    )


    if (loading) {
        return <p className="cart-loading">Loading cart...</p>
    }


    return (

        <div className="cart-page">

            <h1>My Cart</h1>


            {cartItems.length === 0 ? (

                <div className="empty-cart">

                    <h2>Your cart is empty</h2>

                    <p>
                        Add some products to your cart.
                    </p>

                </div>

            ) : (

                <div className="cart-container">


                    {/* =========================
                        CART ITEMS
                    ========================= */}

                    <div className="cart-items">

                        {cartItems.map(item => (

                            <div
                                className="cart-item"
                                key={item.id}
                            >

                                <img
                                    src={item.product.image}
                                    alt={item.product.name}
                                />


                                <div className="cart-item-details">

                                    <h2>
                                        {item.product.name}
                                    </h2>

                                    <p>
                                        ₹{item.product.price}
                                    </p>


                                    <div className="quantity-control">

                                        <button
                                            onClick={() =>
                                                updateQuantity(
                                                    item,
                                                    item.quantity - 1
                                                )
                                            }
                                        >
                                            −
                                        </button>

                                        <span>
                                            {item.quantity}
                                        </span>

                                        <button
                                            onClick={() =>
                                                updateQuantity(
                                                    item,
                                                    item.quantity + 1
                                                )
                                            }
                                        >
                                            +
                                        </button>

                                    </div>


                                    <button
                                        className="remove-button"
                                        onClick={() =>
                                            removeItem(item.id)
                                        }
                                    >
                                        Remove
                                    </button>

                                </div>


                                <strong className="item-total">

                                    ₹
                                    {(
                                        item.product.price *
                                        item.quantity
                                    ).toFixed(2)}

                                </strong>

                            </div>

                        ))}

                    </div>


                    {/* =========================
                        CART SUMMARY
                    ========================= */}

                    <div className="cart-summary">

                        <h2>Order Summary</h2>

                        <div className="summary-row">

                            <span>Items</span>

                            <span>
                                {cartItems.length}
                            </span>

                        </div>


                        <div className="summary-row total">

                            <span>Total</span>

                            <span>
                                ₹{totalPrice.toFixed(2)}
                            </span>

                        </div>


                        <button className="checkout-button" onClick={() => navigate("/checkout", {
    state: {
        total: totalPrice.toFixed(2)
    }
})}>
                            CHECKOUT
                        </button>

                    </div>

                </div>

            )}

        </div>

    )
}

export default Cart