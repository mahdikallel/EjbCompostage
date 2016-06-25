/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ado.MatiereAdo;
import project.ado.ProfesseurAdo;
import project.model.Enseignant;
import project.model.Groupe;
import project.model.Matiere;
import project.model.Niveau;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "ProfServlet", urlPatterns = {"/ProfServlet"})
public class ProfServlet extends HttpServlet {

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
    Enseignant prof = new Enseignant();
    ProfesseurAdo profAdo = new ProfesseurAdo();
    RevCrypt crypt = new RevCrypt();
    Niveau niv = new Niveau();
    MatiereAdo matAdo = new MatiereAdo();
    Matiere mat = new Matiere();
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
            out.println("<title>Servlet ProfServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfServlet at " + request.getContextPath() + "</h1>");
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

        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String idToDelete = request.getParameter("idTodelete");
            prof.setId(Integer.parseInt(idToDelete));
            profAdo.deleteById(prof);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Professeur/listeProfesseur.jsp");
        }

        if ("edit".equals(action)) {

            String id = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adr = request.getParameter("adr");
            String tel = request.getParameter("tel");
            String date = request.getParameter("date");
            //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String cin = request.getParameter("cin");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            //out.println("id grp " + idToEdit + " \n nom groupe " + nameToEdit);
            // out.println("action " + action + " \n  abreviation " + abvToEdit +"\n id niv to edit "+idNivToEdit);

            out.println("date 1  " + date);
            try {
                prof.setId(Integer.parseInt(id));
                prof.setNom(nom);
                prof.setPrenom(prenom);
                prof.setAdresse(adr);
                prof.setTel(tel);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(date);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());

                out.println("date" + sql);
                prof.setDateNaiss(sql);
                prof.setCin(Integer.parseInt(cin));
                prof.setLogin(login);
                crypt.setClef("mahdi");
                prof.setPassword(RevCrypt.encode(crypt.getClef(), password));
                profAdo.UpdateById(prof);

            } catch (ParseException ex) {
                Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Professeur/listeProfesseur.jsp");
        }


        if ("editFromEspaceEns".equals(action)) {

            String id = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adr = request.getParameter("adr");
            String tel = request.getParameter("tel");
            String date = request.getParameter("date");
            //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String cin = request.getParameter("cin");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            //out.println("id grp " + idToEdit + " \n nom groupe " + nameToEdit);
            // out.println("action " + action + " \n  abreviation " + abvToEdit +"\n id niv to edit "+idNivToEdit);

            out.println("date 1  " + date);
            try {
                prof.setId(Integer.parseInt(id));
                prof.setNom(nom);
                prof.setPrenom(prenom);
                prof.setAdresse(adr);
                prof.setTel(tel);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(date);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());

                out.println("date" + sql);
                prof.setDateNaiss(sql);
                prof.setCin(Integer.parseInt(cin));
                prof.setLogin(login);
                crypt.setClef("mahdi");
                prof.setPassword(RevCrypt.encode(crypt.getClef(), password));
                profAdo.UpdateById(prof);

            } catch (ParseException ex) {
                Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/EspaceEnseignant/acceuil.jsp");
        }
        if ("EspaceEns".equals(action)) {
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
        //   processRequest(request, response);
        PrintWriter out = response.getWriter();
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adr = request.getParameter("adr");
        String tel = request.getParameter("tel");
        String date = request.getParameter("date");
        //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String cin = request.getParameter("cin");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("add".equals(action)) {


            prof.setLogin(login);
            if (profAdo.verifLogin(prof) == 0) {
                try {
                    prof.setNom(nom);
                    prof.setPrenom(prenom);
                    prof.setAdresse(adr);
                    prof.setTel(tel);



                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed = format.parse(date);
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    // out.print("dat    " + sql);

                    prof.setDateNaiss(sql);
                    prof.setCin(Integer.parseInt(cin));

                    crypt.setClef("mahdi");
                    prof.setPassword(RevCrypt.encode(crypt.getClef(), password));
                    profAdo.insert(prof);
                    session.setAttribute("ProfloginFailed", 0);

                } catch (ParseException ex) {
                    Logger.getLogger(ProfServlet.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                session.setAttribute("ProfloginFailed", 1);
            }

        }
        getList(request, response);
        response.sendRedirect("/EJBProject/template/production/pages/Professeur/listeProfesseur.jsp");

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
        List<Enseignant> ensList;
        ensList = profAdo.getAll();

        sesion1.setAttribute("ensList", ensList);
    }

    public void getEnsById(HttpServletRequest request, HttpServletResponse respons, int id) throws IOException {
        prof.setId(id);
        PrintWriter out = respons.getWriter();
        HttpSession sesion1 = request.getSession();
        List<Enseignant> infoEns;
        infoEns = profAdo.getProfById(prof);

        sesion1.setAttribute("infoEns", infoEns);
    }

    public void getListGroupeEnsByNiveau(HttpServletRequest request, HttpServletResponse respons, int idProf, int idNiv) throws IOException {
        HttpSession session1 = request.getSession();
        List<Enseignant> profGroupList;
        prof.setId(idProf);
        niv.setId(idNiv);
        profGroupList = profAdo.getListGroupeForEnsByNiveau(prof, niv);

        session1.setAttribute("profGroupList", profGroupList);

    }

    public void getListMatiereByGroupeIdProf(HttpServletRequest request, HttpServletResponse respons, int idProf, int idGrp) throws IOException {
        HttpSession session1 = request.getSession();
        List<Enseignant> ListMatiereForEns;
        prof.setId(idProf);
        grp.setId(idGrp);
        ListMatiereForEns = matAdo.getListMatiereByGroupeAndIdProf(grp, prof);

        session1.setAttribute("ListMatiereForEns", ListMatiereForEns);

    }
}
