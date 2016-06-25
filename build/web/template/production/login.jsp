<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Custom login/register lock page freebie by @remtsoy</title>
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/lineicons.css">
        <link rel="stylesheet" href="css/styles.css">

        <style>

            input[type=checkbox]:not(old) + label > span,
            input[type=radio   ]:not(old) + label > span{
                display          : inline-block;
                width            : 0.875em;
                height           : 0.875em;
                margin           : 0.25em 0.5em 0.25em 0.25em;
                border           : 0.0625em solid rgb(192,192,192);
                border-radius    : 0.25em;
                background       : rgb(224,224,224);
                background-image :    -moz-linear-gradient(rgb(240,240,240),rgb(224,224,224));
                background-image :     -ms-linear-gradient(rgb(240,240,240),rgb(224,224,224));
                background-image :      -o-linear-gradient(rgb(240,240,240),rgb(224,224,224));
                background-image : -webkit-linear-gradient(rgb(240,240,240),rgb(224,224,224));
                background-image :         linear-gradient(rgb(240,240,240),rgb(224,224,224));
                vertical-align   : bottom;
            }
        </style>
    </head>
    <body>
        <div class="main-wrapper">
            <div class="main-wrapper-bg" id="main-wrapper-bg"></div>
            <canvas id="my-canvas" class="my-canvas"></canvas>
            <div class="main-wrapper-mask"></div>
            <div class="main-container">
                <div class="container">
                    <div class="row row-main">

                        <div class="col-md-4 col">
                            <h1 class="main-title">Authentification</h1>
                            <form method="POST" action="../../Authentification">

                                <select  class="form-control" required name="groupe" onchange="window.location.href = '../../Authentification?idCat=' + this.options[this.selectedIndex].value + '&action=selectTypeUser';">

                                    <%
                                        int x;
                                        if (session.getAttribute("idCat") == null) {
                                            x = -1;

                                        } else {
                                            x = Integer.parseInt(session.getAttribute("idCat").toString());
                                        }
                                    %>
                                    <option value="-1"
                                            <%


                                                if (x == -1) {
                                                    out.print("selected");
                                                }%>>Choisissez le type</option>

                                    <option value="0" <%


                                        if (x == 0) {
                                            out.print("selected");
                                        }%>>Administrateur</option>
                                    <option value="1"
                                            <%

                                                if (x == 1) {
                                                    out.print("selected");
                                                }%>>Enseignant</option>
                                    <option value="2" 
                                            <%

                                                if (x == 2) {
                                                    out.print("selected");
                                                }%>>Etudiant</option>
                                </select>

                                <div class="form-group">
                                    <input type="text"  hidden name="action" value="<%
                                        if (session.getAttribute("type") == null) {
                                            out.print("noSelect");
                                        } else {
                                            out.print(session.getAttribute("type"));
                                        }
                                           %>">

                                    <input type="text" class="form-control" placeholder="Username"  name="username">
                                    <div class="form-icon lin lin-user"></div>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="Password" name="password">
                                    <div class="form-icon lin lin-key"></div>
                                </div>





                                <input type="submit" value="Log in" class="btn btn-default btn-ghost">
                            </form>
                            <a href="#" class="forgot-password">

                                <% if (session.getAttribute("failedEns") != null) {%>
                                <div class="alert alert-danger alert-dismissible fade in" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                                    </button>
                                    <h5>  <strong>Erreur !</strong> Vérifier votre login ou password</h5>
                                </div>
                                <%                                    }
                                    session.setAttribute("failedEns", null);
                                %>

                            </a>

                            <a href="#" class="forgot-password">

                                <% if (session.getAttribute("failedAdmin") != null) {%>
                                <div class="alert alert-danger alert-dismissible fade in" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                                    </button>
                                  <h5>   <strong>Erreur !</strong> Vérifier votre login ou password</h5>
                                </div>
                                <%                                    }
                                    session.setAttribute("failedAdmin", null);
                                %>

                            </a>

                            <a href="#" class="forgot-password">

                                <% if (session.getAttribute("failedEtud") != null) {%>
                                <div class="alert alert-danger alert-dismissible fade in" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                                    </button>
                                   <h5>  <strong>Erreur !</strong> Vérifier votre login ou password</h5>
                                </div>
                                <%                                    }
                                    session.setAttribute("failedEtud", null);
                                %>

                            </a>


                            <a href="#" class="forgot-password">

                                <% if (session.getAttribute("choixType") != null) {%>
                                <div class="alert alert-warning alert-dismissible fade in" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                                    </button>
                                    <h5> <strong>Wargning !</strong> Vous devez choisir le type d'utilistaeur</h5>
                                </div>
                                <%                                    }
                                    session.setAttribute("choixType", null);
                                %>

                            </a>

                        </div>



                    </div>

                </div>
            </div>

        </div>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/canvas.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>