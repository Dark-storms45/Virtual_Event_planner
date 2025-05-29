import React from 'react';
import { Link } from 'react-router-dom';
import './WelcomePage.css'; // Assuming you will create a CSS file for styling

const WelcomePage = () => {
  return (
    <div className="welcome-container">
      <div className="welcome-header">
        <h1>Welcome to the Online Event Planner</h1>
        <p>Your one-stop solution for planning and managing events effortlessly!</p>
      </div>
      <div className="welcome-animation">
        <img src="/assets/animated-welcome.gif" alt="Welcome Animation" />
      </div>
      <div className="welcome-buttons">
        <Link to="/login" className="button login-button">Login</Link>
        <Link to="/signup" className="button signup-button">Sign Up</Link>
      </div>
    </div>
  );
};

export default WelcomePage;