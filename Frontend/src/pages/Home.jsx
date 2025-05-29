import React from 'react';
import { Link } from 'react-router-dom';

// Professional white event icon (calendar with check)
const HeaderIcon = (
  <svg width="48" height="48" viewBox="0 0 24 24" fill="none">
    <rect x="3" y="5" width="18" height="16" rx="3" fill="white" opacity="0.10"/>
    <rect x="3" y="5" width="18" height="16" rx="3" stroke="white" strokeWidth="2"/>
    <path d="M16 3v4M8 3v4" stroke="white" strokeWidth="2" strokeLinecap="round"/>
    <line x1="3" y1="9" x2="21" y2="9" stroke="white" strokeWidth="2"/>
    <polyline points="9,15 11,17 15,13" fill="none" stroke="#43e97b" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
  </svg>
);

// Dummy events/auctions data
const events = [
  {
    id: 1,
    title: "Tech Innovators Conference",
    type: "Event",
    date: "2025-06-10",
    image: "https://images.unsplash.com/photo-1464983953574-0892a716854b?auto=format&fit=crop&w=600&q=80",
    description: "Join industry leaders and innovators for a day of networking and inspiration."
  },
  {
    id: 2,
    title: "Charity Art Auction",
    type: "Auction",
    date: "2025-07-01",
    image: "https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?auto=format&fit=crop&w=600&q=80",
    description: "Bid on exclusive art pieces and support a great cause."
  },
  {
    id: 3,
    title: "Startup Pitch Night",
    type: "Event",
    date: "2025-08-15",
    image: "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=600&q=80",
    description: "Watch startups pitch their ideas to investors and win prizes."
  },
  {
    id: 4,
    title: "Vintage Car Auction",
    type: "Auction",
    date: "2025-09-05",
    image: "https://images.unsplash.com/photo-1519125323398-675f0ddb6308?auto=format&fit=crop&w=600&q=80",
    description: "A rare collection of vintage cars up for grabs."
  }
];

