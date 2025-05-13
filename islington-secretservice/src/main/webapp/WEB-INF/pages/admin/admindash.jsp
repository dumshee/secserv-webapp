<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.islington.service.DashboardService" %>
<%@ page import="com.islington.model.AgentModel" %>
<%@ page import="java.util.List" %>

<%
    DashboardService service = new DashboardService();
    List<AgentModel> agentList = service.getAllAgents();
    int totalAgents = service.getTotalAgents();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admindash.css?v=2">
</head>
<body>
<div class="dashboard-container">
    <div class="sidebar">
        <h2>Admin Dashboard</h2>
        <ul>
            <li><a href="#">Dashboard</a></li>
            <li><a href="<%= request.getContextPath() %>/modifydash">Manage Agents</a></li>
            <li><a href="<%= request.getContextPath() %>/home">Home</a></li>
            <li><a href="<%= request.getContextPath() %>/login">Logout</a></li>
        </ul>
    </div>

    <div class="content">
        <h1>Agent Statistics</h1>
        <p>Total Agents: <%= totalAgents %></p>

        <h2>Agent List</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Agent ID</th>
                    <th>Name</th>
                    <th>Codename</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (agentList != null) {
                        for (AgentModel agent : agentList) {
                %>
                <tr>
                    <td><%= agent.getAgentId() %></td>
                    <td><%= agent.getFirstName() %> <%= agent.getLastName() %></td>
                    <td><%= agent.getCodename() %></td>
                    <td><%= agent.getEmail() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No agents found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<footer>
    <div class="footerContainer">
        <div class="socialIcons">
            <a href="#"><i class="fa-brands fa-facebook"></i></a>
            <a href="#"><i class="fa-brands fa-instagram"></i></a>
            <a href="#"><i class="fa-brands fa-twitter"></i></a>
            <a href="#"><i class="fa-brands fa-google-plus"></i></a>
            <a href="#"><i class="fa-brands fa-youtube"></i></a>
        </div>
        <div class="footerNav">
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">News</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact Us</a></li>
                <li><a href="#">Our Team</a></li>
            </ul>
        </div>
    </div>
    <div class="footerBottom">
        <p>&copy; 2023 Designed by <span class="designer">SECserv</span></p>
    </div>
</footer>
</body>
</html>
