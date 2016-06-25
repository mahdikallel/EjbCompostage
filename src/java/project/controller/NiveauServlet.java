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
import project.ado.NiveauAdo;
import project.model.Niveau;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "NiveauSer", urlPatterns = {"/NiveauSer"})
public class NiveauServlet extends HttpServlet {

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
    private NiveauAdo nivAdo = new NiveauAdo();
    private Niveau n = new Niveau();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NiveauServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NiveauServlet at " + request.getContextPath() + "</h1>");
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
        //  processRequest(request, response);
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        //out.println("to del " + idToDelete.toString());

        String action = request.getParameter("action");
        out.println("action " + action);


        if ("delete".equals(action)) {
            String idToDelete = request.getParameter("idTodelete");
            n.setId(Integer.parseInt(idToDelete));
            nivAdo.deleteById(n);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Niveau/listeNiveau.jsp");
        }

        if ("edit".equals(action)) {
            String idToEdit = request.getParameter("idToedit");
            String libeleToEdit = request.getParameter("libelleToEdit");
            n.setLibelle(libeleToEdit);
            n.setId(Integer.parseInt(idToEdit));
            nivAdo.UpdateById(n);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Niveau/listeNiveau.jsp");
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
            n.setLibelle(libelle);
            nivAdo.insert(n);
        }
        getList(request, response);
        response.sendRedirect("/EJBProject/template/production/pages/Niveau/listeNiveau.jsp");

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
        List<Niveau> niveauList;
        niveauList = nivAdo.getAll();
        
        sesion1.setAttribute("nivList", niveauList);
    }
}
