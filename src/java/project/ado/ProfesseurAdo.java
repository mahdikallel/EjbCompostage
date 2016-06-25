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
import project.model.Niveau;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class ProfesseurAdo {

    public void insert(Enseignant n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);
        session.getTransaction().commit();

    }

    public List getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from enseignant");

        return query.list();

    }

    public void deleteById(Enseignant n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Enseignant where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(Enseignant n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Enseignant set nom=:nom,prenom=:prenom,dateNaiss=:daten,adresse=:adr,cin=:cin,tel=:tel,login=:login,password=:password where id= :uid ";
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

    public String getNomProfById(int e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Enseignant p where p.id = :uid");
        query.setInteger("uid", e);

        String lib = "";

        Iterator i = query.iterate();
        while (i.hasNext()) {
            Enseignant s = (Enseignant) i.next();

            lib = s.getNom() + "  " + s.getPrenom();

        }

        return lib;

    }

    public int verifLogin(Enseignant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from enseignant n where n.login=:log");
        query.setString("log", e.getLogin());



        return Integer.parseInt(query.uniqueResult().toString());

    }

    public String getPasswordByLogin(String e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Enseignant p where p.login = :uid");
        query.setString("uid", e);

        String password = "";
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Enseignant personne = (Enseignant) etudiants.next();
            // System.out.println("nom = " + personne.getNom());
            password = personne.getPassword();

        }

        return password;

    }

    public List<Enseignant> getListNiveauForEns(Enseignant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select distinct(n.libelle),n.id from groupe g, matiere m , niveau n  where g.id=m.idGroupe and n.id=g.idNiveau "
                + "and m.idEnseignant = :uid");
        query.setInteger("uid", e.getId());
        return query.list();

    }

    public List<Enseignant> getListGroupeForEnsByNiveau(Enseignant e, Niveau n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select distinct(g.nomGourpe),g.id from groupe g, matiere m , niveau n  where g.id=m.idGroupe and n.id=g.idNiveau "
                + "and m.idEnseignant = :uid and n.id = :nivid ");
        query.setInteger("uid", e.getId());
        query.setInteger("nivid", n.getId());
        return query.list();

    }

    public int getIdByLogin(String e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Enseignant p where p.login = :uid");
        query.setString("uid", e);

        int id = 0;
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Enseignant personne = (Enseignant) etudiants.next();
            // System.out.println("nom = " + personne.getNom());
            id = personne.getId();

        }

        return id;

    }

    public List getProfById(Enseignant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Enseignant p where p.id = :uid");

        query.setInteger("uid", e.getId());
        return query.list();

    }

    public int nbrEns() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from enseignant ");




        return Integer.parseInt(query.uniqueResult().toString());

    }
}
