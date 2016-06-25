<%@page import="project.ado.ProfesseurAdo"%>
<%@page import="project.controller.RevCrypt"%>
<%@page import="project.ado.MatiereAdo"%>
<%@page import="project.ado.SessionAdo"%>
<%@page import="project.ado.EtudiantAdo"%>
<%@page import="project.controller.NoteSevlet"%>
<%@page import="project.ado.NoteAdo"%>
<%@page import="project.controller.SessionServlet"%>
<%@page import="project.controller.MatiereServlet"%>
<%@page import="project.model.Enseignant"%>
<%@page import="project.controller.EtudiantServlet"%>
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
        if (session.getAttribute("userEns") == null) {
            // response.sendRedirect("/EJBProject/template/production/login.jsp");
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
                                <h2><% out.print(session.getAttribute("userEns"));%></h2>
                            </div>
                        </div>
                        <!-- /menu profile quick info -->

                        <br />

                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <h3>General</h3>
                                <ul class="nav side-menu">
                                    <li><a href="acceuil.jsp"><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>



                                    </li>
                                    <li><a><i class="fa fa-user"></i> Profil<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="Profil.jsp" >Gérer</a></li>

                                        </ul>
                                    </li>

                                    <c:forEach items="${sessionScope.profNivList}" var="niv">
                                        <li><a><i class="fa fa-table"></i> <c:out value="${niv[0]}"  ></c:out> <span class="fa fa-chevron-down"></span></a>
                                                <ul class="nav child_menu">
                                                <c:set var="idNiveau" value="${niv[1]}"  scope="request" />
                                                <%
                                                    ProfesseurAdo profAdo = new ProfesseurAdo();
                                                    ProfServlet profServ = new ProfServlet();
                                                    // out.print("<h1>" + profAdo.getIdByLogin(session.getAttribute("userEns").toString()) + "</h1>");
                                                    profServ.getListGroupeEnsByNiveau(request, response, profAdo.getIdByLogin(session.getAttribute("userEns").toString()), Integer.parseInt(request.getAttribute("idNiveau").toString()));
                                                %>


                                                <c:forEach items="${sessionScope.profGroupList}" var="grp">
                                                    <li><a href="UpdateNote.jsp?idgrp=<c:out value="${grp[1]}"></c:out>&niv=<c:url value="${niv[0]}"  ></c:url>">        <c:out value="${grp[0]}"  ></c:out></a></li>
                                                    </c:forEach>

                                            </ul>
                                        </li>
                                    </c:forEach>                               




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
                                    <img src="../../images/img.jpg" alt=""><% out.print(session.getAttribute("userEns"));%>
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

                <div style="height:2050px;">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2> Liste des notes </h2>
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
                        <div class="x_content">
                            <h2> <div class='count red' >
                                    <%
                                        MatiereAdo matAdo = new MatiereAdo();
                                        out.print(matAdo.getlibelleMatiereById(Integer.parseInt(session.getAttribute("idmat").toString())));

                                    %>
                                </div>
                            </h2>
                            <table class="table table-striped">

                                <tbody>
                                    <tr>


                                        <th> Numero Compostage</th>


                                        <th>Note Examen</th>

                                        <th>update </th>

                                    </tr>



                                    <c:forEach items="${sessionScope.ListEtudByGroupC}" var="niv">
                                    <form method="GET" action="../../../../NoteSevlet">
                                        <c:set var="ToEdit" value="${niv[0]}" scope="session" />
                                        <% //out.print(session.getAttribute("TrColor"));
                                            //out.print(session.getAttribute("ToEdit"));%>

                                        <tr
                                            <%
                                                if (session.getAttribute("TrColor") != null) {

                                                    int e = Integer.parseInt(session.getAttribute("ToEdit").toString());
                                                    int tr = Integer.parseInt(session.getAttribute("TrColor").toString());
                                                    if (e == tr) {
                                                        out.print("bgcolor=#BCF5A9");
                                                    }
                                                }


                                            %>
                                            >

                                            <!--tp-->
                                        <input type="text" name="id" hidden value=<c:out value="${niv[0]}" > </c:out> >


                                            <input hidden type="text" name="action" value="updateFromEnsC">

                                            <input hidden type="text" name="idmat" value="<%out.print(session.getAttribute("idmat").toString());%>">
                                        <input hidden type="text" name="idgrp" value="<c:out value="${niv[4]}" ></c:out>">
                                        <input hidden type="text" name="sessionId" value="<c:out value="${niv[3]}" ></c:out>">




                                            <td><c:out value="${niv[2]}" ></c:out></td>





                                            <td>

                                                <input  min="0" max="20" type="number" name="exam" step="0.01" class="form-control" id="" value=<c:out value="${niv[1]}" ></c:out> required>

                                            </td>
                                            <td> 
                                                <!--a href="../../../../NoteSevlet?id=<%--c:url value="${niv[0]}"/>&tp=<c:url value="${niv[4]}"/>&ds=<c:url value="${niv[5]}"/>&pres=<c:url value="${niv[7]}"/--%>&action=updateFromEnsNC" -->

                                            <input type="submit"  class="btn btn-info btn-xs"  value="Update"></a></td>



                                        </tr>
                                    </form>
                                </c:forEach>
                                <%session.setAttribute("TrColor", null);%>

                                </tbody>
                            </table>
                            <a href=<%= session.getAttribute("back")%>>

                               
                                <div  style="margin-left: 1000px; width: 160px;">
                                    
                                    <button type="button"  class="btn btn-primary">Terminer</button>
                                  
                                </div>
                            </a>
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