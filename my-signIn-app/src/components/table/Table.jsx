import React, { useState, useEffect } from "react";
import axios from "axios";
import "./table.css";

const Table = () => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const token = localStorage.getItem("authToken");

        if (!token) {
          throw new Error("No authentication token found");
        }

        const response = await axios.get("http://localhost:8080/user", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        setUser(response.data);

        // console.log("Response data:", response.data);
      } catch (error) {
        console.error(
          "Error fetching user:",
          error.response?.data || error.message
        );
        setError(error.response?.data || error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, []);

  return (
    <div className="user-table-container">
      <h2>User Details</h2>
      {loading && <p>Loading...</p>}
      {error && <p className="error">Error: {error}</p>}
      {!loading && !error && user && (
        <table className="user-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Full Name</th>
              <th>Email</th>
              <th>Phone Number</th>
              <th>Role</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.fullName}</td>
              <td>{user.userName}</td>
              <td>{user.phNumber}</td>
              <td>{user.role}</td>
              <td>
                {user.date ? new Date(user.date).toLocaleDateString() : "N/A"}
              </td>
            </tr>
          </tbody>
        </table>
      )}
    </div>
  );
};

export default Table;
