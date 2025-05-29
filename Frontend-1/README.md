# React + Vite Event Planner

This project is a modern online event planner built with React and Vite. It provides a user-friendly interface for managing events, including features for user authentication, event creation, and management.

## Project Structure

- **public/**: Contains static assets like images and icons.
- **src/**: Contains the main application code.
  - **assets/**: Directory for animated assets (GIFs, SVGs).
  - **components/**: Reusable components for the application.
    - `WelcomePage.jsx`: The main landing page with animations and introduction.
    - `LoginForm.jsx`: Component for user login.
    - `SignupForm.jsx`: Component for user registration.
  - **pages/**: Page components for routing.
    - `Home.jsx`: Renders the `WelcomePage` and other features.
    - `Login.jsx`: Renders the `LoginForm`.
    - `Signup.jsx`: Renders the `SignupForm`.
  - `App.css`: Main CSS styles for the application.
  - `App.jsx`: Main application component that sets up routing.
  - `index.css`: Global CSS styles.
  - `main.jsx`: Entry point of the application.

## Features

- **User Authentication**: Users can log in or sign up to access event planning features.
- **Responsive Design**: The application is designed to work on various devices.
- **Animations**: Engaging animations enhance user experience.
- **Modern Tech Stack**: Built with React and Vite for fast development and performance.

## Getting Started

1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd Frontend
   ```

2. **Install dependencies**:
   ```
   npm install
   ```

3. **Run the application**:
   ```
   npm run dev
   ```

4. **Open your browser** and navigate to `http://localhost:3000` to see the application in action.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.