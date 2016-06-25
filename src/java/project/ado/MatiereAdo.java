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
import project.model.Enseignant;
import project.model.Etudiant;
import project.model.Groupe;
import project.model.Matiere;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class MatiereAdo {

    public void insert(Matiere n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);
        session.getTransaction().commit();


    }

    public List getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from matiere");

        return query.list();

    }

    public void deleteById(Matiere n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Matiere where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(Matiere n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Matiere set libelle=:nom,volumeCour=:vc,volumeTp=:vtp,volumeTd=:vtd,coefficient=:coeff,credit=:cred,idEnseignant=:ens,idGroupe=:groupe where id= :uid ";
        Query query = session.createQuery(hql);


        query.setInteger("uid", n.getId());
        query.setString("nom", n.getLibelle());
        query.setFloat("vc", n.getVolumeCour());
        query.setFloat("vtp", n.getVolumeTp());
        query.setFloat("vtd", n.getVolumeTd());
        query.setFloat("coeff", n.getCoefficient());
        query.setInteger("cred", n.getCredit());
        query.setInteger("ens", n.getEnseignant().getId());
        query.setInteger("groupe", n.getGroupe().getId());



        query.executeUpdate();
        session.getTransaction().commit();
    }

    public String getlibelleMatiereById(int e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Matiere p where p.id = :uid");
        query.setInteger("uid", e);

        String lib = "";

        Iterator sessions = query.iterate();
        while (sessions.hasNext()) {
            Matiere s = (Matiere) sessions.next();
            //  System.out.println("nom = " + s.getLibelle());
            lib = s.getLibelle();

        }

        return lib;

    }

    public List getMatiereByIdEns(int idEns) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // Query query = session.createQuery("from Matiere as m where m.enseignant.id = :uid");
        SQLQuery query = session.createSQLQuery("select id,Libelle from matiere where idEnseignant = :uid");
        query.setInteger("uid", idEns);

        return query.list();

    }

    public List getListMatiereByGroupe(Groupe e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select * from matiere where idGroupe = :uid");
        query.setInteger("uid", e.getId());

        return query.list();

    }

    public List getListMatiereByGroupeAndIdProf(Groupe e, Enseignant ens) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select * from matiere where idGroupe = :uid and idEnseignant = :ensid");
        query.setInteger("uid", e.getId());
        query.setInteger("ensid", ens.getId());

        return query.list();

    }

        public List getListEtudByGroup(Groupe e, Matiere m, project.model.Session s) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select n.id,e.cin,e.nom,e.prenom,n.noteTp,n.noteDs,n.noteExam,n.notePresence,n.numCompostage,n.idSession,m.idGroupe"
                + " from note n,etudiant e , matiere m "
                + " where m.idGroupe = e.idGroup"
                + "  and m.idGroupe = :grpid  "
                + "  and n.idMatiere= :matid "
                + "and m.id=n.idMatiere"
                + "  and e.id=n.idEtudiant"
                + "  and n.idSession= :idsess");

        query.setInteger("grpid", e.getId());
        query.setInteger("matid", m.getId());
        query.setInteger("idsess", s.getId());

        return query.list();

    }
        
         public List getListEtudByGroupComposte(Groupe e, Matiere m, project.model.Session s) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select n.id,n.noteExam,n.numCompostage,n.idSession,m.idGroupe"
                + " from note n,etudiant e , matiere m "
                + " where m.idGroupe = e.idGroup"
                + "  and m.idGroupe = :grpid  "
                + "  and n.idMatiere= :matid "
                + "and m.id=n.idMatiere"
                + "  and e.id=n.idEtudiant"
                + "  and n.idSession= :idsess");

        query.setInteger("grpid", e.getId());
        query.setInteger("matid", m.getId());
        query.setInteger("idsess", s.getId());

        return query.list();

    }
}
