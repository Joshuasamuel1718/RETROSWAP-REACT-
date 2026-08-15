import { useEffect, useState } from "react";
import api from "../services/api";
import "./Orders.css";

function Orders() {

    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    const loadOrders = async () => {

        try {

            const response = await api.get("/orders");

            console.log("Orders:", response.data);

            setOrders(response.data);

        } catch (error) {

            console.error("Failed to load orders:", error);

        } finally {

            setLoading(false);

        }
    };

    useEffect(() => {
        loadOrders();
    }, []);

    if (loading) {
        return <p>Loading orders...</p>;
    }

    return (
        <div className="orders-page">

            <h1>My Orders</h1>

            {orders.length === 0 ? (

                <div className="empty-orders">
                    <h2>No orders yet</h2>
                    <p>Your placed orders will appear here.</p>
                </div>

            ) : (

                <div className="orders-container">

                    {orders.map((order) => (

                        <div className="order-card" key={order.id}>

                            <div className="order-header">

                                <div>
                                    <span>Order ID</span>
                                    <strong>#{order.id}</strong>
                                </div>

                                <div>
                                    <span>Date</span>
                                    <strong>
                                        {new Date(order.orderDate).toLocaleDateString()}
                                    </strong>
                                </div>

                            </div>

                            <div className="order-details">

                                <div>
                                    <span>Total Amount</span>
                                    <strong>
                                        ₹{order.amount}
                                    </strong>
                                </div>

                                <div>
                                    <span>Payment</span>
                                    <strong>
                                        {order.paymentoption === 1
                                            ? "Cash on Delivery"
                                            : "Online Payment"}
                                    </strong>
                                </div>

                                <div>
                                    <span>Status</span>
                                    <strong className="order-status">
                                        {order.status ? "Delivered" : "Placed"}
                                    </strong>
                                </div>

                            </div>

                        </div>

                    ))}

                </div>

            )}

        </div>
    );
}

export default Orders;