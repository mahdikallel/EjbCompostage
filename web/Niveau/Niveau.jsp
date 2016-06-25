<%-- 
    Document   : Niveau
    Created on : 20 mai 2016, 00:42:32
    Author     : Mahdi Kallel <mahdi-kallel@live.fr>
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="project.controller.NiveauServlet"%>
<%@page import="project.model.Niveau"%>
<%@page import="project.ado.NiveauAdo"%>
<%@page import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--link rel="stylesheet" href="../js/jquery.mobile-1.4.5.min.css" />
        <script src="../js/jquery-1.11.1.min.js"></script>
        <script src="../js/jquery.mobile-1.4.5.min.js"></script-->
        <title>Niveau</title>
    </head>
    <body>
        <%
            if (session.getAttribute("insert") != null) {
                if (session.getAttribute("insert").equals("oui")) {
                    out.print("Bien inseree");
                }
            }
        %>
        <fieldset>
            <legend>Liste des Niveau</legend>
            <form method="POST" action="../NiveauSer">

                Niveau:<input type="text" name="libelle" required></td><td> <input type="submit" value="Ajouter" >


                    <c:if test="${sessionScope.nivList == null}">



                        <%
                            NiveauServlet servlet = new NiveauServlet();
                            servlet.getList(request, response);
                        %>
                    </c:if>




                    <c:if test="${sessionScope.nivList != null}">
                        <table border="2">
                            <tbody>
                                <tr>
                                    <th>ID avec session</th>
                                    <th>Name</th>

                                    <th>Group's number </th>
                                    <th>Delete </th>
                                    <th>update </th>
                                </tr>


                                <%--
                                    out.print("getRequestURL()        " + request.getRequestURL() + "<br>");
                                    String q = "random word £500 bank $";
                                    String url = "http://example.com/query?q=" + URLEncoder.encode(q, "UTF-8");
                                    out.print(url);--%>
                                <c:forEach items="${sessionScope.nivList}" var="niv">
                                    <tr>

                                        <td><c:out value="${niv[0]}"  ></c:out></td>

                                            <td><c:out value="${niv[1]}" ></c:out></td>



                                            <td><c:out value="${niv[2]}" ></c:out></td>

                                            <td ><a href=../NiveauSer?action=delete&idTodelete=<c:out value="${niv[0]}">  </c:out> > <input type="button" value="Delete"></a> </td>
                                        <td> <a href=UpdateNiveau.jsp?id=<c:out value="${niv[0]}"></c:out>&libelle=<c:out value="${niv[1]}"></c:out> > <input type="button" value="Update"></a></td>
                                        </tr>
                                </c:forEach>


                            </tbody>
                        </table>
                    </c:if>


                    </fieldset>
            </form>


            <SCRIPT language="javascript">
                function ouvre_popup(page) {
                    window.open(page, "nom_popup", "menubar=no, status=no, scrollbars=no, menubar=no, width=200, height=100");
                }


                function afficher_cacher(id2)
                {

                    if (document.getElementById('texte').style.visibility == "visible")
                    {



                        document.getElementById('texte').value = id2;
                    }
                    else
                    {


                        document.getElementById('texte').value = id2;
                    }
                    return true;
                }


            </SCRIPT>
    </body>
</html>
<!--input class="bouton" id="bouton_texte" 
                                                  onclick="afficher_cacher(<!--c:out value=""><!--/c:out>);" type="button" value="update" ></td>
