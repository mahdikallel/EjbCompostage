<%-- 
    Document   : index
    Created on : 19 mai 2016, 02:18:36
    Author     : Mahdi Kallel <mahdi-kallel@live.fr>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="js/jquery.mobile-1.4.5.min.css" />
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/jquery.mobile-1.4.5.min.js"></script>
    </head>
    <body >
        <a href="#popupLogin" data-rel="popup" data-position-to="window" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-check ui-btn-icon-left ui-btn-a" data-transition="pop">Sign in</a>
        <div data-role="popup" id="popupLogin" data-theme="a" class="ui-corner-all">
                <form>
                        <div style="padding:10px 20px;">
                                <h3>Please sign in</h3>
                                <label for="un" class="ui-hidden-accessible">Username:</label>
                                <input type="text" name="user" id="un" value="" placeholder="username" data-theme="a">
                                <label for="pw" class="ui-hidden-accessible">Password:</label>
                                <input type="password" name="pass" id="pw" value="" placeholder="password" data-theme="a">
                                <button type="submit" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check">Sign in</button>
                            </div>
                    </form>
        </div>

        <a href="#popupDialog" data-rel="popup" data-position-to="window" data-transition="pop" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-delete ui-btn-icon-left ui-btn-b">Delete page...</a>
        <div data-role="popup" id="popupDialog" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">
                <div data-role="header" data-theme="a">
                    <h1>Delete Page?</h1>
                    </div>
                <div role="main" class="ui-content">
                        <h3 class="ui-title">Are you sure you want to delete this page?</h3>
                    <p>This action cannot be undone.</p>
                        <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back">Cancel</a>
                        <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back" data-transition="flow">Delete</a>
                    </div>
        </div>
    </body>

</html>
