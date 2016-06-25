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
import project.model.Groupe;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class GroupeAdo {

    public void insert(Groupe n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);

        session.getTransaction().commit();

    }

    public List getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select id,nomGourpe,abreviation,idNiveau from groupe");

        return query.list();

    }

    public void deleteById(Groupe n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Groupe where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(Groupe n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Groupe set nomGourpe=:nom,abreviation=:abv,idNiveau=:niv where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.setString("nom", n.getNomGourpe());
        query.setString("abv", n.getAbreviation());
        query.setInteger("niv", n.getNiveau().getId());

        query.executeUpdate();
        session.getTransaction().commit();
    }

    public String getNomGroupeById(int e) {

       Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Groupe p where p.id = :uid");
        query.setInteger("uid", e);

        String lib = "";
       
        Iterator i = query.iterate();
        while (i.hasNext()) {
            Groupe s = (Groupe) i.next();
            System.out.println("nom = " + s.getNomGourpe());
            lib =  s.getNomGourpe();
            
        }

        return lib ;

    }
    
    public int nbrGrp() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from groupe ");

        return Integer.parseInt(query.uniqueResult().toString());

    }
}
