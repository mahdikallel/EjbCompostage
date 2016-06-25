/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ado.SessionAdo;
import project.model.Niveau;
import project.model.Session;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Session sess = new Session();
    SessionAdo sessAdo = new SessionAdo();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SessionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SessionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        out.println("action " + action);


        if ("delete".equals(action)) {
            String idToDelete = request.getParameter("idTodelete");
            sess.setId(Integer.parseInt(idToDelete));
            sessAdo.deleteById(sess);
            getList(request, response);
           response.sendRedirect("/EJBProject/template/production/pages/Session/listeSession.jsp");
        }

        if ("edit".equals(action)) {
            String idToEdit = request.getParameter("idToedit");
            String libeleToEdit = request.getParameter("libelleToEdit");
            sess.setLibelle(libeleToEdit);
            sess.setId(Integer.parseInt(idToEdit));
            sessAdo.UpdateById(sess);
            getList(request, response);
             response.sendRedirect("/EJBProject/template/production/pages/Session/listeSession.jsp");
        }

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String libelle = request.getParameter("libelle");
        if ("libelle".equals("libelle")) {
            sess.setLibelle(libelle);
            sessAdo.insert(sess);
        }
        getList(request, response);
        response.sendRedirect("/EJBProject/template/production/pages/Session/listeSession.jsp");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void getList(HttpServletRequest request, HttpServletResponse respons) {
        HttpSession sesion1 = request.getSession();
        List<Session> sessList;
        sessList = sessAdo.getAll();
        sesion1.setAttribute("sessList", sessList);
    }
}
