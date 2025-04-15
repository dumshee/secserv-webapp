<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css" />
</head>
<body>
<div class="container">
    <h2>Register</h2>

    <% if (request.getAttribute("error") != null) { %>
        <div class="error"><%= request.getAttribute("error") %></div>
    <% } else if (request.getAttribute("success") != null) { %>
        <div class="success"><%= request.getAttribute("success") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/register">
        <label>First Name</label>
        <input type="text" name="firstName" value="<%= request.getAttribute("firstName") != null ? request.getAttribute("firstName") : "" %>" required />

        <label>Last Name</label>
        <input type="text" name="lastName" value="<%= request.getAttribute("lastName") != null ? request.getAttribute("lastName") : "" %>" required />

        <label>Username</label>
        <input type="text" name="username" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" required />

        <label>Date of Birth</label>
        <input type="date" name="dob" value="<%= request.getAttribute("dob") != null ? request.getAttribute("dob") : "" %>" required />

        <label>Gender</label>
        <select name="gender" required>
            <option value="">Select</option>
            <option value="male" <%= "male".equals(request.getAttribute("gender")) ? "selected" : "" %>>Male</option>
            <option value="female" <%= "female".equals(request.getAttribute("gender")) ? "selected" : "" %>>Female</option>
        </select>

        <label>Email</label>
        <input type="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required />

        <label>Phone Number</label>
        <input type="text" name="phoneNumber" value="<%= request.getAttribute("phoneNumber") != null ? request.getAttribute("phoneNumber") : "" %>" required />

        <label>Subject</label>
        <input type="text" name="subject" value="<%= request.getAttribute("subject") != null ? request.getAttribute("subject") : "" %>" required />

        <label>Password</label>
        <input type="password" name="password" required />

        <label>Retype Password</label>
        <input type="password" name="retypePassword" required />

        <button type="submit">Register</button>
    </form>

    <p>Already have an account? <a href="<%= request.getContextPath() %>/login">Login</a></p>
</div>
</body>
</html>
