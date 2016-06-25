<%-- 
    Document   : Groupe
    Created on : 20 mai 2016, 03:29:40
    Author     : Mahdi Kallel <mahdi-kallel@live.fr>
--%>

<%@page import="java.util.List"%>
<%@page import="project.ado.NiveauAdo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
         <%
        if (session.getAttribute("insert") != null) {
                if (session.getAttribute("insert").equals("oui")) {
                    out.print("Bien inseree");
                }
            }
        %>
        <form method="GET" action="NiveauSer">
            Groupe:<input type="text" name="libelle"><br>
           Niveau: <select name="niveau">
                <%
                    NiveauAdo nivAdo = new NiveauAdo();
                    List<Object[]> listNiv;
                    listNiv = nivAdo.getTous();
                    for (Object[] row : listNiv) {

                        out.println("<option id="+row[0].toString()+"> "+ row[1].toString() + "</oprtion>");

                    }
                %>
               
              
            </select><br>
            <input type="submit" value="Ajouter"><br>
        <table border="1">
           
                
           
            <tr>
                <td>id</td><td>Nom du groupe</td>
                
            </tr>

        </table>
            </form>
    </body>
</html>
