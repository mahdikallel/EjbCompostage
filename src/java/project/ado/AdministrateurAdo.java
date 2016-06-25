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
import project.model.Etudiant;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class AdministrateurAdo {
     public void insert(Administrateur n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);
        session.getTransaction().commit();

    }

    public List getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from administrateur");

        return query.list();

    }

    public void deleteById(Etudiant n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Administrateur where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(Administrateur n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Administrateur set login=:login,password=:password where id= :uid ";
        Query query = session.createQuery(hql);


        query.setInteger("uid", n.getId());
     
        query.setString("login", n.getLogin());
        query.setString("password", n.getPassword());
      


        query.executeUpdate();
        session.getTransaction().commit();
    }

    public String getUsernameById(String e) {

       Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Administrateur p where p.login = :uid");
        query.setString("uid", e);
 
        String login = "";
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Administrateur personne = (Administrateur) etudiants.next();
           // System.out.println("nom = " + personne.getNom());
            login = personne.getLogin();
            
        }

        return login;

    }
    
    
    
     public String getPasswordByLogin(String e) {

       Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Administrateur p where p.login = :uid");
        query.setString("uid", e);
 
        String password = "";
        Iterator etudiants = query.iterate();
        while (etudiants.hasNext()) {
            Administrateur personne = (Administrateur) etudiants.next();
           // System.out.println("nom = " + personne.getNom());
            password = personne.getPassword();
            
        }

        return password;

    }
     
     public int nbrAdmin() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from administrateur ");




        return Integer.parseInt(query.uniqueResult().toString());

    }
}
