<%@page import="project.controller.ProfServlet"%>
<%@page import="project.controller.GroupeServlet"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Gestion Note</title>

        <!-- Bootstrap -->
        <link href="../../../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="../../../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="../../../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!-- bootstrap-progressbar -->
        <link href="../../../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
        <!-- jVectorMap -->
        <link href="../../css/maps/jquery-jvectormap-2.0.3.css" rel="stylesheet"/>

        <!-- Custom Theme Style -->
        <link href="../../../build/css/custom.min.css" rel="stylesheet">

        <%@page import="java.net.URLEncoder"%>
        <%@page import="project.controller.GroupeServlet"%>
        <%@page import="project.model.Groupe"%>
        <%@page import="project.ado.GroupeAdo"%>
        <%@page import="java.util.List"%>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>	

    </head>
    <%
        if (session.getAttribute("username") == null) {
            response.sendRedirect("/EJBProject/template/production/login.jsp");
        }
    %>
    <body class="nav-md" style="background-color: #1b6d85" >
        <div class="container body" style="background-color: #1b6d85">
            <div class="main_container" style="background-color: #1b6d85" >
                <div class="col-md-3 left_col" style="background-color: #1b6d85">
                    <div class="left_col scroll-view"style="background-color: #1b6d85">
                        <div class="navbar nav_title" style="border: 0;background-color: #1b6d85 " >
                            <a href="../../index.jsp" class="site_title"><i class="fa fa-paw"></i> <span>Gestion Note</span></a>
                        </div>

                        <div class="clearfix"></div>

                        <!-- menu profile quick info -->
                        <div class="profile">
                            <div class="profile_pic">
                                <img src="../../images/img.jpg" alt="..." class="img-circle profile_img">
                            </div>
                            <div class="profile_info">
                                <span>Welcome,</span>
                                <h2><% out.print(session.getAttribute("username"));%></h2>
                            </div>
                        </div>
                        <!-- /menu profile quick info -->

                        <br />

                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <h3>General</h3>
                                <ul class="nav side-menu">
                                    <li><a href="../../index.jsp"><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>

                                    </li>
                                    <li><a><i class="fa fa-edit"></i> Professeur <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="listeProfesseur.jsp" >Liste des professeurs</a></li>

                                        </ul>
                                    </li>

                                    <li><a><i class="fa fa-edit"></i> Etudiant <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="../Etudiant/listeEtudiant.jsp">Liste des Etudiants</a></li>

                                        </ul>
                                    </li>

                                    <li><a><i class="fa fa-edit"></i> Niveau <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="../Niveau/listeNiveau.jsp">Liste des Niveaux</a></li>

                                        </ul>
                                    </li>

                                    <li><a><i class="fa fa-edit"></i> Groupe <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="../Groupe/listeGroupe.jsp">Liste des Groupes</a></li>

                                        </ul>
                                    </li>

                                    <li><a><i class="fa fa-edit"></i> Matiére <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="../Matiere/listeMatiere.jsp">Liste des Matiéres</a></li>

                                        </ul>
                                    </li>

                                    <li><a><i class="fa fa-edit"></i> Session <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="../Session/listeSession.jsp">Liste des Sessions</a></li>

                                        </ul>
                                    </li>

                                    <li><a><i class="fa fa-edit"></i> Notes <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="../Note/listeNote.jsp">Liste des Notes</a></li>

                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-edit"></i> Compostage <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="../Compostage/GererCompostage.jsp">Ajouter compostage</a></li>

                                        </ul>
                                    </li>

                                    <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="tables.html">Tables</a></li>
                                            <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                                        </ul>
                                    </li>


                                </ul>
                            </div>

                        </div>


                    </div>

                </div>




            </div>

            <!-- top navigation -->
            <div class="top_nav">
                <div class="nav_menu">
                    <nav class="" role="navigation">
                        <div class="nav toggle">
                            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                        </div>

                        <ul class="nav navbar-nav navbar-right">
                            <li class="">
                                <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <img src="../../images/img.jpg" alt=""><% out.print(session.getAttribute("username"));%>
                                    <span class=" fa fa-angle-down"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-usermenu pull-right">
                                    <li><a href="javascript:;"> Profile</a></li>


                                    <li><a href="../../../../Authentification?action=logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                                </ul>
                            </li>




                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- /top navigation -->

            <!-- page content -->
            <div class="right_col" role="main">
                <!-- top tiles -->

                <div style="height:3050px;">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Liste des enseignants </h2>
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>

                                </li>
                                <li><a class="close-link"><i class="fa fa-close"></i></a>
                                </li>
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                        <c:if test="${sessionScope.ensList == null}">

                            <%
                                ProfServlet servlet = new ProfServlet();
                                servlet.getList(request, response);

                            %>
                        </c:if>
                        <form method="POST" action="../../../../ProfServlet">
                            <input type="text" name="action" hidden value="add">
                            <table style="width: 70%">
                                <tr>

                                    <td><label for="exampleInputName2"> Nom :</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input required name ="nom" type="text" class="form-control has-feedback-left" id="inputSuccess2" placeholder="First Name">
                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                                    <td>

                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>

                                <tr>

                                    <td><label for="exampleInputName2"> Prenom :</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input required name ="prenom" type="text" class="form-control has-feedback-left" id="inputSuccess2" placeholder="Last Name">
                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                        </div>

                                    </td>
                                </tr>

                                <tr>
                                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                                    <td>

                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                                <tr>

                                    <td><label for="exampleInputName2"> Date de naissance:</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input name="date" id="birthday" class="date-picker form-control col-md-7 col-xs-12 active" required="required" type="date">

                                        </div>
                                        <br>
                                    </td>
                                </tr>

                                <tr>
                                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                                    <td>

                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                                <tr>

                                    <td> <label for="exampleInputName2"> Adresse :</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input type="" name="adr" class="form-control" id="" value="">
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                                    <td>

                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                                <tr>

                                    <td> <label for="exampleInputName2"> Num° Cin :</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input type="number" required name="cin" class="form-control" id="" value="" onKeyDown="if (this.value.length == 8 && event.keyCode != 8)
                                                        return false;">
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                                    <td>

                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                                <tr>

                                    <td>  <label for="exampleInputName2"> Num° télèphone :</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input required name="tel" type="text" class="form-control" id="inputSuccess5" placeholder="Phone">
                                            <span class="fa fa-phone form-control-feedback right" aria-hidden="true"></span>
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                                    <td>

                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>

                                <tr>

                                    <td>  <label for="exampleInputName2"> Login :</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input required type="" name="login" class="form-control" id="" value="">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
                                    <td>

                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                                <tr>

                                    <td>   <label for="exampleInputName2"> Password :</label> </td>
                                    <td>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <input required type="password"  class="form-control" name="password" id="password" placeholder="Enter your Password">

                                        </div>


                                    </td>



                                </tr>




                            </table>



                            <br>
                            <div  style="margin-left: 325px; width: 160px;">
                                <button type="button" class="btn btn-success">Vider</button>
                                <input type="submit" value="Ajouter" class="btn btn-primary" >
                            </div>



                        </form>

                        <%
                              if (session.getAttribute("ProfloginFailed") != null) {
                                  if (Integer.parseInt(session.getAttribute("ProfloginFailed").toString()) == 1) {%>
                        <div class="alert alert-danger alert-dismissible fade in" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                            </button>
                            <strong>Login exisit  !</strong>  Please choose an other.
                        </div>
                        <% }
                            }
                        session.setAttribute("ProfloginFailed", 0);%>
                        <div class="x_content">
                            <c:if test="${sessionScope.ensList != null}">
                                <table class="table table-striped">

                                    <tbody>
                                        <tr>
                                            <th>ID </th>
                                            <th>Nom</th>
                                            <th>Prenom</th>
                                            <th>Date de naissance </th>
                                            <th>Adresse</th>
                                            <th>Cin</th>
                                            <th>Tel</th>
                                            <th>Login</th>
                                            <th>Password</th>
                                            <th>Delete </th>
                                            <th>update </th>
                                        </tr>


                                        <%--
                                            out.print("getRequestURL()        " + request.getRequestURL() + "<br>");
                                            String q = "random word £500 bank $";
                                            String url = "http://example.com/query?q=" + URLEncoder.encode(q, "UTF-8");
                                            out.print(url);--%>
                                        <c:forEach items="${sessionScope.ensList}" var="niv">
                                            <tr>

                                                <td><c:out value="${niv[0]}"  ></c:out></td>

                                                    <td><c:out value="${niv[1]}" ></c:out></td>

                                                    <td><c:out value="${niv[2]}" ></c:out></td>

                                                    <td><c:out value="${niv[3]}" ></c:out></td>

                                                    <td><c:out value="${niv[4]}"  ></c:out></td>

                                                    <td><c:out value="${niv[5]}" ></c:out></td>

                                                    <td><c:out value="${niv[6]}" ></c:out></td>

                                                    <td><c:out value="${niv[7]}" ></c:out></td>
                                                <td><c:out value="${niv[8]}" ></c:out></td>

                                                    <td ><a href=../../../../ProfServlet?action=delete&idTodelete=<c:out value="${niv[0]}">  </c:out> class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete  </a> </td>

                                                    <td> 
                                                        <a href="UpdateProfesseur.jsp?id=<c:url value="${niv[0]}"/>&nom=<c:url value="${niv[1]}"/>&prenom=<c:url value="${niv[2]}"/>&date=<c:url value="${niv[3]}"/>&adr=<c:url value="${niv[4]}"/>&cin=<c:url value="${niv[5]}"/>&tel=<c:url value="${niv[6]}"/>&login=<c:url value="${niv[7]}"/>&password=<c:url value="${niv[8]}"/>" class="btn btn-info btn-xs">

                                                        <i class="fa fa-pencil"></i> Edit </a></td>

                                            </tr>
                                        </c:forEach>


                                    </tbody>
                                </table>
                            </c:if>

                        </div>
                    </div>
                </div>

            </div>






        </div>



        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>

