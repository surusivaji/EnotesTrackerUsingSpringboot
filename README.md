<h2 align="center">📝 ENOTES APPLICATION USING SPRINGBOOT</h2>

<p>
  <strong>ENotes Application</strong> is a feature-rich note-taking and user management system built using 
  <strong>Spring MVC</strong>, <strong>Spring Data JPA</strong>, <strong>HTML</strong>, <strong>CSS</strong>, 
  and <strong>JavaScript</strong>. This application empowers users to easily manage their notes and account details.
</p>

<hr>

<h3 align="center">📌 Key Features</h3>
<p>
  The application provides users with powerful tools for note-taking and account management:
</p>

<div>
  <ul>
    <li>🔐 <strong>User Registration & Login</strong>: Easy and secure account creation and login process.</li>
    <li>🔑 <strong>Password Recovery</strong>: Reset passwords with email and mobile number verification.</li>
    <li>🗒️ <strong>Notes Management</strong>: Organize information by adding, updating, and deleting notes.</li>
    <li>👤 <strong>User Profile Management</strong>: View, update, and delete profile information.</li>
    <li>💻 <strong>JavaScript Validations</strong>: Ensures data integrity across all forms.</li>
  </ul>
</div>

<hr>

<h3 align="center">🏗️ Project Architecture</h3>
<p>
  Built using a clean and maintainable <strong>four-layer architecture</strong> for optimal code organization.
</p>

<div>
  <h4>1. Model Layer</h4>
  <p>Contains entity classes representing the database tables:</p>
  <ul>
    <li><strong>User Entity</strong>: Stores user details.</li>
    <li><strong>Notes Entity</strong>: Stores individual notes, with a <strong>Many-to-One</strong> relationship to users.</li>
  </ul>

  <h4>2. Controller Layer</h4>
  <p>Handles <strong>URL mappings</strong>, routes to thymeleaf template engine, and manages HTTP requests and responses.</p>

  <h4>3. Service Layer</h4>
  <p>Processes core business logic, including operations for managing users and notes.</p>

  <h4>4. Repository Layer</h4>
  <p>Defines repository interfaces for CRUD operations on database entities.</p>
</div>

<hr>

<h3 align="center">🚀 Core Functionalities</h3>

<div>
  <ul>
    <li>🔐 <strong>User Registration</strong>: Create accounts with basic user details.</li>
    <li>🔑 <strong>User Login</strong>: Securely access the application.</li>
    <li>❓ <strong>Forgot Password</strong>: Reset passwords using mobile and email validation.</li>
    <li>🗒️ <strong>Add Notes</strong>: Easily add notes for personal organization.</li>
    <li>✏️ <strong>Update Notes</strong>: Modify existing notes as needed.</li>
    <li>❌ <strong>Delete Notes</strong>: Remove notes that are no longer relevant.</li>
    <li>👤 <strong>View Profile</strong>: View personal information after login.</li>
    <li>✏️ <strong>Update Profile</strong>: Update profile information to keep it current.</li>
    <li>🗑️ <strong>Delete Account</strong>: Permanently delete accounts if desired.</li>
  </ul>
</div>

<hr>

<h3 align="center">⚙️ Technology Stack</h3>
<div>
  <ul>
    <li><strong>Backend</strong>: Spring MVC, Spring Data JPA</li>
    <li><strong>Frontend</strong>: HTML, CSS, JavaScript</li>
    <li><strong>Database</strong>: MySQL</li>
  </ul>
</div>

<hr>

<h3 align="center">🛠️ How It Works</h3>
<p>
  <strong>ENotes Application</strong> is developed using <strong>Spring MVC</strong>, <strong>Thymeleaf</strong>, 
  <strong>Spring Data JPA</strong>, <strong>CSS</strong>, <strong>JavaScript</strong>, and <strong>MySQL</strong>. 
  The application provides a seamless and user-friendly experience for note management and account operations:
</p>
<div>
  <ol>
    <li>
      <strong>Registration:</strong> New users can register by providing their details such as email and mobile number. Upon successful registration, all user details are securely stored in the database.
    </li>
    <li>
      <strong>Login:</strong> Users can log in with their credentials to access their account. Authentication ensures a secure experience.
    </li>
    <li>
      <strong>Password Recovery:</strong> If a user forgets their password, they can reset it through the password recovery feature. The application verifies the provided email and mobile number. If both are correct, the user is allowed to reset their password. Otherwise, access is denied.
    </li>
    <li>
      <strong>Perform Operations:</strong> After logging in, users can manage their notes and profile. The operations include:
      <ul>
        <li>🗒️ <strong>Add Notes:</strong> Create new notes to store personal information.</li>
        <li>✏️ <strong>Update Notes:</strong> Modify existing notes as needed.</li>
        <li>📖 <strong>Read Notes:</strong> View the details of specific notes.</li>
        <li>❌ <strong>Remove Notes:</strong> Delete notes that are no longer required.</li>
        <li>👤 <strong>View Profile:</strong> View personal profile details after login.</li>
        <li>✏️ <strong>Update Profile:</strong> Update profile information to keep it current.</li>
        <li>🗑️ <strong>Delete Profile:</strong> Permanently delete the user profile and associated details from the application.</li>
      </ul>
    </li>
  </ol>
</div>

<hr>

<h3 align="center">📋 Pre-requisites</h3>
<div>
  <ul>
    <li>Java Development Kit (JDK) 8 or higher installed</li>
    <li>Apache Tomcat server or equivalent</li>
    <li>MySQL Database</li>
    <li>Basic knowledge of Spring Framework</li>
  </ul>
</div>

<hr>

<h3 align="center">😊 Enjoy Using ENotes Application!</h3>
