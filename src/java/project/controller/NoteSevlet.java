/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ado.EtudiantAdo;
import project.ado.MatiereAdo;
import project.ado.NoteAdo;
import project.ado.SessionAdo;
import project.model.Enseignant;
import project.model.Etudiant;
import project.model.Groupe;
import project.model.Matiere;
import project.model.Note;
import project.model.Session;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "NoteSevlet", urlPatterns = {"/NoteSevlet"})
public class NoteSevlet extends HttpServlet {

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
    Note note = new Note();
    NoteAdo noteAdo = new NoteAdo();
    Etudiant etud = new Etudiant();
    EtudiantAdo etudAdo = new EtudiantAdo();
    Session sess = new Session();
    SessionAdo sessAdo = new SessionAdo();
    Matiere mat = new Matiere();
    MatiereAdo matAdo = new MatiereAdo();
    Groupe grp = new Groupe();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NoteSevlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NoteSevlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if ("delete".equals(action)) {

            String idToDelete = request.getParameter("idTodelete");
            out.println("id to del " + idToDelete);
            note.setId(Integer.parseInt(idToDelete));
            noteAdo.deleteById(note);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Note/listeNote.jsp");
        }

        if ("edit".equals(action)) {

            String id = request.getParameter("id");
            String tp = request.getParameter("tp");
            String ds = request.getParameter("ds");
            String exam = request.getParameter("exam");
            String pres = request.getParameter("pres");
            String idetud = request.getParameter("idetud");
            //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String idsess = request.getParameter("idsess");
            String idmat = request.getParameter("idMat");
            String idcompos = request.getParameter("idcompos");


            //out.println("id grp " + idToEdit + " \n nom groupe " + nameToEdit);
            // out.println("action " + action + " \n  abreviation " + abvToEdit +"\n id niv to edit "+idNivToEdit);
            note.setId(Integer.parseInt(id));
            note.setNoteDs(Float.parseFloat(ds));
            note.setNoteTp(Float.parseFloat(tp));
            note.setNoteExam(Float.parseFloat(exam));
            note.setNotePresence(Float.parseFloat(pres));

            etud.setId(Integer.parseInt(idetud));
            note.setEtudiant(etud);

            sess.setId(Integer.parseInt(idsess));
            note.setSession(sess);

            mat.setId(Integer.parseInt(idmat));
            note.setMatiere(mat);

            note.setNumCompostage(Integer.parseInt(idcompos));

            noteAdo.UpdateById(note);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Note/listeNote.jsp");
        }


        if ("updateFromEnsNC".equals(action)) {

            String id = request.getParameter("id");
            String tp = request.getParameter("tp");
            String ds = request.getParameter("ds");
            String pres = request.getParameter("pres");

            String idmat = request.getParameter("idmat");
            String idgrp = request.getParameter("idgrp");
            String sessionId = request.getParameter("sessionId");
            out.println("id  " + id + " \n tp " + tp);
            out.println("action " + action + " \n  ds " + ds + "\n pres " + pres);

            note.setId(Integer.parseInt(id));
            note.setNoteDs(Float.parseFloat(ds));
            note.setNoteTp(Float.parseFloat(tp));
            note.setNotePresence(Float.parseFloat(pres));

            mat.setId(Integer.parseInt(idmat));
            grp.setId(Integer.parseInt(idgrp));
            sess.setId(Integer.parseInt(sessionId));

            noteAdo.UpdateNoteNCById(note);
            HttpSession session1 = request.getSession();
            List ListEtudByGroup;
            ListEtudByGroup = matAdo.getListEtudByGroup(grp, mat, sess);
            session1.setAttribute("ListEtudByGroup", ListEtudByGroup);
            session1.setAttribute("TrColor", id);
            response.sendRedirect("/EJBProject/template/production/pages/EspaceEnseignant/SaisieNote.jsp");
        }


