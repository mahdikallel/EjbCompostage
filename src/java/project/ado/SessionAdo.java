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
import project.model.Etudiant;
import project.model.Niveau;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class SessionAdo {
     public void insert(project.model.Session n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);

        session.getTransaction().commit();

    }

    public List<project.model.Session> getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from session");

        // while(query.)
        return query.list();

    }

    public void deleteById(project.model.Session n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Session where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(project.model.Session n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Session set libelle=:lib where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.setString("lib", n.getLibelle());
        query.executeUpdate();
        session.getTransaction().commit();
    }
    
    
     public String getlibelleSessiontById(int e) {

       Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Session p where p.id = :uid");
        query.setInteger("uid", e);

        String lib = "";
       
        Iterator sessions = query.iterate();
        while (sessions.hasNext()) {
            project.model.Session s = (project.model.Session) sessions.next();
           // System.out.println("nom = " + s.getLibelle());
            lib = s.getLibelle();
            
        }

        return lib ;

    }

}
