import React from 'react';
import { Link } from 'react-router-dom';

// Professional white event icon (calendar with check), enlarged
const HeaderIcon = (
  <svg width="100" height="100" viewBox="0 0 24 24" fill="none">
    <rect x="3" y="5" width="18" height="16" rx="3" fill="#6a11cb" opacity="0.10"/>
    <rect x="3" y="5" width="18" height="16" rx="3" stroke="#6a11cb" strokeWidth="2"/>
    <path d="M16 3v4M8 3v4" stroke="#6a11cb" strokeWidth="2" strokeLinecap="round"/>
    <line x1="3" y1="9" x2="21" y2="9" stroke="#6a11cb" strokeWidth="2"/>
    <polyline points="9,15 11,17 15,13" fill="none" stroke="#43e97b" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
  </svg>
);

// More diverse event/auction images
const events = [
  {
    id: 1,
    title: "Tech Innovators Conference",
    type: "Event",
    date: "2025-06-10",
    image: "https://images.unsplash.com/photo-1464983953574-0892a716854b?auto=format&fit=crop&w=600&q=80",
    description: "Join industry leaders and innovators for a day of networking and inspiration. Keynotes, workshops, and networking sessions await you.",
    location: "San Francisco, CA",
    organizer: "TechWorld Inc."
  },
  {
    id: 2,
    title: "Charity Art Auction",
    type: "Auction",
    date: "2025-07-01",
    image: "https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?auto=format&fit=crop&w=600&q=80",
    description: "Bid on exclusive art pieces and support a great cause. All proceeds go to local charities.",
    location: "New York, NY",
    organizer: "Art4Good Foundation"
  },
  {
    id: 3,
    title: "Startup Pitch Night",
    type: "Event",
    date: "2025-08-15",
    image: "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=600&q=80",
    description: "Watch startups pitch their ideas to investors and win prizes. Network with founders and VCs.",
    location: "Online",
    organizer: "StartupHub"
  },
  {
    id: 4,
    title: "Vintage Car Auction",
    type: "Auction",
    date: "2025-09-05",
    image: "https://images.unsplash.com/photo-1519125323398-675f0ddb6308?auto=format&fit=crop&w=600&q=80",
    description: "A rare collection of vintage cars up for grabs. Don't miss your chance to own a classic.",
    location: "Monaco",
    organizer: "Classic Motors"
  },
  {
    id: 5,
    title: "Global Health Summit",
    type: "Event",
    date: "2025-10-12",
    image: "https://images.unsplash.com/photo-1465101046530-73398c7f28ca?auto=format&fit=crop&w=600&q=80",
    description: "A summit bringing together health professionals from around the world to discuss the future of healthcare.",
    location: "Geneva, Switzerland",
    organizer: "World Health Org"
  },
  {
    id: 6,
    title: "Luxury Watch Auction",
    type: "Auction",
    date: "2025-11-20",
    image: "https://images.unsplash.com/photo-1516574187841-cb9cc2ca948b?auto=format&fit=crop&w=600&q=80",
    description: "Bid on rare and luxury watches from top brands. Exclusive event for collectors.",
    location: "London, UK",
    organizer: "Timepiece Auctions"
  },
  {
    id: 7,
    title: "Gourmet Food Festival",
    type: "Event",
    date: "2025-12-05",
    image: "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=600&q=80",
    description: "Taste the best from world-renowned chefs and local artisans.",
    location: "Paris, France",
    organizer: "Foodies United"
  },
  {
    id: 8,
    title: "Modern Art Auction",
    type: "Auction",
    date: "2026-01-15",
    image: "https://images.unsplash.com/photo-1465101178521-c1a9136a3b99?auto=format&fit=crop&w=600&q=80",
    description: "Acquire unique pieces from emerging and established artists.",
    location: "Berlin, Germany",
    organizer: "ArtHouse Europe"
  }
];

