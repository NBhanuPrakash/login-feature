import React, { useState } from "react";
import axios from "axios";
import "./Home.css";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const [isSignUp, setIsSignUp] = useState(false);
  const [signInForm, setSignInForm] = useState({
    email: "",
    password: "",
  });
  const [signUpForm, setSignUpForm] = useState({
    fullName: "",
    email: "",
    password: "",
    phoneNumber: "",
  });

  const navigate = useNavigate();

  const toggleForm = () => {
    setIsSignUp(!isSignUp);
  };

  const handleSignInChange = (e) => {
    setSignInForm({
      ...signInForm,
      [e.target.name]: e.target.value,
    });
  };

  const handleSignUpChange = (e) => {
    setSignUpForm({
      ...signUpForm,
      [e.target.name]: e.target.value,
    });
  };

  const handleSignInSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/login", {
        userName: signInForm.email,
        password: signInForm.password,
      });
      //   console.log(response.data);

      localStorage.setItem("authToken", response.data);

      navigate("/users");
    } catch (error) {
      if (error.response && error.response.status === 401) {
        console.error("Unauthorized: Invalid email or password");
        alert("Invalid email or password. Please try again.");
      } else {
        console.error("Login failed:", error.response?.data || error.message);
        alert("Login failed. Please try again later.");
      }
    }
  };

  const handleSignUpSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/register", {
        userName: signUpForm.email,
        fullName: signUpForm.fullName,
        passward: signUpForm.password,
        phNumber: signUpForm.phoneNumber,
      });
      if (response.data != null) {
        setSignUpForm({
          fullName: "",
          email: "",
          password: "",
          phoneNumber: "",
        });
        alert("Succefully Registered...");
      }
      //   console.log(response.data);
    } catch (error) {
      console.error("Registration failed:", error.response.data);
      // Handle error
    }
  };

  return (
    <div className={`auth-container ${isSignUp ? "sign-up-mode" : ""}`}>
      <div className="form-container sign-in-container">
        <form onSubmit={handleSignInSubmit}>
          <h2>Sign In</h2>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              name="email"
              value={signInForm.email}
              onChange={handleSignInChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              name="password"
              value={signInForm.password}
              onChange={handleSignInChange}
              required
            />
          </div>
          <button type="submit" className="btn">
            Sign In
          </button>
          <p onClick={toggleForm}>Don't have an account? Sign Up</p>
        </form>
      </div>

      <div className="form-container sign-up-container">
        <form onSubmit={handleSignUpSubmit}>
          <h2>Sign Up</h2>
          <div className="form-group">
            <label>Full Name</label>
            <input
              type="text"
              name="fullName"
              value={signUpForm.fullName}
              onChange={handleSignUpChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              name="email"
              value={signUpForm.email}
              onChange={handleSignUpChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              name="password"
              value={signUpForm.password}
              onChange={handleSignUpChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Phone Number</label>
            <input
              type="text"
              name="phoneNumber"
              value={signUpForm.phoneNumber}
              onChange={handleSignUpChange}
              required
            />
          </div>

          <button type="submit" className="btn">
            Sign Up
          </button>
          <p onClick={toggleForm}>Already have an account? Sign In</p>
        </form>
      </div>
    </div>
  );
};

export default Home;
