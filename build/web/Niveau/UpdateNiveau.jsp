<%-- 
    Document   : UpdateNiveau
    Created on : 26 mai 2016, 16:58:11
    Author     : Mahdi Kallel <mahdi-kallel@live.fr>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Update niveau</h1>
        <form method="GET" action="../NiveauSer">    
            <input type="text" name="action" required value="edit" hidden>
            id  <input type="text" name="idToedit" required value="<% out.print(request.getParameter("id"));%>" >
            Libelle:      <input type="text" name="libelleToEdit" value="<% out.print(request.getParameter("libelle"));%>" required>
           <input type="submit" value="Save and Return">
           <input type="submit" value="Cancel"></a>
        </form>
    </body>
</html>
