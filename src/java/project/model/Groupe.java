package project.model;
// Generated 19 mai 2016 02:33:48 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Groupe generated by hbm2java
 */
public class Groupe  implements java.io.Serializable {


     private Integer id;
     private Niveau niveau;
     private String nomGourpe;
     private String abreviation;
     private Set<Etudiant> etudiants = new HashSet<Etudiant>(0);

    public Groupe() {
    }

	
    public Groupe(Niveau niveau, String nomGourpe, String abreviation) {
        this.niveau = niveau;
        this.nomGourpe = nomGourpe;
        this.abreviation = abreviation;
    }
    public Groupe(Niveau niveau, String nomGourpe, String abreviation, Set<Etudiant> etudiants) {
       this.niveau = niveau;
       this.nomGourpe = nomGourpe;
       this.abreviation = abreviation;
       this.etudiants = etudiants;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Niveau getNiveau() {
        return this.niveau;
    }
    
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    public String getNomGourpe() {
        return this.nomGourpe;
    }
    
    public void setNomGourpe(String nomGourpe) {
        this.nomGourpe = nomGourpe;
    }
    public String getAbreviation() {
        return this.abreviation;
    }
    
    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }
    public Set<Etudiant> getEtudiants() {
        return this.etudiants;
    }
    
    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

   
   
    




}