<!-- jQuery -->
<script src="../../../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../../../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../../../vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../../../vendors/nprogress/nprogress.js"></script>
<!-- Chart.js -->
<script src="../../../vendors/Chart.js/dist/Chart.min.js"></script>
<!-- gauge.js -->
<script src="../../../vendors/bernii/gauge.js/dist/gauge.min.js"></script>
<!-- bootstrap-progressbar -->
<script src="../../../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="../../../vendors/iCheck/icheck.min.js"></script>
<!-- Skycons -->
<script src="../../../vendors/skycons/skycons.js"></script>
<!-- Flot -->
<script src="../../../vendors/Flot/jquery.flot.js"></script>
<script src="../../../vendors/Flot/jquery.flot.pie.js"></script>
<script src="../../../vendors/Flot/jquery.flot.time.js"></script>
<script src="../../../vendors/Flot/jquery.flot.stack.js"></script>
<script src="../../../vendors/Flot/jquery.flot.resize.js"></script>
<!-- Flot plugins -->
<script src="../../js/flot/jquery.flot.orderBars.js"></script>
<script src="../../js/flot/date.js"></script>
<script src="../../js/flot/jquery.flot.spline.js"></script>
<script src="../../js/flot/curvedLines.js"></script>
<!-- jVectorMap -->
<script src="../../js/maps/jquery-jvectormap-2.0.3.min.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="../../js/moment/moment.min.js"></script>
<script src="../../js/datepicker/daterangepicker.js"></script>

