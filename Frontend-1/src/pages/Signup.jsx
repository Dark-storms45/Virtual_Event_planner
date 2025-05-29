import React from 'react';
import SignupForm from '../components/SignupForm';
import './Signup.css'; // Assuming you will create a CSS file for styling

const Signup = () => {
  return (
    <div className="signup-container">
      <div className="signup-header">
        <h1>Join Our Event Planner</h1>
        <p>Create an account to start planning your events effortlessly!</p>
      </div>
      <SignupForm />
      <div className="signup-footer">
        <p>Already have an account? <a href="/login">Login here</a></p>
      </div>
    </div>
  );
};

export default Signup;