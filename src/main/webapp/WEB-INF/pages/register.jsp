<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css?v=2" />
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
    <div class="column left">
        <div>
            <label>First Name</label>
            <input type="text" name="firstName" value="<%= request.getAttribute("firstName") != null ? request.getAttribute("firstName") : "" %>" required />
        </div>
        <div>
            <label>Last Name</label>
            <input type="text" name="lastName" value="<%= request.getAttribute("lastName") != null ? request.getAttribute("lastName") : "" %>" required />
        </div>
        <div>
            <label>Date of Birth</label>
            <input type="date" name="dob" value="<%= request.getAttribute("dob") != null ? request.getAttribute("dob") : "" %>" required />
        </div>
        <div>
            <label>Gender</label>
            <select name="gender" required>
                <option value="">Select</option>
                <option value="male" <%= "male".equals(request.getAttribute("gender")) ? "selected" : "" %>>Male</option>
                <option value="female" <%= "female".equals(request.getAttribute("gender")) ? "selected" : "" %>>Female</option>
            </select>
        </div>
    </div>

    <div class="column right">
         <div>
            <label>Username</label>
            <input type="text" name="codename" value="<%= request.getAttribute("codename") != null ? request.getAttribute("codename") : "" %>" required />
        </div>
        <div>
            <label>Email</label>
            <input type="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required />
        </div>
        <div>
            <label>Phone Number</label>
            <input type="text" name="phoneNumber" value="<%= request.getAttribute("phoneNumber") != null ? request.getAttribute("phoneNumber") : "" %>" required />
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" required />
        </div>
        <div>
            <button type="submit">Register</button>
        </div>
    </div>
</form>

    <p>Already have an account? <a href="<%= request.getContextPath() %>/login">Login</a></p>
</div>
</body>
</html>
