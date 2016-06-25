package org.apache.jsp.template.production;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <title>Custom login/register lock page freebie by @remtsoy</title>\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/lineicons.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/styles.css\">\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("\n");
      out.write("            input[type=checkbox]:not(old) + label > span,\n");
      out.write("            input[type=radio   ]:not(old) + label > span{\n");
      out.write("                display          : inline-block;\n");
      out.write("                width            : 0.875em;\n");
      out.write("                height           : 0.875em;\n");
      out.write("                margin           : 0.25em 0.5em 0.25em 0.25em;\n");
      out.write("                border           : 0.0625em solid rgb(192,192,192);\n");
      out.write("                border-radius    : 0.25em;\n");
      out.write("                background       : rgb(224,224,224);\n");
      out.write("                background-image :    -moz-linear-gradient(rgb(240,240,240),rgb(224,224,224));\n");
      out.write("                background-image :     -ms-linear-gradient(rgb(240,240,240),rgb(224,224,224));\n");
      out.write("                background-image :      -o-linear-gradient(rgb(240,240,240),rgb(224,224,224));\n");
      out.write("                background-image : -webkit-linear-gradient(rgb(240,240,240),rgb(224,224,224));\n");
      out.write("                background-image :         linear-gradient(rgb(240,240,240),rgb(224,224,224));\n");
      out.write("                vertical-align   : bottom;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"main-wrapper\">\n");
      out.write("            <div class=\"main-wrapper-bg\" id=\"main-wrapper-bg\"></div>\n");
      out.write("            <canvas id=\"my-canvas\" class=\"my-canvas\"></canvas>\n");
      out.write("            <div class=\"main-wrapper-mask\"></div>\n");
      out.write("            <div class=\"main-container\">\n");
      out.write("                <div class=\"container\">\n");
      out.write("                    <div class=\"row row-main\">\n");
      out.write("\n");
      out.write("                        <div class=\"col-md-4 col\">\n");
      out.write("                            <h1 class=\"main-title\">Authentification</h1>\n");
      out.write("                            <form method=\"POST\" action=\"../../Authentification\">\n");
      out.write("\n");
      out.write("                                <select  class=\"form-control\" required name=\"groupe\" onchange=\"window.location.href = '../../Authentification?idCat=' + this.options[this.selectedIndex].value + '&action=selectTypeUser';\">\n");
      out.write("\n");
      out.write("                                    ");

                                        int x;
                                        if (session.getAttribute("idCat") == null) {
                                            x = -1;

                                        } else {
                                            x = Integer.parseInt(session.getAttribute("idCat").toString());
                                        }
                                    
      out.write("\n");
      out.write("                                    <option value=\"-1\"\n");
      out.write("                                            ");



                                                if (x == -1) {
                                                    out.print("selected");
                                                }
      out.write(">Choisissez le type</option>\n");
      out.write("\n");
      out.write("                                    <option value=\"0\" ");



                                        if (x == 0) {
                                            out.print("selected");
                                        }
      out.write(">Administrateur</option>\n");
      out.write("                                    <option value=\"1\"\n");
      out.write("                                            ");


                                                if (x == 1) {
                                                    out.print("selected");
                                                }
      out.write(">Enseignant</option>\n");
      out.write("                                    <option value=\"2\" \n");
      out.write("                                            ");


                                                if (x == 2) {
                                                    out.print("selected");
                                                }
      out.write(">Etudiant</option>\n");
      out.write("                                </select>\n");
      out.write("\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <input type=\"text\"  hidden name=\"action\" value=\"");

                                        if (session.getAttribute("type") == null) {
                                            out.print("noSelect");
                                        } else {
                                            out.print(session.getAttribute("type"));
                                        }
                                           
      out.write("\">\n");
      out.write("\n");
      out.write("                                    <input type=\"text\" class=\"form-control\" placeholder=\"Username\"  name=\"username\">\n");
      out.write("                                    <div class=\"form-icon lin lin-user\"></div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <input type=\"password\" class=\"form-control\" placeholder=\"Password\" name=\"password\">\n");
      out.write("                                    <div class=\"form-icon lin lin-key\"></div>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                                <input type=\"submit\" value=\"Log in\" class=\"btn btn-default btn-ghost\">\n");
      out.write("                            </form>\n");
      out.write("                            <a href=\"#\" class=\"forgot-password\">\n");
      out.write("\n");
      out.write("                                ");
 if (session.getAttribute("failedEns") != null) {
      out.write("\n");
      out.write("                                <div class=\"alert alert-danger alert-dismissible fade in\" role=\"alert\">\n");
      out.write("                                    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>\n");
      out.write("                                    </button>\n");
      out.write("                                    <h5>  <strong>Erreur !</strong> Vérifier votre login ou password</h5>\n");
      out.write("                                </div>\n");
      out.write("                                ");
                                    }
                                    session.setAttribute("failedEns", null);
                                
      out.write("\n");
      out.write("\n");
      out.write("                            </a>\n");
      out.write("\n");
      out.write("                            <a href=\"#\" class=\"forgot-password\">\n");
      out.write("\n");
      out.write("                                ");
 if (session.getAttribute("failedAdmin") != null) {
      out.write("\n");
      out.write("                                <div class=\"alert alert-danger alert-dismissible fade in\" role=\"alert\">\n");
      out.write("                                    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>\n");
      out.write("                                    </button>\n");
      out.write("                                  <h5>   <strong>Erreur !</strong> Vérifier votre login ou password</h5>\n");
      out.write("                                </div>\n");
      out.write("                                ");
                                    }
                                    session.setAttribute("failedAdmin", null);
                                
      out.write("\n");
      out.write("\n");
      out.write("                            </a>\n");
      out.write("\n");
      out.write("                            <a href=\"#\" class=\"forgot-password\">\n");
      out.write("\n");
      out.write("                                ");
 if (session.getAttribute("failedEtud") != null) {
      out.write("\n");
      out.write("                                <div class=\"alert alert-danger alert-dismissible fade in\" role=\"alert\">\n");
      out.write("                                    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>\n");
      out.write("                                    </button>\n");
      out.write("                                   <h5>  <strong>Erreur !</strong> Vérifier votre login ou password</h5>\n");
      out.write("                                </div>\n");
      out.write("                                ");
                                    }
                                    session.setAttribute("failedEtud", null);
                                
      out.write("\n");
      out.write("\n");
      out.write("                            </a>\n");
      out.write("\n");
      out.write("\n");
      out.write("                            <a href=\"#\" class=\"forgot-password\">\n");
      out.write("\n");
      out.write("                                ");
 if (session.getAttribute("choixType") != null) {
      out.write("\n");
      out.write("                                <div class=\"alert alert-warning alert-dismissible fade in\" role=\"alert\">\n");
      out.write("                                    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>\n");
      out.write("                                    </button>\n");
      out.write("                                    <h5> <strong>Wargning !</strong> Vous devez choisir le type d'utilistaeur</h5>\n");
      out.write("                                </div>\n");
      out.write("                                ");
                                    }
                                    session.setAttribute("choixType", null);
                                
      out.write("\n");
      out.write("\n");
      out.write("                            </a>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap.js\"></script>\n");
      out.write("        <script src=\"js/canvas.js\"></script>\n");
      out.write("        <script src=\"js/script.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
