<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.islington.model.Mission" %>
<%
    List<Mission> missions = (List<Mission>) request.getAttribute("missions");
    String search = request.getParameter("search");
    if (search == null) search = "";

    Boolean searchPerformed = (Boolean) request.getAttribute("searchPerformed");
    String searchTerm = (String) request.getAttribute("searchTerm");
    if (searchPerformed == null) searchPerformed = false;
    if (searchTerm == null) searchTerm = "";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Missions</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/missions.css?v=2">
</head>
<body>
<div class="dashboard">
    <!-- Sidebar -->
    <div class="sidebar">
        <h2>Dashboard</h2>
        <a href="<%= request.getContextPath() %>/MyProfile">My Profile</a>
        <a href="<%= request.getContextPath() %>/home">Home</a>
        <a href="#">Missions</a>
        <form method="post" action="" style="display:inline;">
            <button type="submit" name="log" value="logout" class="btn">Logout</button>
        </form>
    </div>
    <!-- Main Content -->
    <div class="main">
        <h1>Missions</h1>

        <form method="get" action="<%= request.getContextPath() %>/missions" class="card">
            <input type="text" name="search" value="<%= search %>" placeholder="Search mission name..." />
            <button type="submit" class="btn">Search</button>
        </form>

        <div class="card">
            <h3>Mission List</h3>
            <table style="width: 100%; border-collapse: collapse; margin-top: 10px;">
                <thead>
                    <tr style="background-color: #f0f4ff;">
                        <th style="padding: 10px;">Name</th>
                        <th style="padding: 10px;">Description</th>
                        <th style="padding: 10px;">Start Date</th>
                        <th style="padding: 10px;">End Date</th>
                        <th style="padding: 10px;">Status</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    if (missions != null && !missions.isEmpty()) {
                        for (Mission mission : missions) {
                %>
                    <tr>
                        <td style="padding: 10px;"><%= mission.getMissionName() %></td>
                        <td style="padding: 10px;"><%= mission.getMissionDescription() %></td>
                        <td style="padding: 10px;"><%= mission.getStartDate() %></td>
                        <td style="padding: 10px;"><%= mission.getEndDate() %></td>
                        <td style="padding: 10px;"><%= mission.getStatus() %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5" style="text-align: center; padding: 20px;">No missions found.</td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
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
            <ul>
                <li><a href="">Home</a></li>
                <li><a href="">News</a></li>
                <li><a href="">About</a></li>
                <li><a href="">Contact Us</a></li>
                <li><a href="">Our Team</a></li>
            </ul>
        </div>
    </div>
    <div class="footerBottom">
        <p>Copyright &copy;2023; Designed by <span class="designer">SECserv</span></p>
    </div>
</footer>

<!-- Modal Popup -->
<div id="searchModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeModal()">&times;</span>
        <p id="modal-message"></p>
    </div>
</div>

<script>
    window.onload = function () {
        <% if (searchPerformed) { %>
            var modal = document.getElementById("searchModal");
            var message = document.getElementById("modal-message");
            message.innerText = "Search completed for: '<%= searchTerm %>'";
            modal.style.display = "block";
        <% } %>
    };

    function closeModal() {
        document.getElementById("searchModal").style.display = "none";
    }
</script>

</body>
</html>
