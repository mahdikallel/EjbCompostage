package project.model;
// Generated 19 mai 2016 02:33:48 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Etudiant generated by hbm2java
 */
public class Etudiant  implements java.io.Serializable {


     private Integer id;
     private Groupe groupe;
     private String nom;
     private String prenom;
     private Date dateNaiss;
     private String adresse;
     private int cin;
     private String tel;
     private String login;
     private String password;
     private Set<Note> notes = new HashSet<Note>(0);

    public Etudiant() {
    }

	
    public Etudiant(Groupe groupe, String nom, String prenom, Date dateNaiss, String adresse, int cin, String tel, String login, String password) {
        this.groupe = groupe;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.cin = cin;
        this.tel = tel;
        this.login = login;
        this.password = password;
    }
    public Etudiant(Groupe groupe, String nom, String prenom, Date dateNaiss, String adresse, int cin, String tel, String login, String password, Set<Note> notes) {
       this.groupe = groupe;
       this.nom = nom;
       this.prenom = prenom;
       this.dateNaiss = dateNaiss;
       this.adresse = adresse;
       this.cin = cin;
       this.tel = tel;
       this.login = login;
       this.password = password;
       this.notes = notes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Groupe getGroupe() {
        return this.groupe;
    }
    
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDateNaiss() {
        return this.dateNaiss;
    }
    
    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public int getCin() {
        return this.cin;
    }
    
    public void setCin(int cin) {
        this.cin = cin;
    }
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Note> getNotes() {
        return this.notes;
    }
    
    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }




}


