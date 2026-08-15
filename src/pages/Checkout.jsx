import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Checkout.css";
import api from "../services/api";
import { useLocation} from "react-router-dom";


function Checkout() {

    const navigate = useNavigate();
    const location = useLocation();

const total = location.state?.total || 0;

    const [paymentOption, setPaymentOption] = useState("");

    // For now use your cart total here
   

 const handlePlaceOrder = async () => {

    if (!paymentOption) {
        alert("Please select a payment method");
        return;
    }

    try {

        await api.post(`/orders/${paymentOption}`);

        alert("Order placed successfully!");

        navigate("/orders");

    } catch (error) {

        console.log(error);
        alert("Failed to place order");

    }
};

    return (
        <div className="checkout-page">

            <div className="checkout-box">

                <h2>Checkout</h2>

                <div className="checkout-total">
                    <span>Total Amount</span>
                    <strong>₹{total}</strong>
                </div>

                <h3>Payment Method</h3>

                <label className="payment-option">
    <input
        type="radio"
        name="payment"
        value="1"
        checked={paymentOption === "1"}
        onChange={(e) => setPaymentOption(e.target.value)}
    />

    Cash on Delivery
</label>

<label className="payment-option">
    <input
        type="radio"
        name="payment"
        value="2"
        checked={paymentOption === "2"}
        onChange={(e) => setPaymentOption(e.target.value)}
    />

    Online Payment
</label>

                <button
                    className="place-order-btn"
                    onClick={handlePlaceOrder}
                >
                    Place Order
                </button>

            </div>

        </div>
    );
}

export default Checkout;