<!-- Custom Theme Scripts -->
<script src="../../../build/js/custom.min.js"></script>

<!-- Flot -->

<script>
                                                $(document).ready(function() {
                                                    var data1 = [
                                                        [gd(2012, 1, 1), 17],
                                                        [gd(2012, 1, 2), 74],
                                                        [gd(2012, 1, 3), 6],
                                                        [gd(2012, 1, 4), 39],
                                                        [gd(2012, 1, 5), 20],
                                                        [gd(2012, 1, 6), 85],
                                                        [gd(2012, 1, 7), 7]
                                                    ];

                                                    var data2 = [
                                                        [gd(2012, 1, 1), 82],
                                                        [gd(2012, 1, 2), 23],
                                                        [gd(2012, 1, 3), 66],
                                                        [gd(2012, 1, 4), 9],
                                                        [gd(2012, 1, 5), 119],
                                                        [gd(2012, 1, 6), 6],
                                                        [gd(2012, 1, 7), 9]
                                                    ];
                                                    $("#canvas_dahs").length && $.plot($("#canvas_dahs"), [
                                                        data1, data2
                                                    ], {
                                                        series: {
                                                            lines: {
                                                                show: false,
                                                                fill: true
                                                            },
                                                            splines: {
                                                                show: true,
                                                                tension: 0.4,
                                                                lineWidth: 1,
                                                                fill: 0.4
                                                            },
                                                            points: {
                                                                radius: 0,
                                                                show: true
                                                            },
                                                            shadowSize: 2
                                                        },
                                                        grid: {
                                                            verticalLines: true,
                                                            hoverable: true,
                                                            clickable: true,
                                                            tickColor: "#d5d5d5",
                                                            borderWidth: 1,
                                                            color: '#fff'
                                                        },
                                                        colors: ["rgba(38, 185, 154, 0.38)", "rgba(3, 88, 106, 0.38)"],
                                                        xaxis: {
                                                            tickColor: "rgba(51, 51, 51, 0.06)",
                                                            mode: "time",
                                                            tickSize: [1, "day"],
                                                            //tickLength: 10,
                                                            axisLabel: "Date",
                                                            axisLabelUseCanvas: true,
                                                            axisLabelFontSizePixels: 12,
                                                            axisLabelFontFamily: 'Verdana, Arial',
                                                            axisLabelPadding: 10
                                                        },
                                                        yaxis: {
                                                            ticks: 8,
                                                            tickColor: "rgba(51, 51, 51, 0.06)",
                                                        },
                                                        tooltip: false
                                                    });

                                                    function gd(year, month, day) {
                                                        return new Date(year, month - 1, day).getTime();
                                                    }
                                                });
</script>
<!-- /Flot -->

