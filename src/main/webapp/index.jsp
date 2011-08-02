<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <p>
        TestServlet : <a href="test">test</a>
        </p>
        
        <p>
        REST : <a href="rest/account">all accounts</a>
        <br/>
        REST : <a href="rest/account/count">accounts count</a>
        <br/>
        REST : <a href="rest/account/1">account id=1</a>
        <br/>
        REST : <a href="rest/account/10/20">accounts pagination (10>>20)</a>
        </p>
        
        <p>
        REST : <a href="rest/operation">all operations</a>
        <br/>
        REST : <a href="rest/operation/count">operations count</a>
        <br/>
        REST : <a href="rest/operation/1">operation id=1</a>
        <br/>
        REST : <a href="rest/operation/10/20">operations pagination (10>>20)</a>
        </p>

        <p>
        UPLOAD : <a href="upload.jsp">upload.jsp</a>
        </p>
    </body>
</html>
