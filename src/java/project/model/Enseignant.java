package project.model;
// Generated 19 mai 2016 02:33:48 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Enseignant generated by hbm2java
 */
public class Enseignant  implements java.io.Serializable {


     private Integer id;
     private String nom;
     private String prenom;
     private Date dateNaiss;
     private String adresse;
     private int cin;
     private String tel;
     private String login;
     private String password;
     private Set<Matiere> matieres = new HashSet<Matiere>(0);

    public Enseignant() {
    }

	
    public Enseignant(String nom, String prenom, Date dateNaiss, String adresse, int cin, String tel, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.cin = cin;
        this.tel = tel;
        this.login = login;
        this.password = password;
    }
    public Enseignant(String nom, String prenom, Date dateNaiss, String adresse, int cin, String tel, String login, String password, Set<Matiere> matieres) {
       this.nom = nom;
       this.prenom = prenom;
       this.dateNaiss = dateNaiss;
       this.adresse = adresse;
       this.cin = cin;
       this.tel = tel;
       this.login = login;
       this.password = password;
       this.matieres = matieres;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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
    public Set<Matiere> getMatieres() {
        return this.matieres;
    }
    
    public void setMatieres(Set<Matiere> matieres) {
        this.matieres = matieres;
    }




}