function Home() {
  return (
    <div style={outerStyle}>
      {/* Header */}
      <header style={headerStyle} className="fade-in-down">
        <div style={headerIconStyle}>{HeaderIcon}</div>
        <div>
          <h1 style={headerTitleStyle}>Virtual Event Planner</h1>
          <p style={headerSubtitleStyle}>
            Welcome! Effortlessly organize, manage, and enjoy your events and auctions.<br />
            <span style={{ fontWeight: 500 }}>Get started by logging in or creating a new account.</span>
          </p>
          <div style={headerButtonGroupStyle}>
            <Link to="/login" style={buttonStyle} className="fade-in-left">Login</Link>
            <Link to="/signup" style={buttonStyle} className="fade-in-right">Sign Up</Link>
          </div>
        </div>
      </header>

      {/* Events & Auctions Section */}
      <section style={eventsSectionStyle}>
        <h2 style={sectionTitleStyle} className="fade-in-up">Upcoming Events & Auctions</h2>
        <div style={eventsGridStyle}>
          {events.map((event, idx) => (
            <div key={event.id}  className="fade-in-up" style={{...eventCardStyle, animationDelay: `${0.2 + idx * 0.15}s`}}>
              <img src={event.image} alt={event.title} style={eventImageStyle} />
              <div style={eventInfoStyle}>
                <span style={eventTypeStyle(event.type)}>{event.type}</span>
                <h3 style={eventTitleStyle}>{event.title}</h3>
                <p style={eventDescStyle}>{event.description}</p>
                <span style={eventDateStyle}>{new Date(event.date).toLocaleDateString()}</span>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* Footer */}
      <footer style={footerStyle}>
        <div style={footerModulesStyle}>
          <div>
            <h4>Available After Login</h4>
            <ul style={footerListStyle}>
              <li>My Events</li>
              <li>My Auctions</li>
              <li>Profile</li>
              <li>Notifications</li>
              <li>Settings</li>
            </ul>
          </div>
          <div>
            <h4>FAQ</h4>
            <ul style={footerListStyle}>
              <li>How do I create an event?</li>
              <li>How do I join an auction?</li>
              <li>How do I contact support?</li>
            </ul>
          </div>
        </div>
        <div style={footerBottomStyle}>
          <span>© {new Date().getFullYear()} Virtual Event Planner Inc.</span>
        </div>
      </footer>

      {/* Animation styles */}
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
  color: '#fff',
  fontFamily: 'Segoe UI, sans-serif',
  boxSizing: 'border-box',
  padding: 0,
  margin: 0,
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center'
};

const headerStyle = {
  width: '100%',
  maxWidth: 900,
  margin: '0 auto',
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  paddingTop: 48,
  paddingBottom: 32,
  position: 'relative'
};

const headerIconStyle = {
  marginBottom: 12,
  background: 'rgba(255,255,255,0.08)',
  borderRadius: '50%',
  padding: 18,
  boxShadow: '0 4px 24px 0 rgba(31, 38, 135, 0.12)'
};

const headerTitleStyle = {
  fontWeight: 800,
  fontSize: '2.4rem',
  letterSpacing: '1px',
  margin: 0,
  textShadow: '0 2px 8px rgba(0,0,0,0.08)'
};

const headerSubtitleStyle = {
  margin: '18px 0 0 0',
  fontSize: '1.15rem',
  fontWeight: 400,
  lineHeight: 1.6,
  textAlign: 'center'
};

const headerButtonGroupStyle = {
  display: 'flex',
  gap: '18px',
  marginTop: 28,
  justifyContent: 'center'
};

const buttonStyle = {
  background: 'linear-gradient(90deg, #43e97b 0%, #38f9d7 100%)',
  color: '#222',
  padding: '14px 36px',
  borderRadius: '8px',
  textDecoration: 'none',
  fontWeight: 600,
  fontSize: '1.1rem',
  transition: 'background 0.3s, color 0.3s, transform 0.2s',
  boxShadow: '0 2px 8px rgba(0,0,0,0.08)',
  border: 'none',
  outline: 'none'
};

const eventsSectionStyle = {
  width: '100%',
  maxWidth: 1100,
  margin: '0 auto',
  padding: '32px 0 64px 0',
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center'
};

const sectionTitleStyle = {
  fontSize: '2rem',
  fontWeight: 700,
  marginBottom: 32,
  letterSpacing: '0.5px'
};

const eventsGridStyle = {
  display: 'grid',
  gridTemplateColumns: 'repeat(auto-fit, minmax(270px, 1fr))',
  gap: '32px',
  width: '100%',
  maxWidth: 1000
};

const eventCardStyle = {
  background: 'rgba(255,255,255,0.07)',
  borderRadius: '14px',
  boxShadow: '0 4px 24px 0 rgba(31, 38, 135, 0.10)',
  overflow: 'hidden',
  display: 'flex',
  flexDirection: 'column',
  minHeight: 380,
  animation: 'fadeInUp 1.2s cubic-bezier(.39,.575,.565,1.000) both'
};

const eventImageStyle = {
  width: '100%',
  height: 160,
  objectFit: 'cover'
};

const eventInfoStyle = {
  padding: '20px 18px 16px 18px',
  display: 'flex',
  flexDirection: 'column',
  flex: 1
};

const eventTypeStyle = (type) => ({
  alignSelf: 'flex-start',
  background: type === 'Auction'
    ? 'linear-gradient(90deg, #ffb347 0%, #ffcc33 100%)'
    : 'linear-gradient(90deg, #43e97b 0%, #38f9d7 100%)',
  color: '#222',
  fontWeight: 700,
  fontSize: '0.95rem',
  borderRadius: '6px',
  padding: '3px 12px',
  marginBottom: 8
});

const eventTitleStyle = {
  fontWeight: 700,
  fontSize: '1.2rem',
  margin: '0 0 8px 0'
};

const eventDescStyle = {
  fontSize: '1rem',
  margin: '0 0 18px 0',
  flex: 1
};

const eventDateStyle = {
  fontSize: '0.95rem',
  color: '#b2f7ef',
  fontWeight: 500,
  marginTop: 'auto'
};

const footerStyle = {
  width: '100%',
  background: 'rgba(10, 10, 30, 0.98)',
  color: '#fff',
  padding: '48px 0 0 0',
  marginTop: 64
};

const footerModulesStyle = {
  display: 'flex',
  flexWrap: 'wrap',
  justifyContent: 'center',
  gap: '64px',
  padding: '0 0 32px 0'
};

const footerListStyle = {
  listStyle: 'none',
  padding: 0,
  margin: '12px 0 0 0',
  fontSize: '1rem',
  opacity: 0.85
};

const footerBottomStyle = {
  borderTop: '1px solid rgba(255,255,255,0.08)',
  textAlign: 'center',
  padding: '18px 0',
  fontSize: '1rem',
  opacity: 0.7
};

export default Home;