        if ("updateFromEnsC".equals(action)) {

            String id = request.getParameter("id");
            String exam = request.getParameter("exam");


            String idmat = request.getParameter("idmat");
            String idgrp = request.getParameter("idgrp");
            String sessionId = request.getParameter("sessionId");

            note.setId(Integer.parseInt(id));
            note.setNoteExam(Float.parseFloat(exam));


            mat.setId(Integer.parseInt(idmat));
            grp.setId(Integer.parseInt(idgrp));
            sess.setId(Integer.parseInt(sessionId));

            noteAdo.UpdateNoteCById(note);
            HttpSession session1 = request.getSession();
            List ListEtudByGroup;
            ListEtudByGroup = matAdo.getListEtudByGroupComposte(grp, mat, sess);
            session1.setAttribute("ListEtudByGroupC", ListEtudByGroup);
            session1.setAttribute("TrColor", id);
            response.sendRedirect("/EJBProject/template/production/pages/EspaceEnseignant/SaisieNoteC.jsp");
        }
        if ("ajoutCompostage".equals(action)) {
            HttpSession session1 = request.getSession();
            ArrayList<Note> Arraynote = new ArrayList<Note>();
            Arraynote = (ArrayList<Note>) session1.getAttribute("ArrayNoteCompost");
            Iterator<Note> it = Arraynote.iterator();

            while (it.hasNext()) {
                Note s = it.next();
                out.println("id session " + s.getSession().getId());
                out.println("com pso" + s.getNumCompostage());
                out.println("id mat" + s.getMatiere().getId());
                out.println("id etud" + s.getEtudiant().getId());

                etud.setId(s.getEtudiant().getId());
                note.setEtudiant(etud);

                sess.setId(s.getSession().getId());
                note.setSession(sess);

                mat.setId(s.getMatiere().getId());
                note.setMatiere(mat);

                note.setNumCompostage(s.getNumCompostage());

                //out.println("fn"+noteAdo.verifCopostageByMatAndSessAndEtud(note));
                if (noteAdo.verifCopostageByMatAndSessAndEtud(note) == 0) {
                    noteAdo.insert(note);
                    session1.setAttribute("etudListBygroupe", null);
                } else {
                }

            }

            session1.setAttribute("NomSession", null);
            session1.setAttribute("afficheNomNiv", null);
            session1.setAttribute("afficheGroupe", null);
            session1.setAttribute("idMatCom", null);
            session1.setAttribute("afficheInfo", null);
            session1.setAttribute("matListByGroupe", null);
            response.sendRedirect("/EJBProject/template/production/pages/Compostage/GererCompostage.jsp");
        }
        
        if("annulerCompostage".equals(action)){
            HttpSession session1 = request.getSession();
            session1.setAttribute("NomSession", null);
            session1.setAttribute("afficheNomNiv", null);
            session1.setAttribute("afficheGroupe", null);
            session1.setAttribute("idMatCom", null);
            session1.setAttribute("afficheInfo", null);
            session1.setAttribute("matListByGroupe", null);
            response.sendRedirect("/EJBProject/template/production/pages/Compostage/GererCompostage.jsp");
        }

        if ("listNoteEtudByMat".equals(action)) {


            String idMat = request.getParameter("idMat");
            String idEtud = request.getParameter("idEtud");
            String vc = request.getParameter("vc");
            String vtp = request.getParameter("vtp");
            String vtd = request.getParameter("vtd");
            String coeff = request.getParameter("coeff");
            String cred = request.getParameter("cred");
            String lib = request.getParameter("lib");
            out.println("id idMat " + idMat);
           
            mat.setId(Integer.parseInt(idMat));
            etud.setId(Integer.parseInt(idEtud));
            HttpSession session1 = request.getSession();
            List ListNoteEtud;
            ListNoteEtud = noteAdo.getListNoteEtudByMat(mat, etud);
            session1.setAttribute("ListNoteEtud", ListNoteEtud);
            session1.setAttribute("vc", vc);
            session1.setAttribute("vtp", vtp);
            session1.setAttribute("vtd", vtd);
            session1.setAttribute("coeff", coeff);
            session1.setAttribute("cred", cred);
            session1.setAttribute("lib", lib);
              response.sendRedirect("/EJBProject/template/production/pages/EspaceEtudiant/mesnotes.jsp");

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
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");




        if ("add".equals(action)) {


            String tp = request.getParameter("tp");
            String ds = request.getParameter("ds");
            String exam = request.getParameter("exam");
            String pres = request.getParameter("pres");
            String idetud = request.getParameter("idetud");
            //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String idsess = request.getParameter("idsess");
            String idmat = request.getParameter("idMat");
            String idcompos = request.getParameter("idcompos");


            /*  out.println("id ds " + ds + " \n tp " + tp);
             * out.println("id grp " + tp + " \n nom groupe " + ds);
             * out.println("pres " + pres + " \n  idetud " + idetud + "\n  exam " + exam);
             * out.println("idsess " + idsess + " \n  idmat " + idmat + "\n id idcompos " + idcompos);*/
            note.setNoteDs(Float.parseFloat(ds));
            note.setNoteTp(Float.parseFloat(tp));
            note.setNoteExam(Float.parseFloat(exam));
            note.setNotePresence(Float.parseFloat(pres));

            etud.setId(Integer.parseInt(idetud));
            note.setEtudiant(etud);

            sess.setId(Integer.parseInt(idsess));
            note.setSession(sess);

            mat.setId(Integer.parseInt(idmat));
            note.setMatiere(mat);

            note.setNumCompostage(Integer.parseInt(idcompos));

            noteAdo.insert(note);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Note/listeNote.jsp");
        }
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
        List<Enseignant> noteList;
        noteList = noteAdo.getAll();

        sesion1.setAttribute("noteList", noteList);
    }
}