function Home() {
  return (
    <div style={outerStyle}>
      {/* Hero Section */}
      <section style={heroSectionStyle}>
        <div style={heroImageContainerStyle}>
          <img
            src="https://images.unsplash.com/photo-1504384308090-c894fdcc538d?auto=format&fit=crop&w=1200&q=80"
            alt="Welcome Event"
            style={heroImageStyle}
          />
          <div style={heroOverlayStyle}></div>
        </div>
        <div style={heroContentStyle} className="fade-in-down">
          <div style={headerIconStyle}>{HeaderIcon}</div>
          <h1 style={heroTitleStyle}>Welcome to Virtual Event Planner</h1>
          <p style={heroSubtitleStyle}>
            <strong>Discover, connect, and experience the best events and auctions worldwide.</strong>
            <br />
            <span style={{ color: "#6a11cb", fontWeight: 600 }}>Your journey to unforgettable moments starts here.</span>
          </p>
          <div style={headerButtonGroupStyle}>
            <Link to="/login" style={buttonStyle} className="fade-in-left">Login</Link>
            <Link to="/signup" style={buttonStyle} className="fade-in-right">Sign Up</Link>
          </div>
        </div>
      </section>

      {/* Why Choose Us Section */}
      <section style={whySectionStyle}>
        <h2 style={sectionTitleStyle} className="fade-in-up">Why Choose Virtual Event Planner?</h2>
        <div style={whyGridStyle}>
          <div style={whyCardStyle} className="fade-in-up">
            <span style={{ ...whyIconStyle, fontSize: '3rem' }}>🌐</span>
            <h3 style={whyCardTitleStyle}>Global Access</h3>
            <p style={whyCardDescStyle}>Join events and auctions from anywhere in the world, anytime.</p>
          </div>
          <div style={whyCardStyle} className="fade-in-up">
            <span style={{ ...whyIconStyle, fontSize: '3rem' }}>🔒</span>
            <h3 style={whyCardTitleStyle}>Secure & Private</h3>
            <p style={whyCardDescStyle}>Your data is protected with industry-leading security and privacy standards.</p>
          </div>
          <div style={whyCardStyle} className="fade-in-up">
            <span style={{ ...whyIconStyle, fontSize: '3rem' }}>⚡</span>
            <h3 style={whyCardTitleStyle}>Fast & Easy</h3>
            <p style={whyCardDescStyle}>Create, join, and manage events or auctions in just a few clicks.</p>
          </div>
          <div style={whyCardStyle} className="fade-in-up">
            <span style={{ ...whyIconStyle, fontSize: '3rem' }}>🤝</span>
            <h3 style={whyCardTitleStyle}>Community</h3>
            <p style={whyCardDescStyle}>Connect with like-minded people and grow your network.</p>
          </div>
        </div>
      </section>

      {/* Events & Auctions Section */}
      <section style={eventsSectionStyle}>
        <h2 style={sectionTitleStyle} className="fade-in-up">Upcoming Events & Auctions</h2>
        <div style={eventsGridStyle}>
          {events.map((event, idx) => (
            <div
              key={event.id}
              className="fade-in-up"
              style={{ ...eventCardStyle, animationDelay: `${0.2 + idx * 0.15}s` }}
            >
              <img src={event.image} alt={event.title} style={eventImageStyle} />
              <div style={eventInfoStyle}>
                <span style={eventTypeStyle(event.type)}>{event.type}</span>
                <h3 style={eventTitleStyle}>{event.title}</h3>
                <p style={eventDescStyle}>{event.description}</p>
                <div style={eventMetaStyle}>
                  <span style={eventMetaItemStyle}><b>📍</b> {event.location}</span>
                  <span style={eventMetaItemStyle}><b>🗓️</b> {new Date(event.date).toLocaleDateString()}</span>
                </div>
                <span style={eventOrganizerStyle}>By {event.organizer}</span>
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
              <li>Event Creation</li>
              <li>Live Chat</li>
              <li>Support Center</li>
            </ul>
          </div>
          <div>
            <h4>FAQ</h4>
            <ul style={footerListStyle}>
              <li>How do I create an event?</li>
              <li>How do I join an auction?</li>
              <li>How do I contact support?</li>
              <li>Can I invite friends?</li>
              <li>How do I manage my profile?</li>
            </ul>
          </div>
          <div>
            <h4>Contact & Social</h4>
            <ul style={footerListStyle}>
              <li>Email: support@virtualeventplanner.com</li>
              <li>Twitter: @VirtualEventPlanner</li>
              <li>LinkedIn: Virtual Event Planner</li>
              <li>Instagram: @veplanner</li>
            </ul>
          </div>
        </div>
        <div style={footerBottomStyle}>
          <span>© {new Date().getFullYear()} Virtual Event Planner Inc. | Crafted with ❤️ for your success</span>
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
  background: 'white',
  backgroundImage: 'radial-gradient(circle at 0 100%, #e0e7ff 0%, transparent 60%), radial-gradient(circle at 100% 0, #e0e7ff 0%, transparent 60%)',
  color: '#222',
  fontFamily: 'Segoe UI, sans-serif',
  boxSizing: 'border-box',
  padding: 0,
  margin: 0,
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center'
};

// Hero Section
const heroSectionStyle = {
  width: '100%',
  minHeight: 420,
  position: 'relative',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  marginBottom: 32
};

const heroImageContainerStyle = {
  position: 'absolute',
  top: 0,
  left: 0,
  width: '100%',
  height: '100%',
  zIndex: 0,
  overflow: 'hidden'
};

const heroImageStyle = {
  width: '100%',
  height: 420,
  objectFit: 'cover',
  filter: 'brightness(0.7) blur(1px)'
};

const heroOverlayStyle = {
  position: 'absolute',
  top: 0,
  left: 0,
  width: '100%',
  height: '100%',
  background: 'linear-gradient(135deg, rgba(106,17,203,0.18) 0%, rgba(37,117,252,0.18) 100%)',
  zIndex: 1
};

const heroContentStyle = {
  position: 'relative',
  zIndex: 2,
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent: 'center',
  width: '100%',
  maxWidth: 900,
  padding: '48px 16px 32px 16px',
  textAlign: 'center'
};

const heroTitleStyle = {
  fontWeight: 900,
  fontSize: '2.7rem',
  letterSpacing: '1.5px',
  margin: '18px 0 12px 0',
  textShadow: '0 2px 8px rgba(106,17,203,0.08)'
};

const heroSubtitleStyle = {
  fontSize: '1.25rem',
  fontWeight: 400,
  lineHeight: 1.6,
  marginBottom: 28,
  color: '#444'
};

const headerIconStyle = {
  marginBottom: 10,
  background: 'rgba(106,17,203,0.08)',
  borderRadius: '50%',
  padding: 24,
  boxShadow: '0 4px 24px 0 rgba(106,17,203,0.12)'
};

const headerButtonGroupStyle = {
  display: 'flex',
  gap: '18px',
  marginTop: 18,
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

// Why Choose Us Section
const whySectionStyle = {
  width: '100%',
  maxWidth: 1100,
  margin: '0 auto 48px auto',
  padding: '32px 0 0 0',
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center'
};

const whyGridStyle = {
  display: 'grid',
  gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))',
  gap: '28px',
  width: '100%',
  marginTop: 24
};

const whyCardStyle = {
  background: 'rgba(106,17,203,0.07)',
  borderRadius: '14px',
  boxShadow: '0 4px 24px 0 rgba(106,17,203,0.10)',
  padding: '32px 18px',
  textAlign: 'center',
  color: '#222'
};

const whyIconStyle = {
  fontSize: '2.2rem',
  marginBottom: 10,
  display: 'block'
};

const whyCardTitleStyle = {
  fontWeight: 700,
  fontSize: '1.15rem',
  margin: '0 0 8px 0'
};

const whyCardDescStyle = {
  fontSize: '1rem',
  opacity: 0.92
};

// Events & Auctions Section
const eventsSectionStyle = {
  width: '100%',
  maxWidth: 1200,
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
  maxWidth: 1100
};

const eventCardStyle = {
  background: 'rgba(106,17,203,0.06)',
  borderRadius: '14px',
  boxShadow: '0 4px 24px 0 rgba(106,17,203,0.10)',
  overflow: 'hidden',
  display: 'flex',
  flexDirection: 'column',
  minHeight: 420,
  animation: 'fadeInUp 1.2s cubic-bezier(.39,.575,.565,1.000) both'
};

const eventImageStyle = {
  width: '100%',
  height: 180,
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
  margin: '0 0 12px 0',
  flex: 1
};

const eventMetaStyle = {
  display: 'flex',
  gap: 12,
  fontSize: '0.98rem',
  marginBottom: 8,
  color: '#6a11cb'
};

const eventMetaItemStyle = {
  display: 'flex',
  alignItems: 'center',
  gap: 4
};

const eventOrganizerStyle = {
  fontSize: '0.95rem',
  color: '#6a11cb',
  opacity: 0.8,
  marginTop: 4
};

// Footer
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