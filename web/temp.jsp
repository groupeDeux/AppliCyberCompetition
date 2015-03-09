<%-- 
    Document   : temp
    Created on : 6 mars 2015, 08:57:03
    Author     : vivi
--%>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form id="test" action="getListDelegation">
            <input type="submit" value="coucou" >
            ${listDelegations.size()}
        </form>
    </body>
</html>
