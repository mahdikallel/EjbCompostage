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
import project.ado.MatiereAdo;
import project.ado.ProfesseurAdo;
import project.ado.SessionAdo;
import project.model.Enseignant;
import project.model.Groupe;
import project.model.Matiere;
import project.model.Niveau;
import project.model.Session;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "EspaceEnseignant", urlPatterns = {"/EspaceEnseignant"})
public class EspaceEnseignant extends HttpServlet {

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
    private ProfesseurAdo profAdo = new ProfesseurAdo();
    private Enseignant ens = new Enseignant();
    private SessionAdo sessAdo = new SessionAdo();
    private MatiereAdo matAdo = new MatiereAdo();
    private Groupe grp = new Groupe();
    private Matiere mat = new Matiere();
    private Session sess = new Session();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EspaceEnseignant</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EspaceEnseignant at " + request.getContextPath() + "</h1>");
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


        if ("saisieNonComposte".equals(action)) {
            String idmat = request.getParameter("idmat");
            String idgrp = request.getParameter("idgrp");
            String idniv = request.getParameter("niv");
            String sessionId = request.getParameter("sessionId");
            out.print("idmat  " + idmat + "  idgrp  " + idgrp + "   niv  " + idniv + "id session" + sessionId);
            HttpSession session1 = request.getSession();

            List ListEtudByGroup;

            mat.setId(Integer.parseInt(idmat));
            grp.setId(Integer.parseInt(idgrp));
            sess.setId(Integer.parseInt(sessionId));
            ListEtudByGroup = matAdo.getListEtudByGroup(grp, mat, sess);

            session1.setAttribute("ListEtudByGroup", ListEtudByGroup);
            request.setAttribute("idgrp", idgrp);
            request.setAttribute("niv", idniv);
            session1.setAttribute("idmat", idmat);
            // System.out.println("id matiere pour saisir les note est : "+idmat);
            // out.print(request.getHeader("Referer"));
            session1.setAttribute("back", request.getHeader("Referer"));
            response.sendRedirect("/EJBProject/template/production/pages/EspaceEnseignant/SaisieNote.jsp");
        }


        if ("saisieComposte".equals(action)) {
            String idmat = request.getParameter("idmat");
            String idgrp = request.getParameter("idgrp");
            String idniv = request.getParameter("niv");
            String sessionId = request.getParameter("sessionId");
            out.print("idmat  " + idmat + "  idgrp  " + idgrp + "   niv  " + idniv + "id session" + sessionId);
            HttpSession session1 = request.getSession();

            List ListEtudByGroup;

            mat.setId(Integer.parseInt(idmat));
            grp.setId(Integer.parseInt(idgrp));
            sess.setId(Integer.parseInt(sessionId));
            ListEtudByGroup = matAdo.getListEtudByGroupComposte(grp, mat, sess);

            session1.setAttribute("ListEtudByGroupC", ListEtudByGroup);
            request.setAttribute("idgrp", idgrp);
            request.setAttribute("niv", idniv);
            session1.setAttribute("idmat", idmat);
            // System.out.println("id matiere pour saisir les note est : "+idmat);
            session1.setAttribute("back", request.getHeader("Referer"));
            response.sendRedirect("/EJBProject/template/production/pages/EspaceEnseignant/SaisieNoteC.jsp");
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
        // processRequest(request, response);
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

    /*  public void getListEtudByGroupeForMatiere(HttpServletRequest request, HttpServletResponse respons, int idGrp) throws IOException {
     HttpSession session1 = request.getSession();
     List ListMatiereForEns;

     grp.setId(idGrp);
     ListMatiereForEns = matAdo.getListMatiereByGroupeAndIdEtudiant(grp);

     session1.setAttribute("ListMatiereForEns", ListMatiereForEns);

     }*/
}