<!-- jVectorMap -->
<script src="../../js/maps/jquery-jvectormap-world-mill-en.js"></script>
<script src="../../js/maps/jquery-jvectormap-us-aea-en.js"></script>
<script src="../../js/maps/gdp-data.js"></script>
<script>
                                                $(document).ready(function() {
                                                    $('#world-map-gdp').vectorMap({
                                                        map: 'world_mill_en',
                                                        backgroundColor: 'transparent',
                                                        zoomOnScroll: false,
                                                        series: {
                                                            regions: [{
                                                                    values: gdpData,
                                                                    scale: ['#E6F2F0', '#149B7E'],
                                                                    normalizeFunction: 'polynomial'
                                                                }]
                                                        },
                                                        onRegionTipShow: function(e, el, code) {
                                                            el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
                                                        }
                                                    });
                                                });
</script>
<!-- /jVectorMap -->

<!-- Skycons -->
<script>
    $(document).ready(function() {
        var icons = new Skycons({
            "color": "#73879C"
        }),
        list = [
            "clear-day", "clear-night", "partly-cloudy-day",
            "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
            "fog"
        ],
                i;

        for (i = list.length; i--; )
            icons.set(list[i], list[i]);

        icons.play();
    });
</script>
<!-- /Skycons -->

<!-- Doughnut Chart -->
<script>
    $(document).ready(function() {
        var options = {
            legend: false,
            responsive: false
        };

        new Chart(document.getElementById("canvas1"), {
            type: 'doughnut',
            tooltipFillColor: "rgba(51, 51, 51, 0.55)",
            data: {
                labels: [
                    "Symbian",
                    "Blackberry",
                    "Other",
                    "Android",
                    "IOS"
                ],
                datasets: [{
                        data: [15, 20, 30, 10, 30],
                        backgroundColor: [
                            "#BDC3C7",
                            "#9B59B6",
                            "#E74C3C",
                            "#26B99A",
                            "#3498DB"
                        ],
                        hoverBackgroundColor: [
                            "#CFD4D8",
                            "#B370CF",
                            "#E95E4F",
                            "#36CAAB",
                            "#49A9EA"
                        ]
                    }]
            },
            options: options
        });
    });
</script>
<!-- /Doughnut Chart -->

<!-- bootstrap-daterangepicker -->
<script>
    $(document).ready(function() {

        var cb = function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
            $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        };

        var optionSet1 = {
            startDate: moment().subtract(29, 'days'),
            endDate: moment(),
            minDate: '01/01/2012',
            maxDate: '12/31/2015',
            dateLimit: {
                days: 60
            },
            showDropdowns: true,
            showWeekNumbers: true,
            timePicker: false,
            timePickerIncrement: 1,
            timePicker12Hour: true,
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            opens: 'left',
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary',
            cancelClass: 'btn-small',
            format: 'MM/DD/YYYY',
            separator: ' to ',
            locale: {
                applyLabel: 'Submit',
                cancelLabel: 'Clear',
                fromLabel: 'From',
                toLabel: 'To',
                customRangeLabel: 'Custom',
                daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                firstDay: 1
            }
        };
        $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
        $('#reportrange').daterangepicker(optionSet1, cb);
        $('#reportrange').on('show.daterangepicker', function() {
            console.log("show event fired");
        });
        $('#reportrange').on('hide.daterangepicker', function() {
            console.log("hide event fired");
        });
        $('#reportrange').on('apply.daterangepicker', function(ev, picker) {
            console.log("apply event fired, start/end dates are " + picker.startDate.format('MMMM D, YYYY') + " to " + picker.endDate.format('MMMM D, YYYY'));
        });
        $('#reportrange').on('cancel.daterangepicker', function(ev, picker) {
            console.log("cancel event fired");
        });
        $('#options1').click(function() {
            $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
        });
        $('#options2').click(function() {
            $('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
        });
        $('#destroy').click(function() {
            $('#reportrange').data('daterangepicker').remove();
        });
    });
</script>
<!-- /bootstrap-daterangepicker -->

<!-- gauge.js -->
<script>
    var opts = {
        lines: 12,
        angle: 0,
        lineWidth: 0.4,
        pointer: {
            length: 0.75,
            strokeWidth: 0.042,
            color: '#1D212A'
        },
        limitMax: 'false',
        colorStart: '#1ABC9C',
        colorStop: '#1ABC9C',
        strokeColor: '#F0F3F3',
        generateGradient: true
    };
    var target = document.getElementById('foo'),
            gauge = new Gauge(target).setOptions(opts);

    gauge.maxValue = 6000;
    gauge.animationSpeed = 32;
    gauge.set(3200);
    gauge.setTextField(document.getElementById("gauge-text"));
</script>

<script>
    $(document).ready(function() {
        $('#birthday').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_4"
        }, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });
</script>
<!-- /gauge.js -->
</body>
</html>