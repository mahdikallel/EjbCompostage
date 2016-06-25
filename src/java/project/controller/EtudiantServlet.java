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
import project.ado.EtudiantAdo;
import project.ado.GroupeAdo;
import project.ado.MatiereAdo;
import project.model.Etudiant;
import project.model.Groupe;
import project.model.Matiere;
import project.model.Niveau;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "Etudiant", urlPatterns = {"/Etudiant"})
public class EtudiantServlet extends HttpServlet {

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
    Etudiant etud = new Etudiant();
    EtudiantAdo etudAdo = new EtudiantAdo();
    GroupeAdo grpAdo = new GroupeAdo();
    Groupe grp = new Groupe();
    Matiere mat = new Matiere();
    MatiereAdo matAdo = new MatiereAdo();
    RevCrypt crypt = new RevCrypt();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Etudiant</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Etudiant at " + request.getContextPath() + "</h1>");



            out.println("<h2>" + "</h2>");
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
            etud.setId(Integer.parseInt(idToDelete));
            etudAdo.deleteById(etud);
            getList(request, response);
            response.sendRedirect("/EJBProject/template/production/pages/Etudiant/listeEtudiant.jsp");
        }


        if ("editFromEtudiant".equals(action)) {

            HttpSession session = request.getSession();
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

            out.println("id  " + id + " \n nom  " + nom);
             out.println("prenom " + prenom + " \n  adr " + adr +"\n tel "+tel);
             out.println("login   " + login + "password " + password);
             //out.println("login   " + date + "cin " + cin);
            etud.setLogin(login);
            if (etudAdo.verifLogin(etud) == 0) {
                try {
                    out.println("login   " + login + "password " + password);
                    etud.setId(Integer.parseInt(id));
                    etud.setNom(nom);
                    etud.setPrenom(prenom);
                    etud.setAdresse(adr);
                    etud.setTel(tel);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed = format.parse(date);
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());

                    out.println("date" + sql);
                    etud.setDateNaiss(sql);
                    etud.setCin(Integer.parseInt(cin));

                    crypt.setClef("mahdi");
                    etud.setPassword(RevCrypt.encode(crypt.getClef(), password));
                    //grp.setId(Integer.parseInt(idgroupe));
                    
                    etudAdo.UpdateByIdFromEtud(etud);
                    getEtudById(request, response,etud.getId());
                    session.setAttribute("loginFailed", 0);
                    session.setAttribute("userEtud", etud.getLogin());

                } catch (ParseException ex) {
                    Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(EtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                session.setAttribute("loginFailed", 1);
            }

            response.sendRedirect("/EJBProject/template/production/pages/EspaceEtudiant/acceuil.jsp");
        }
        if ("edit".equals(action)) {

            HttpSession session = request.getSession();
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
            String idgroupe = request.getParameter("groupe");
            //out.println("id grp " + idToEdit + " \n nom groupe " + nameToEdit);
            // out.println("action " + action + " \n  abreviation " + abvToEdit +"\n id niv to edit "+idNivToEdit);
            out.println("date   " + date + "groupr " + idgroupe);
            etud.setLogin(login);
            if (etudAdo.verifLogin(etud) == 0) {
                try {
                    etud.setId(Integer.parseInt(id));
                    etud.setNom(nom);
                    etud.setPrenom(prenom);
                    etud.setAdresse(adr);
                    etud.setTel(tel);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed = format.parse(date);
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());

                    out.println("date" + sql);
                    etud.setDateNaiss(sql);
                    etud.setCin(Integer.parseInt(cin));

                    crypt.setClef("mahdi");
                    etud.setPassword(RevCrypt.encode(crypt.getClef(), password));
                    grp.setId(Integer.parseInt(idgroupe));
                    etud.setGroupe(grp);
                    etudAdo.UpdateById(etud);
                    getList(request, response);
                    session.setAttribute("loginFailed", 0);

                } catch (ParseException ex) {
                    Logger.getLogger(ProfServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(EtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                session.setAttribute("loginFailed", 1);
            }

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
        // processRequest(request, response);
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
        String idgroupe = request.getParameter("groupe");
        HttpSession session = request.getSession();
        if ("add".equals(action)) {


            etud.setLogin(login);
            if (etudAdo.verifLogin(etud) == 0) {
                try {
                    etud.setNom(nom);
                    etud.setPrenom(prenom);
                    etud.setAdresse(adr);
                    etud.setTel(tel);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed = format.parse(date);
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    // out.print("dat    " + sql);

                    etud.setDateNaiss(sql);
                    etud.setCin(Integer.parseInt(cin));


                    crypt.setClef("mahdi");
                    etud.setPassword(RevCrypt.encode(crypt.getClef(), password));
                    grp.setId(Integer.parseInt(idgroupe));
                    etud.setGroupe(grp);
                    etudAdo.insert(etud);
                    session.setAttribute("EtudloginFailed", 0);

                } catch (ParseException ex) {
                    Logger.getLogger(ProfServlet.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(EtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                session.setAttribute("EtudloginFailed", 1);
            }

        }
        getList(request, response);
        response.sendRedirect("/EJBProject/template/production/pages/Etudiant/listeEtudiant.jsp");
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
        List<Etudiant> etudList;
        etudList = etudAdo.getAll();

        sesion1.setAttribute("etudList", etudList);
    }

    public void getListGroupe(HttpServletRequest request, HttpServletResponse respons) throws IOException {
        PrintWriter out = respons.getWriter();
        HttpSession sesion1 = request.getSession();
        List<Niveau> niveauList;
        niveauList = grpAdo.getAll();
        sesion1.setAttribute("grpList", niveauList);
    }

    public void getEtudById(HttpServletRequest request, HttpServletResponse respons, int id) throws IOException {
        etud.setId(id);
        PrintWriter out = respons.getWriter();
        HttpSession sesion1 = request.getSession();
        List<Etudiant> infoEtud;
        infoEtud = etudAdo.getEtudById(etud);

        sesion1.setAttribute("infoEtud", infoEtud);
    }
    
    
    public void getListMatiereByIdEtuiant(HttpServletRequest request, HttpServletResponse respons, Etudiant etud1) throws IOException {
      //  etud.setId(id);
        PrintWriter out = respons.getWriter();
        HttpSession sesion1 = request.getSession();
        List<Etudiant> infoEtud;
        infoEtud = etudAdo.getListMatiereByIdEtudiantGroupe(etud1);

        sesion1.setAttribute("ListMatiere", infoEtud);
    }
}
