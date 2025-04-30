<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/MyProfile.css?v=2" />
</head>
<body>
<div class="dashboard">
    <div class="sidebar">
        <h2>Dashboard</h2>
        <a href="#" class="active">My Profile</a>
        <a href="<%= request.getContextPath() %>/Home">Home</a>
        <a href="#">Missions</a>
        <a href="#">Achievements</a>
        <a href="#">Programs</a>
        <a href="<%= request.getContextPath() %>/login">Logout</a>
    </div>

    <div class="main">
        <h1>Welcome, <%= session.getAttribute("username") %></h1>

        <div class="card">
            <h3>Profile Details</h3>
            <form action="${pageContext.request.contextPath}/MyProfile" method="post">
                <input type="email" name="email" placeholder="Email" value="<%= session.getAttribute("email") %>">
                <input type="tel" name="number" placeholder="number" value="<%= session.getAttribute("number") %>">
                <button class="btn" type="submit">Update Profile</button>
            </form>
        </div>

        <div class="flex-row">
            <div class="card small">
                <h3>Achievements</h3>
                <ul>
                    <li>✅ Joined 3 Programs</li>
                    <li>🎓 Leadership Badge</li>
                </ul>
            </div>
            <div class="card small">
                <h3>Current Mission</h3>
                <p>Enroll in Growth Bootcamp</p>
                <p>Status: <strong style="color: green;">In Progress</strong></p>
            </div>
            <div class="card small">
                <h3>Programs</h3>
                <p>🌱 Growth Accelerator</p>
            </div>
        </div>
    </div>
</div>
<footer>
    <div class="footerContainer">
        <div class="socialIcons">
            <a href=""><i class="fa-brands fa-facebook"></i></a>
            <a href=""><i class="fa-brands fa-instagram"></i></a>
            <a href=""><i class="fa-brands fa-twitter"></i></a>
            <a href=""><i class="fa-brands fa-google-plus"></i></a>
            <a href=""><i class="fa-brands fa-youtube"></i></a>
        </div>
        <div class="footerNav">
            <ul><li><a href="">Home</a></li>
                <li><a href="">News</a></li>
                <li><a href="">About</a></li>
                <li><a href="">Contact Us</a></li>
                <li><a href="">our Team</a></li>
            </ul>
        </div>
        
    </div>
    <div class="footerBottom">
        <p>Copyright &copy;2023; Designed by <span class="designer">SECserv</span></p>
    </div>
</footer>
</body>
</html>