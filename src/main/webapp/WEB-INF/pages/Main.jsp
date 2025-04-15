<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Secret Service</title>
    <link rel="stylesheet" href="css/Main.css">
</head>
<body>
    <header>
        <div class="logo">
            <img src="resources/agent.jpg" alt="Secret Service">
            <h1>Secret Service</h1>
        </div>
        <nav>
            <ul>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Careers</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="<%= request.getContextPath() %>/register">Register</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="hero">
            <h2>One Integrated Mission</h2>
            <p>Protect our nation's leaders and financial infrastructure</p>
            <a href="#" class="btn">Learn More</a>
        </section>

        <section class="key-issues">
            <div class="issue">
                <h3>National Security</h3>
                <p>We protect world leaders, major events, and key locations.</p>
                <a href="#">View</a>
            </div>
            <div class="issue">
                <h3>Public Safety</h3>
                <p>We share our threat assessment expertise for public safety.</p>
                <a href="#">View</a>
            </div>
            <div class="issue">
                <h3>Economic Safeguard</h3>
                <p>We protect the integrity of currency.</p>
                <a href="#">View</a>
            </div>
            <div class="issue">
                <h3>Cyber Investigations</h3>
                <p>We fight cybercrime to safeguard financial infrastructure.</p>
                <a href="#">View</a>
            </div>
        </section>

        <section class="careers">
            <h2>Careers at Secret Service</h2>
            <p>If you have a commitment to excellence and are looking for a unique and fulfilling career, we want to hear from you.</p>
            <a href="#" class="btn">Visit Our Careers Section</a>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 Secret Service. All rights reserved.</p>
    </footer>
</body>
</html>
