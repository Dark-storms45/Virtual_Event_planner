import React from 'react';
import LoginForm from '../components/LoginForm';
import './Login.css'; // Assuming you will create a CSS file for styling

const Login = () => {
  return (
    <div className="login-page">
      <div className="login-background">
        <img src="/path/to/your/animated-background.gif" alt="Animated Background" className="background-image" />
      </div>
      <div className="login-container">
        <h1>Welcome Back!</h1>
        <p>Please log in to your account</p>
        <LoginForm />
        <div className="signup-link">
          <p>Don't have an account? <a href="/signup">Sign up here</a></p>
        </div>
      </div>
    </div>
  );
};

export default Login;