<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Users</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admindash.css?v=2">
</head>
<body>
<div class="dashboard-container">
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <ul>
            <li><a href="<%= request.getContextPath() %>/admindash">Dashboard</a></li>
                <li><a href="#">Manage Agents</a></li>
                <li><a href="#">Settings</a></li>
            <li><a href="#">Logout</a></li>
        </ul>
    </div>
    <div class="main-content">
        <h1>Modify User Accounts</h1>
        <form class="user-form" method="post" action="<%= request.getContextPath() %>/modifydash">
            <label>Select User</label>
            <input type="text" name="username" placeholder="Enter username to modify" required />

            <label>Action</label>
            <select name="action" required>
                <option value="">Select an action</option>
                <option value="deactivate">Deactivate Account</option>
                <option value="delete">Delete Account</option>
                <option value="reset">Reset Password</option>
            </select>

            <label>Notes</label>
            <textarea name="notes" placeholder="Reason or description (optional)" rows="4"></textarea>

            <button type="submit">Apply Action</button>
        </form>
    </div>
</div>
</body>
</html>
