# 1. Logical and security testing
In this section candidate must find security and logical vulnerabilities, git clone this
project. Provided project is a flask based app contains four main apis install and follow
instruction then run app and start find logical and security vulnerabilities. List all findings
as bug reporting with risk scoring.
Just focus on
- /client_registeration
- /client_login

## Answer
Vulnerabilities
# Endpoint: /client_registeration

a. SQL Injection
•	The query select userName from users where email = " + email + " concatenates user input (email) directly into the SQL query, making it vulnerable to SQL Injection attacks.
Impact: High (potential unauthorized access or data leaks).
Risk: Critical

b. Missing Input Validation
The endpoint does not validate user input fields (fullName, userName, email, etc.) for length, format, or malicious input (e.g., <script> tags).
Impact: Medium (leads to potential XSS or database corruption).
Risk: High

c. Weak Password Storage
Passwords are stored in plaintext in the database.
Impact: High (user credentials can be compromised if the database is breached).
Risk: Critical

d. Email Enumeration
The API responds with {'msg':'Email already Exist'} if the email is found in the database. This allows attackers to check if specific emails are registered.
Impact: Medium.
Risk: High


# /client_login

a. SQL Injection
The queries select privillage from users where email = " + email + " and password = " + password + " and similar ones concatenate user inputs (email, password) directly into the SQL query, making it vulnerable to SQL Injection attacks.\
Impact: High.

b. Weak Authentication
Psswords are compared directly in plaintext, and there is no rate limiting for login attempts.
Impact: High.
verall Risk: Critical

c. JWT Token Without Expiry
he JWT generated does not have an expiration time, allowing tokens to remain valid indefinitely if leaked.
Impact: High.
Risk: High

