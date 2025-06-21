# Virtual Event Planner Frontend

This project is the frontend component of the Virtual Event Planner application, built using JavaFX. It provides a user-friendly interface for planning and managing virtual events.

## Features

- **User Authentication**: Users can log in and sign up to access the platform.
- **Welcome Screen**: A welcoming interface that greets users after successful login or signup.
- **Responsive Design**: The application is designed to be visually appealing and user-friendly.

## Project Structure

- **src/main/java/com/eventplanner**: Contains the Java source files for the application.
  - **App.java**: The main entry point of the JavaFX application.
  - **controllers**: Contains the controllers for handling user interactions.
    - **LoginController.java**: Manages the login process.
    - **SignupController.java**: Handles user registration.
    - **WelcomeController.java**: Displays the welcome page.
  - **models**: Contains the data models for the application.
    - **User.java**: Represents a user in the application.

- **src/main/resources/css**: Contains the CSS files for styling the application.
  - **styles.css**: Defines the visual appearance of the UI components.

- **src/main/resources/fxml**: Contains the FXML files for the user interface layout.
  - **login.fxml**: Layout for the login page.
  - **signup.fxml**: Layout for the signup page.
  - **welcome.fxml**: Layout for the welcome page.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/virtual-event-planner.git
   cd virtual-event-planner/frontend
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn javafx:run
   ```

### Usage

- Launch the application and navigate through the login and signup pages.
- After logging in or signing up, you will be directed to the welcome page.

## Contribution

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License.