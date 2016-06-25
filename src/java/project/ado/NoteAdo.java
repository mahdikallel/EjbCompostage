/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ado;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import project.model.Enseignant;
import project.model.Etudiant;
import project.model.Groupe;
import project.model.Matiere;
import project.model.Note;
import project.propreties.hibernate.ClassHibernateUtil;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class NoteAdo {

    public void insert(Note n) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(n);
        session.getTransaction().commit();


    }

    public List getAll() {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from note");

        return query.list();

    }

    public void deleteById(Note n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete from Note where id= :uid ";
        Query query = session.createQuery(hql);
        query.setInteger("uid", n.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateById(Note n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Note set NoteTP=:tp,NoteDS=:ds,NoteExam=:exam,notePresence=:pres,idEtudiant=:idetud,idSession=:idsess,idMatiere=:idMat,numCompostage=:numCompos where id= :uid ";
        Query query = session.createQuery(hql);


        query.setInteger("uid", n.getId());

        query.setFloat("tp", n.getNoteTp());
        query.setFloat("ds", n.getNoteDs());
        query.setFloat("exam", n.getNoteExam());
        query.setFloat("pres", n.getNotePresence());
        query.setInteger("idetud", n.getEtudiant().getId());
        query.setInteger("idsess", n.getSession().getId());
        query.setInteger("idMat", n.getMatiere().getId());
        query.setInteger("numCompos", n.getNumCompostage());



        query.executeUpdate();
        session.getTransaction().commit();
    }

    public int verifCopostageByMatAndSessAndEtud(Note n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from note n where n.idSession=:idSess and n.idMatiere=:idMat and n.idEtudiant=:idEtud");
        query.setInteger("idSess", n.getSession().getId());
        query.setInteger("idMat", n.getMatiere().getId());
        query.setInteger("idEtud", n.getEtudiant().getId());

        /*  System.out.println("idSess " + n.getSession().getId());
         * System.out.println("idMat "+ n.getMatiere().getId());
         * System.out.println("idEtud"+ n.getEtudiant().getId());
         */
        // System.out.println("ressss"+query.uniqueResult().toString());
        return Integer.parseInt(query.uniqueResult().toString());

    }

    public List getListEtudByMatAndIdProfAndIdSess(Matiere e, project.model.Session sess) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select numCompostage from note where idMatiere = :uid and idSession = :idsess");
        query.setInteger("uid", e.getId());
        query.setInteger("idsess", sess.getId());

        return query.list();

    }

    public void UpdateNoteNCById(Note n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Note set NoteTP=:tp,NoteDS=:ds,notePresence=:pres where id= :uid ";
        Query query = session.createQuery(hql);


        query.setInteger("uid", n.getId());
        query.setFloat("tp", n.getNoteTp());
        query.setFloat("ds", n.getNoteDs());
        query.setFloat("pres", n.getNotePresence());

        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void UpdateNoteCById(Note n) {
        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "Update  Note set noteExam=:exm where id= :uid ";
        Query query = session.createQuery(hql);


        query.setInteger("uid", n.getId());
        query.setFloat("exm", n.getNoteExam());


        query.executeUpdate();
        session.getTransaction().commit();
    }

    public List getListNoteEtudByMat(Matiere m, Etudiant e) {

        Session session = ClassHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select n.id,n.noteTp,n.noteDs,n.noteExam,n.notePresence,n.idSession,m.idGroupe,m.libelle"
                + " from note n,etudiant e , matiere m "
                + " where m.idGroupe = e.idGroup"
                + "  and n.idEtudiant = :idetud  "
                + "  and n.idMatiere= :matid "
                + "and m.id=n.idMatiere"
                + "  and e.id=n.idEtudiant"
                );

       // System.out.println("queyr"+query);
        query.setInteger("idetud", e.getId());
        query.setInteger("matid", m.getId());
        //query.setInteger("idsess", s.getId());

        return query.list();

    }
}
