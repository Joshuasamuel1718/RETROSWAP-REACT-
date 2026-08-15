import { useState } from 'react';
import api from '../services/api';
import './EditProfile.css';

function EditProfile({ user, onClose }) {

    const [username, setUsername] = useState(user.username);
    const [email, setEmail] = useState(user.email);
    const [phone, setPhone] = useState(user.phone);
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleSave = async () => {

        // Check password
        if (password !== confirmPassword) {
            alert("Passwords do not match");
            return;
        }

        const updatedUser = {
            username: username,
            phone: phone,
            password: password
        };

        try {

            // THIS SENDS TO BACKEND
            await api.put("user/profile", updatedUser);

            alert("Profile updated successfully");

            onClose();

        } catch (error) {

            console.log(error);
            alert("Failed to update profile");

        }
    };

    return (
        <div className="edit-overlay" onClick={onClose}>

            <div
                className="edit-profile-box"
                onClick={(e) => e.stopPropagation()}
            >

                <h2>Edit Profile</h2>

                <div className="edit-field">
                    <label>Username</label>

                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
<div className="edit-field">
    <label>Email</label>

    <input
        type="email"
        value={email}
        disabled
    />
</div>

                <div className="edit-field">
                    <label>Phone</label>

                    <input
                        type="text"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                    />
                </div>

                <div className="edit-field">
                    <label>Password</label>

                    <input
                        type="password"
                        placeholder="Enter new password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                <div className="edit-field">
                    <label>Confirm Password</label>

                    <input
                        type="password"
                        placeholder="Confirm new password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                </div>

                <div className="edit-buttons">

                    <button
                        className="cancel-btn"
                        onClick={onClose}
                    >
                        Cancel
                    </button>

                    <button
                        className="save-btn"
                        onClick={handleSave}
                    >
                        Save
                    </button>

                </div>

            </div>

        </div>
    );
}

export default EditProfile;