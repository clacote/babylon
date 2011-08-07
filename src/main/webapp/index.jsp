<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.net.InetAddress"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Babylon tests</h1>
        
        <p>
            Server running at http://<%= InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort() + request.getContextPath() %>
        </p>
        
        <p>
        TestServlet : <a href="test">test</a>
        </p>
        
        <%
            if (request.getUserPrincipal() != null) {
        %>
                <p>
                    Hello, <strong><%=request.getUserPrincipal().getName()%></strong>!
                </p>
        <%
            }
        %>
        
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
        LOGIN : <a href="http://<%= InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort() + request.getContextPath() %>/login.jsp">login.jsp</a>
        </p>

        <p>
        UPLOAD : <a href="upload.jsp">upload.jsp</a> (actuel ID address used instead of "localhost" for OpenID redirection and jsessionid propagation through cookie
        </p>
    </body>
</html>
