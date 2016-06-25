/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ado;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import project.model.Administrateur;
import project.model.Enseignant;
import project.model.Etudiant;
import project.model.Groupe;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class EtudiantAdo {

    public void insert(Etudiant n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);
        session.getTransaction().commit();

    }

    public List getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from etudiant");

        return query.list();

    }

    public void deleteById(Etudiant n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Etudiant where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(Etudiant n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Etudiant set nom=:nom,prenom=:prenom,dateNaiss=:daten,adresse=:adr,cin=:cin,tel=:tel,login=:login,password=:password,idGroup=:idGrp where id= :uid ";
        Query query = session.createQuery(hql);


        query.setInteger("uid", n.getId());
        query.setString("nom", n.getNom());
        query.setString("prenom", n.getPrenom());
        query.setDate("daten", n.getDateNaiss());
        query.setString("adr", n.getAdresse());
        query.setInteger("cin", n.getCin());
        query.setString("tel", n.getTel());
        query.setString("login", n.getLogin());
        query.setString("password", n.getPassword());
        query.setInteger("idGrp", n.getGroupe().getId());


        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateByIdFromEtud(Etudiant n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Etudiant set nom=:nom,prenom=:prenom,dateNaiss=:daten,adresse=:adr,cin=:cin,tel=:tel,login=:login,password=:password where id= :uid ";
        Query query = session.createQuery(hql);


        query.setInteger("uid", n.getId());
        query.setString("nom", n.getNom());
        query.setString("prenom", n.getPrenom());
        query.setDate("daten", n.getDateNaiss());
        query.setString("adr", n.getAdresse());
        query.setInteger("cin", n.getCin());
        query.setString("tel", n.getTel());
        query.setString("login", n.getLogin());
        query.setString("password", n.getPassword());



        query.executeUpdate();
        session.getTransaction().commit();
    }

    public String getNomEtudiantById(int e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Etudiant p where p.id = :uid");
        query.setInteger("uid", e);

        String nom = "";
        String prenom = "";
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Etudiant personne = (Etudiant) etudiants.next();
            // System.out.println("nom = " + personne.getNom());
            nom = personne.getNom();
            prenom = personne.getPrenom();
        }

        return nom + " " + prenom;

    }

    public List getListEtudiantByGroupe(Groupe e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select * from etudiant where idGroup = :uid");
        query.setInteger("uid", e.getId());

        return query.list();

    }

    public int verifLogin(Etudiant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from etudiant n where n.login=:log");
        query.setString("log", e.getLogin());



        return Integer.parseInt(query.uniqueResult().toString());

    }

    public String getPasswordByLogin(String e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Etudiant p where p.login = :uid");
        query.setString("uid", e);

        String password = "";
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Etudiant personne = (Etudiant) etudiants.next();
            // System.out.println("nom = " + personne.getNom());
            password = personne.getPassword();

        }

        return password;

    }

    public String getIdByLogin(String e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Etudiant p where p.login = :uid");
        query.setString("uid", e);

        String id = "";
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Etudiant personne = (Etudiant) etudiants.next();
            // System.out.println("nom = " + personne.getNom());
            id = personne.getId().toString();

        }

        return id;

    }

    public List getEtudById(Etudiant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Etudiant p where p.id = :uid");

        query.setInteger("uid", e.getId());
        return query.list();

    }

    public List getListMatiereByIdEtudiantGroupe(Etudiant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        System.out.println("groupe ID" + e.getGroupe().getId());
        System.out.println("etud ID" + e.getId());
        SQLQuery query = session.createSQLQuery("select m.libelle,m.volumeCour,m.volumeTp,m.volumeTd,m.coefficient,m.credit,m.id"
                + " from etudiant e,matiere m "
                + "where e.idGroup=m.idGroupe "
                + "and e.idGroup= :idgrp "
                + "and e.id= :uid ");
          System.out.println("Quert" + query);
        query.setInteger("uid", e.getId());
        query.setInteger("idgrp", e.getGroupe().getId());

        return query.list();

    }

    public int getIdGroupeByIdEtudiant(Etudiant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Etudiant p where p.id = :uid");
        query.setInteger("uid", e.getId());

        int idGrp = 0;
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Etudiant personne = (Etudiant) etudiants.next();
            // System.out.println("nom = " + personne.getNom());
            idGrp = personne.getGroupe().getId();

        }

        return idGrp;

    }
    
     public int nbrEtud() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from etudiant ");
        



        return Integer.parseInt(query.uniqueResult().toString());

    }
}
