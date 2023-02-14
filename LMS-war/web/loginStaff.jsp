<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>

        <h1>Login staff</h1>
        <form action="doLoginStaff" method="POST">
            <label for="username">Name:     </label><input type="text" id="username" 
                                                       name="username" /><br />
            <label for="password">Password: </label><input type="text" id="password" 
                                                       name="password" /><br />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>