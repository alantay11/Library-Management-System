<%-- 
    Document   : registerMember
    Created on : Feb 14, 2023, 4:40:06 PM
    Author     : Uni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Member</title>
    </head>
    <body>
        <%@include file="layout/header.jsp" %>
        <form action="doRegisterMember" method="POST">
            <label for="firstName">First Name:     </label><input type="text" id="firstName" name="firstName" /><br />
            <label for="lastName">Last Name: </label><input type="text" id="lastName" name="lastName" /><br />
            Gender: <select name="gender">
                <option value="F">Female</option>
                <option value="M">Male</option>
            </select><br>
            <label for="age">Age: </label><input type="text" id="age" name="age" /><br />
            <label for="identityNo">Identity No:     </label><input type="text" id="identityNo" name="identityNo" /><br />
            <label for="phone">Phone: </label><input type="text" id="phone" name="phone" /><br />
            <label for="address">Address: </label><input type="text" id="address" name="address" /><br />
            <input type="submit" value="Register" />
        </form>

    </body>
</html>
