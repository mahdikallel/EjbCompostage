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
import project.ado.GroupeAdo;
import project.ado.NiveauAdo;
import project.model.Groupe;
import project.model.Niveau;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "GroupeServlet", urlPatterns = {"/GroupeServlet"})
public class GroupeServlet extends HttpServlet {

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
    private GroupeAdo grpAdo = new GroupeAdo();
    private NiveauAdo nivAdo = new NiveauAdo();
    private Groupe grp = new Groupe();
    private Niveau niv = new Niveau();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GroupeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GroupeServlet at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        //out.println("action " + action);
        String username = request.getParameter("us");
        out.print("testt" + username);

        if ("delete".equals(action)) {
            String idToDelete = request.getParameter("idTodelete");
            grp.setId(Integer.parseInt(idToDelete));
            grpAdo.deleteById(grp);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Groupe/listeGroupe.jsp");
        }

        if ("edit".equals(action)) {

            String idToEdit = request.getParameter("idToedit");
            String nameToEdit = request.getParameter("nameToEdit");
            String abvToEdit = request.getParameter("abvToEdit");
            String idNivToEdit = request.getParameter("niveau");
            //out.println("id grp " + idToEdit + " \n nom groupe " + nameToEdit);
            // out.println("action " + action + " \n  abreviation " + abvToEdit +"\n id niv to edit "+idNivToEdit);
            grp.setId(Integer.parseInt(idToEdit));
            grp.setNomGourpe(nameToEdit);
            grp.setAbreviation(abvToEdit);

            niv.setId(Integer.parseInt(idNivToEdit));
            grp.setNiveau(niv);

            grpAdo.UpdateById(grp);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Groupe/listeGroupe.jsp");
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
        //processRequest(request, response);



        PrintWriter out = response.getWriter();
        String niveau = request.getParameter("niveau");
        String action = request.getParameter("add");
        String nomgrp = request.getParameter("nomgpr");
        String abreviation = request.getParameter("abreviation");


        String username = request.getParameter("username");
        out.print("action " + action  );

        out.print("id Niveau " + niveau + " nom groupe " + nomgrp);
        out.print(" abreviation " + abreviation );

        if ("add".equals(action)) {
            niv.setId(Integer.parseInt(niveau));
            grp.setNiveau(niv);
            grp.setNomGourpe(nomgrp);
            grp.setAbreviation(abreviation);
            grpAdo.insert(grp);
        }
        getList(request, response);
        response.sendRedirect("/EJBProject/template/production/pages/Groupe/listeGroupe.jsp");
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

    public void getList(HttpServletRequest request, HttpServletResponse respons) throws IOException {
        PrintWriter out = respons.getWriter();
        HttpSession sesion1 = request.getSession();
        List<Groupe> grpList;
        grpList = grpAdo.getAll();

        sesion1.setAttribute("grpList", grpList);
    }

    public void getListNivau(HttpServletRequest request, HttpServletResponse respons) throws IOException {
        PrintWriter out = respons.getWriter();
        HttpSession sesion1 = request.getSession();
        List<Niveau> niveauList;
        niveauList = nivAdo.getAll();
        sesion1.setAttribute("nivList", niveauList);
    }

   
}
