/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.awt.RenderingHints.Key;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;


import project.propreties.hibernate.ClassHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import project.model.Administrateur;
import project.model.Etudiant;
import project.model.Niveau;

/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println(RevCrypt.decode("mahdi","STgVAA=="));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
