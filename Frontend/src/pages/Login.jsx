import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const LoginIcon = (
  <svg width="48" height="48" viewBox="0 0 24 24" fill="none">
    <circle cx="12" cy="12" r="12" fill="white" opacity="0.10"/>
    <path d="M12 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm0 2c-3.31 0-6 2.01-6 4.5V20h12v-1.5c0-2.49-2.69-4.5-6-4.5z" fill="white"/>
    <path d="M12 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm0 2c-3.31 0-6 2.01-6 4.5V20h12v-1.5c0-2.49-2.69-4.5-6-4.5z" fill="#43e97b" fillOpacity="0.3"/>
  </svg>
);

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add login logic here
    alert(`Logging in with\nEmail: ${email}\nPassword: ${password}`);
  };

  return (
    <div style={outerStyle}>
      <div style={iconContainerStyle} className="fade-in-down">
        {LoginIcon}
      </div>
      <div style={cardStyle} className="fade-in-up">
        <h2 style={titleStyle}>Welcome Back!</h2>
        <p style={descStyle}>Sign in to your account to manage your events and auctions.</p>
        <form onSubmit={handleSubmit} style={formStyle}>
          <div style={inputGroupStyle}>
            <label style={labelStyle}>Email</label>
            <input
              type="email"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
              style={inputStyle}
              placeholder="Enter your email"
              className="fade-in-left"
            />
          </div>
          <div style={inputGroupStyle}>
            <label style={labelStyle}>Password</label>
            <div style={{ position: 'relative' }}>
              <input
                type={showPassword ? "text" : "password"}
                value={password}
                onChange={e => setPassword(e.target.value)}
                required
                style={inputStyle}
                placeholder="Enter your password"
                className="fade-in-right"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                style={showPasswordBtnStyle}
                tabIndex={-1}
                aria-label="Show password"
              >
                {/* {showPassword ? "🙈" : "👁️"} */}
              </button>
            </div>
          </div>
          <button type="submit" style={buttonStyle} className="fade-in-up">Login</button>
        </form>
        <div style={optionsStyle}>
          <Link to="/signup" style={linkStyle}>Don't have an account? Sign Up</Link>
          <Link to="#" style={linkStyle}>Forgot Password?</Link>
        </div>
        <div style={dividerStyle}><span>or</span></div>
        <button style={socialBtnStyle} className="fade-in-up">
          <span style={{ marginRight: 8 }}>🔒</span> Login with Google
        </button>
      </div>
      <style>
        {`
          .fade-in-down {
            animation: fadeInDown 1s cubic-bezier(.39,.575,.565,1.000) both;
          }
          .fade-in-up {
            animation: fadeInUp 1.2s cubic-bezier(.39,.575,.565,1.000) both;
          }
          .fade-in-left {
            animation: fadeInLeft 1.4s cubic-bezier(.39,.575,.565,1.000) both;
          }
          .fade-in-right {
            animation: fadeInRight 1.6s cubic-bezier(.39,.575,.565,1.000) both;
          }
          @keyframes fadeInDown {
            0% { opacity: 0; transform: translateY(-40px);}
            100% { opacity: 1; transform: translateY(0);}
          }
          @keyframes fadeInUp {
            0% { opacity: 0; transform: translateY(40px);}
            100% { opacity: 1; transform: translateY(0);}
          }
          @keyframes fadeInLeft {
            0% { opacity: 0; transform: translateX(-40px);}
            100% { opacity: 1; transform: translateX(0);}
          }
          @keyframes fadeInRight {
            0% { opacity: 0; transform: translateX(40px);}
            100% { opacity: 1; transform: translateX(0);}
          }
        `}
      </style>
    </div>
  );
}

// Styles
const outerStyle = {
  minHeight: '100vh',
  minWidth: '100vw',
  background: 'linear-gradient(135deg, #6a11cb 0%, #2575fc 100%)',
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent: 'center',
  color: '#fff',
  fontFamily: 'Segoe UI, sans-serif',
  boxSizing: 'border-box',
  padding: 0,
  margin: 0,
  position: 'relative'
};

const iconContainerStyle = {
  position: 'absolute',
  top: 40,
  left: '50%',
  transform: 'translateX(-50%)',
  zIndex: 2,
  background: 'rgba(255,255,255,0.05)',
  borderRadius: '50%',
  padding: 16,
  boxShadow: '0 4px 24px 0 rgba(31, 38, 135, 0.12)'
};

const cardStyle = {
  background: 'rgba(0,0,0,0.7)',
  padding: '48px 30px 40px 30px',
  borderRadius: '16px',
  boxShadow: '0 8px 32px 0 rgba(31, 38, 135, 0.37)',
  textAlign: 'center',
  maxWidth: '95vw',
  width: '100%',
  minWidth: '280px',
  marginTop: 100,
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center'
};

const titleStyle = {
  marginBottom: '12px',
  fontWeight: 700,
  letterSpacing: '1px',
  fontSize: '2rem'
};

const descStyle = {
  marginBottom: '28px',
  fontSize: '1.1rem',
  color: '#e0e0e0'
};

const formStyle = {
  width: '100%',
  maxWidth: 350,
  display: 'flex',
  flexDirection: 'column',
  gap: '18px',
  margin: '0 auto'
};

const inputGroupStyle = {
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'flex-start'
};

const labelStyle = {
  marginBottom: 6,
  fontWeight: 500,
  fontSize: '1rem'
};

const inputStyle = {
  width: '100%',
  padding: '12px',
  borderRadius: '7px',
  border: 'none',
  outline: 'none',
  fontSize: '1rem',
  marginBottom: 0,
  background: 'rgba(255,255,255,0.13)',
  color: '#fff',
  boxShadow: '0 1px 4px rgba(0,0,0,0.06)'
};

const showPasswordBtnStyle = {
  position: 'absolute',
  right: 10,
  top: 10,
  background: 'none',
  border: 'none',
  color: '#43e97b',
  fontSize: '1.3rem',
  cursor: 'pointer',
  padding: '0 4px',
};

const buttonStyle = {
  background: 'linear-gradient(90deg, #43e97b 0%, #38f9d7 100%)',
  color: '#222',
  padding: '14px 0',
  borderRadius: '8px',
  textDecoration: 'none',
  fontWeight: 600,
  fontSize: '1.1rem',
  transition: 'background 0.3s, color 0.3s, transform 0.2s',
  boxShadow: '0 2px 8px rgba(0,0,0,0.08)',
  width: '100%',
  border: 'none',
  marginTop: 10,
  cursor: 'pointer'
};

const optionsStyle = {
  marginTop: 18,
  display: 'flex',
  flexDirection: 'column',
  gap: 6
};

const linkStyle = {
  color: '#43e97b',
  textDecoration: 'none',
  fontSize: '0.98rem',
  transition: 'color 0.2s'
};

const dividerStyle = {
  margin: '24px 0 12px 0',
  width: '100%',
  textAlign: 'center',
  borderBottom: '1px solid rgba(255,255,255,0.13)',
  lineHeight: '0.1em'
};

const socialBtnStyle = {
  background: 'linear-gradient(90deg, #fff 0%, #e0e0e0 100%)',
  color: '#222',
  padding: '12px 0',
  borderRadius: '8px',
  fontWeight: 600,
  fontSize: '1rem',
  width: '100%',
  border: 'none',
  cursor: 'pointer',
  marginTop: 0,
  boxShadow: '0 2px 8px rgba(0,0,0,0.08)'
};

export default Login;