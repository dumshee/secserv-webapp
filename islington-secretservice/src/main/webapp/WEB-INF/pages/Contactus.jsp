<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us - Secret Service</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/contactus.css?v=2">
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
        <h2>Contact Us</h2>
        <p>We value your feedback and inquiries.</p>
    </section>

    <section class="careers">
        <h2>Reach Out</h2>
        <p>Email: <strong>support@secretservice.gov</strong></p>
        <p>Phone: <strong>+1-800-555-SECRET</strong></p>
        <p>Address: 950 H Street NW, Washington, DC 20223, USA</p>
    </section>

    <section class="careers">
        <h2>Send Us a Message</h2>
        <form action="SendMessageServlet" method="post" style="display: flex; flex-direction: column; gap: 15px; max-width: 600px; margin: auto;">
            <input type="text" name="name" placeholder="Your Name" required style="padding: 10px; border-radius: 5px;">
            <input type="email" name="email" placeholder="Your Email" required style="padding: 10px; border-radius: 5px;">
            <textarea name="message" placeholder="Your Message" rows="6" required style="padding: 10px; border-radius: 5px;"></textarea>
            <button type="submit" class="btn">Submit</button>
        </form>
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
