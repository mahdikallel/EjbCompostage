package project.model;
// Generated 19 mai 2016 19:41:19 by Hibernate Tools 3.2.1.GA



/**
 * Administrateur generated by hbm2java
 */
public class Administrateur  implements java.io.Serializable {


     private Integer id;
     private String login;
     private String password;

    public Administrateur() {
    }

    public Administrateur(String login, String password) {
       this.login = login;
       this.password = password;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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




}


