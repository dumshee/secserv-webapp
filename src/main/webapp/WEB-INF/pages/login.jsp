<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css?v=2" />
</head>
<body>
<div class="login-container">
    <div class="login-card">
        <div class="left-panel">
            <div class="logo">&#x2B22;</div>
        </div>
        <div class="right-panel">
            <h1>We are <span class="brand-name">Secret Service</span></h1>
            <p class="subtitle">Welcome back! Log in to your account.</p>

            <% if (request.getAttribute("error") != null) { %>
                <div class="error"><%= request.getAttribute("error") %></div>
            <% } else if (request.getAttribute("success") != null) { %>
                <div class="success"><%= request.getAttribute("success") %></div>
            <% } %>

            <form method="post" action="<%= request.getContextPath() %>/login">
                <div class="input-group">
                    <input type="text" name="codename" placeholder="codename" required />
                </div>
                <div class="input-group">
                    <input type="password" name="password" placeholder="Password" required />
                </div>
                <button type="submit" class="login-btn">Log in</button>
            </form>

            <a href="<%= request.getContextPath() %>/register" class="basic-link">Register</a>
        </div>
    </div>
</div>
</body>
</html>
