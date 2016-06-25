/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import project.model.Groupe;
import project.model.Niveau;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class NiveauAdo {

    public void insert(Niveau n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);

        session.getTransaction().commit();

    }

    public List<Niveau> getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select id, libelle,nbrGrp from niveau");

        // while(query.)
        return query.list();

    }

    public void deleteById(Niveau n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Niveau where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(Niveau n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Niveau set libelle=:lib where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.setString("lib", n.getLibelle());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public String getNomNiveauById(int e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Niveau p where p.id = :uid");
        query.setInteger("uid", e);

        String lib = "";

        Iterator i = query.iterate();
        while (i.hasNext()) {
            Niveau s = (Niveau) i.next();

            lib = s.getLibelle();

        }

        return lib;

    }
}
