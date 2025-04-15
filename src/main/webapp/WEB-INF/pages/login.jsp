<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css" />
</head>
<body>
<div class="container">
    <h2>Login</h2>

    <% if (request.getAttribute("error") != null) { %>
         <div class="error"><%= request.getAttribute("error") %></div>
    <% } else if (request.getAttribute("success") != null) { %>
        <div class="success"><%= request.getAttribute("success") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/login">
        <label>Username</label>
        <input type="text" name="username" required />

        <label>Password</label>
        <input type="password" name="password" required />

        <button type="submit">Login</button>
    </form>

    <p>Don’t have an account? <a href="<%= request.getContextPath() %>/register">Create new account</a></p>
</div>
</body>
</html>
