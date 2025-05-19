<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.islington.service.UserService" %>
<%@ page import="com.islington.model.AgentModel" %>
<%@ page import="java.util.List" %>

<%
    UserService service = new UserService();
    List<AgentModel> agentList = service.getAllAgents();
    String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Agents</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admindash.css?v=2">
</head>
<body>
<div class="dashboard-container">
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <ul>
            <li><a href="<%= request.getContextPath() %>/admindash">Dashboard</a></li>
            <li><a class="active" href="<%= request.getContextPath() %>/modifydash">Manage Agents</a></li>
            <li><a href="<%= request.getContextPath() %>/home">Home</a></li>  
            <li>
            	<form method="post" action="" style="margin: 0;">
        			<button type="submit" name="log" value="logout">Logout</button>
    			</form>
            </li>
        </ul>     
    </div>

    <div class="content">
        <h1>Manage Agents</h1>

        <% if (message != null) { %>
            <div class="message"><%= message %></div>
        <% } %>

        <h2>Agent List</h2>
        <div class="table-container">
            <table class="agent-table">
                <thead>
                    <tr>
                        <th>Agent ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Codename</th>
                        <th>DOB</th>
                        <th>Gender</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Password</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <% for (AgentModel agent : agentList) { %>
                    <tr>
                        <form method="post" action="<%= request.getContextPath() %>/modifydash">
                            <td>
                                <%= agent.getAgentId() %>
                                <input type="hidden" name="agentId" value="<%= agent.getAgentId() %>" />
                            </td>
                            <td><input class="table-input" type="text" name="firstName" value="<%= agent.getFirstName() %>" required /></td>
                            <td><input class="table-input" type="text" name="lastName" value="<%= agent.getLastName() %>" required /></td>
                            <td><input class="table-input" type="text" name="codename" value="<%= agent.getCodename() %>" required /></td>
                            <td><input class="table-input" type="date" name="dob" value="<%= agent.getDob() %>" required /></td>
                            <td>
                                <select class="table-input" name="gender" required>
                                    <option value="Male" <%= agent.getGender().equals("Male") ? "selected" : "" %>>Male</option>
                                    <option value="Female" <%= agent.getGender().equals("Female") ? "selected" : "" %>>Female</option>
                                    <option value="Other" <%= agent.getGender().equals("Other") ? "selected" : "" %>>Other</option>
                                </select>
                            </td>
                            <td><input class="table-input" type="email" name="email" value="<%= agent.getEmail() %>" required /></td>
                            <td><input class="table-input" type="text" name="number" value="<%= agent.getNumber() %>" required /></td>
                            <td><input class="table-input" type="password" name="password" placeholder="New Password" /></td>
                            <td class="action-buttons">
                                <button class="btn" type="submit" name="action" value="update">Update</button>
                                <button class="btn danger" type="submit" name="action" value="delete"
                                        onclick="return confirm('Are you sure you want to delete this agent?')">Delete</button>
                            </td>
                        </form>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <h2>Add New Agent</h2>
        <form method="post" action="<%= request.getContextPath() %>/modifydash" class="user-form">
            <input type="hidden" name="action" value="create" />
            <input type="text" name="firstName" placeholder="First Name" required />
            <input type="text" name="lastName" placeholder="Last Name" required />
            <input type="text" name="codename" placeholder="Codename" required />
            <input type="email" name="email" placeholder="Email" required />
            <input type="date" name="dob" required />
            <select name="gender" required>
                <option value="">Gender</option>
                <option>Male</option>
                <option>Female</option>
                <option>Other</option>
            </select>
            <input type="text" name="number" placeholder="Phone Number" required />
            <input type="password" name="password" placeholder="Password" required />
            <button class="btn" type="submit">Add Agent</button>
        </form>
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
