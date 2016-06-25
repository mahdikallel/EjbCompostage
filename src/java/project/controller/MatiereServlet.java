/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javassist.bytecode.Descriptor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ado.EtudiantAdo;
import project.ado.MatiereAdo;
import project.ado.SessionAdo;
import project.model.Enseignant;
import project.model.Etudiant;
import project.model.Groupe;
import project.model.Matiere;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "MatiereServlet", urlPatterns = {"/MatiereServlet"})
public class MatiereServlet extends HttpServlet {

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
    Matiere mat = new Matiere();
    MatiereAdo matAdo = new MatiereAdo();
    Groupe grp = new Groupe();
    Enseignant ens = new Enseignant();
    Etudiant etud = new Etudiant();
    EtudiantAdo etudAdo = new EtudiantAdo();
    SessionAdo seesAdo = new SessionAdo();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MatiereServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MatiereServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");


        if ("compostage".equals(action)) {
            String idgrp = request.getParameter("idgrp");
            String idmat = request.getParameter("idMat");
            grp.setId(Integer.parseInt(idgrp));
            etudAdo.getListEtudiantByGroupe(grp);

            


            List<Etudiant> etudListBygroupe;
            etudListBygroupe = etudAdo.getListEtudiantByGroupe(grp);

            HttpSession session1 = request.getSession();
            session1.setAttribute("etudListBygroupe", etudListBygroupe);
            session1.setAttribute("idMatCom", idmat);


           
            response.sendRedirect("/EJBProject/template/production/pages/Compostage/GererCompostage.jsp");

        }

        if ("selectSession".equals(action)) {
            String idsess = request.getParameter("idSession");
            out.print(idsess);
            HttpSession session1 = request.getSession();
            session1.setAttribute("idSess", idsess);

            session1.setAttribute("NomSession", seesAdo.getlibelleSessiontById(Integer.parseInt(idsess)));
            session1.setAttribute("afficheInfo", 1);
            session1.setAttribute("afficheGroupe", "");

            response.sendRedirect("/EJBProject/template/production/pages/Compostage/GererCompostage.jsp");

        }
        
         if ("selectSessionFromEns".equals(action)) {
            String idsess = request.getParameter("idSession");
            //out.print(idsess);
            HttpSession session1 = request.getSession();
            session1.setAttribute("idSess", idsess);

            session1.setAttribute("NomSession", seesAdo.getlibelleSessiontById(Integer.parseInt(idsess)));
           
          //  out.print(request.getHeader("Referer"));
            response.sendRedirect(request.getHeader("Referer"));

        }

        if ("listeEtudGrp".equals(action)) {

            String idgrp = request.getParameter("idgrp");
            String nomGroupe = request.getParameter("nomGroupe");
            String nomNiveau = request.getParameter("nomNiveau");
            out.print("nomNiveau" + nomNiveau + "nomGroupe " + nomGroupe);
            grp.setId(Integer.parseInt(idgrp));
            out.print(grp.getId());
            HttpSession sesion1 = request.getSession();


            List<Matiere> matListByGroupe;
            matListByGroupe = matAdo.getListMatiereByGroupe(grp);

            sesion1.setAttribute("afficheNomNiv", nomNiveau);
            sesion1.setAttribute("afficheGroupe", nomGroupe);
            sesion1.setAttribute("matListByGroupe", matListByGroupe);
            response.sendRedirect("/EJBProject/template/production/pages/Compostage/GererCompostage.jsp");

        }





        if ("delete".equals(action)) {
            String idToDelete = request.getParameter("idTodelete");
            mat.setId(Integer.parseInt(idToDelete));
            matAdo.deleteById(mat);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Matiere/listeMatiere.jsp");
        }

        if ("edit".equals(action)) {

            String nom = request.getParameter("nom");
            String vc = request.getParameter("vc");
            String vtp = request.getParameter("vtp");
            String vtd = request.getParameter("vtd");
            String coeff = request.getParameter("coeff");
            String cred = request.getParameter("cred");
            String groupe = request.getParameter("groupe");
            String ense = request.getParameter("ens");

            mat.setLibelle(nom);
            mat.setCoefficient(Integer.parseInt(coeff));
            mat.setCredit(Integer.parseInt(cred));
            mat.setVolumeCour(Float.parseFloat(vc));
            mat.setVolumeTd(Float.parseFloat(vtd));
            mat.setVolumeTp(Float.parseFloat(vtp));

            ens.setId(Integer.parseInt(ense));
            mat.setEnseignant(ens);

            grp.setId(Integer.parseInt(groupe));
            mat.setGroupe(grp);


            matAdo.UpdateById(mat);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Etudiant/listeEtudiant.jsp");

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
        //  processRequest(request, response);

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            PrintWriter out = response.getWriter();
            String nom = request.getParameter("nom");
            String vc = request.getParameter("vc");
            String vtp = request.getParameter("vtp");
            String vtd = request.getParameter("vtd");
            String coeff = request.getParameter("coeff");
            String cred = request.getParameter("cred");
            String groupe = request.getParameter("groupe");
            String ense = request.getParameter("ens");

            mat.setLibelle(nom);
            mat.setCoefficient(Integer.parseInt(coeff));
            mat.setCredit(Integer.parseInt(cred));
            mat.setVolumeCour(Float.parseFloat(vc));
            mat.setVolumeTd(Float.parseFloat(vtd));
            mat.setVolumeTp(Float.parseFloat(vtp));

            ens.setId(Integer.parseInt(ense));
            mat.setEnseignant(ens);

            grp.setId(Integer.parseInt(groupe));
            mat.setGroupe(grp);

            matAdo.insert(mat);



        }
        getList(request, response);
        response.sendRedirect("/EJBProject/template/production/pages/Matiere/listeMatiere.jsp");
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
        List<Matiere> matList;
        matList = matAdo.getAll();

        sesion1.setAttribute("matList", matList);
    }

    public void getListMatiereByIdEns(HttpServletRequest request, HttpServletResponse respons, int idEns) throws IOException {
        PrintWriter out = respons.getWriter();
        HttpSession sesion1 = request.getSession();
        List<Matiere> matEnsList;
        matEnsList = matAdo.getMatiereByIdEns(idEns);


        sesion1.setAttribute("matEnsList", matEnsList);
    }
}
