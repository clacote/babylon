<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>

        <form action="login">
            <label for="provider">Choose an OpenID provider :</label>
            <input type="radio" name="provider" value="google" />Google
            <input type="radio" name="provider" value="yahoo" />Yahoo
            <input type="radio" name="provider" value="twitter" />Twitter
            <input type="radio" name="provider" value="linkedin" />LinkedIn
            
            <input type="submit" value="Login" />
        </form>
                
    </body>
</html>
