/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ado.AdministrateurAdo;
import project.ado.EtudiantAdo;
import project.ado.ProfesseurAdo;
import project.model.Administrateur;
import project.model.Enseignant;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
@WebServlet(name = "Authentification", urlPatterns = {"/Authentification"})
public class Authentification extends HttpServlet {

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
    RevCrypt crypt = new RevCrypt();
    Administrateur admin = new Administrateur();
    AdministrateurAdo adminAdo = new AdministrateurAdo();
    ProfesseurAdo profAdo = new ProfesseurAdo();
    EtudiantAdo etudAdo = new EtudiantAdo();
    Enseignant ens = new Enseignant();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Authentification</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authentification at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        if (action == null) {
            response.sendRedirect("/EJBProject/template/production/login.jsp");
            session.setAttribute("choixType", 1);
        }
        if ("logout".equals(action)) {

            session.setAttribute("username", null);
            session.setAttribute("userEns", null);
            session.setAttribute("userEtud", null);

            session.invalidate();
            response.sendRedirect("/EJBProject/template/production/login.jsp");
        }
        if ("selectTypeUser".equals(action)) {
            int idCat = Integer.parseInt(request.getParameter("idCat").toString());
            //out.print(idCat);

            switch (idCat) {
                case 0:
                    session.setAttribute("type", "loginAdmin");
                    session.setAttribute("idCat", idCat);
                    break;
                case 1:
                    session.setAttribute("type", "loginEns");
                    session.setAttribute("idCat", idCat);
                    break;
                case 2:
                    session.setAttribute("type", "loginEtudiant");
                    session.setAttribute("idCat", idCat);
                    break;
                default:
                    session.setAttribute("type", null);
                    break;
            }
            response.sendRedirect("/EJBProject/template/production/login.jsp");
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        out.print("action66" + action);
        if ("noSelect".equals(action)) {
             session.setAttribute("choixType", 1);
            response.sendRedirect("/EJBProject/template/production/login.jsp");
           
        }
        if ("signup".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rpassword = request.getParameter("rpassword");
            out.print(username + "  " + password);


            if (password.equals(rpassword)) {
                admin.setLogin(username);
                crypt.setClef("mahdi");
                try {
                    admin.setPassword(RevCrypt.encode(crypt.getClef(), password));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
                }

                adminAdo.insert(admin);
                response.sendRedirect("/EJBProject/template/production/login.jsp");
            }


        }
        if ("loginAdmin".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // out.println(adminAdo.getUsernameById(username) + "password " + password);

            crypt.setClef("mahdi");
            try {

                if ((RevCrypt.encode(crypt.getClef(), password).equals(adminAdo.getPasswordByLogin(username))) && ((username != "") || (password != ""))) {
                    response.sendRedirect("/EJBProject/template/production/index.jsp");
                    session.setAttribute("username", username);


                } else {

                    session.setAttribute("failedAdmin", 1);
                    response.sendRedirect("/EJBProject/template/production/login.jsp");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


        /*---Login enseignant--*/
        if ("loginEns".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            out.println(profAdo.getPasswordByLogin(username) + "password " + password);
            /* try {
             //  crypt.setClef("mahdi");
             //  out.println("cryptage de la mote de passe "+RevCrypt.encode(crypt.getClef(), password));
             } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
             } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
             }*/

            crypt.setClef("mahdi");
            try {

                if (RevCrypt.encode(crypt.getClef(), password).equals(profAdo.getPasswordByLogin(username)) && ((username != "") || (password != ""))) {

                    session.setAttribute("userEns", username);

                    //  System.out.println("id prof connecté " + profAdo.getIdByLogin(username));
                    ens.setId(profAdo.getIdByLogin(username));//recupere Id pour parcourir list des groupe pour cet ensei

                    List<Enseignant> profNivList;
                    profNivList = profAdo.getListNiveauForEns(ens);
                    session.setAttribute("profNivList", profNivList);



                    response.sendRedirect("/EJBProject/template/production/pages/EspaceEnseignant/acceuil.jsp");


                } else {

                    session.setAttribute("failedEns", 1);
                    response.sendRedirect("/EJBProject/template/production/login.jsp");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        /*---Fin login enseignant--*/


        /*---Login etudiant--*/
        if ("loginEtudiant".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // out.println(adminAdo.getUsernameById(username) + "password " + password);

            crypt.setClef("mahdi");
            try {

                if (RevCrypt.encode(crypt.getClef(), password).equals(etudAdo.getPasswordByLogin(username)) && ((username != "") || (password != ""))) {

                    session.setAttribute("userEtud", username);
                    response.sendRedirect("/EJBProject/template/production/pages/EspaceEtudiant/acceuil.jsp");


                } else {

                    session.setAttribute("failedEtud", 1);
                    response.sendRedirect("/EJBProject/template/production/login.jsp");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        /*---FinLogin etudiant--*/


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
}
