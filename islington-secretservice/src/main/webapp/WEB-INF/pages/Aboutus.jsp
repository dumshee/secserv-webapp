<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Us - Secret Service</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/aboutus.css?v=2">
</head>
<body>
<header>
        <div class="logo">
            <h1>Secret Service</h1>
        </div>
        <nav>
            <ul>
                <li><a href="<%= request.getContextPath() %>/Aboutus">About Us</a></li>
                <li><a href="<%= request.getContextPath() %>/Contactus">Contacts</a></li>
                <li><a href="<%= request.getContextPath() %>/MyProfile">My Profile</a></li>
                <li><a href="<%= request.getContextPath() %>/register">Register</a></li>
                <li><a href="<%= request.getContextPath() %>/login">Login</a></li>
            </ul>
        </nav>
    </header>

<main>
    <section class="hero">
        <h2>About the Secret Service</h2>
        <p>Who we are, what we do, and why it matters.</p>
    </section>

    <section class="careers">
        <h2>Our Mission</h2>
        <p>We are committed to protecting the highest elected leaders of our country and preserving the integrity of our nation's financial systems through advanced intelligence, cyber investigations, and secure operations.</p>
    </section>

    <section class="careers">
        <h2>Our Vision</h2>
        <p>To be a world-class security organization recognized for innovation, dedication, and service. We strive for a safer nation where threats are neutralized before they emerge.</p>
    </section>
</main>
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
