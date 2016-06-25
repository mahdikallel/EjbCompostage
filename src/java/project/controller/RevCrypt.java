/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 *
 * @author Mahdi Kallel <mahdi-kallel@live.fr>
 */
public class RevCrypt {
    
    

    public static String decode(String clef, String pass) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        // Cryptage de la clef
        String cryptclef = SHA1(clef);
        // Décryptage code 64
        byte[] data = Base64.getDecoder().decode(pass.getBytes());
       
        // Décodeur de chaîne
        int kc;
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                kc = cryptclef.charAt(cryptclef.length() - 1);
            } else {
                kc = cryptclef.charAt((i % cryptclef.length()) - 1);
            }
            data[i] = (byte) (((int) data[i]) - ((int) kc));
        }
        // Résultat
        String decryptpass = new String(data);
        return decryptpass;
    }

    public static String encode(String clef, String pass) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        // Cryptage de la clef
        String cryptclef = SHA1(clef);
        // Encodeur de chaîne
        byte[] data = new byte[pass.length()];
        int kc;
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                kc = cryptclef.charAt(cryptclef.length() - 1);
            } else {
                kc = cryptclef.charAt((i % cryptclef.length()) - 1);
            }
            data[i] = (byte) ((int) kc + (int) pass.charAt(i));
        }
        // Cryptage code 64
        
        String cryptpass = new String(Base64.getEncoder().encode(data));
        // Résultat
        return cryptpass;
    }

    private String clef ;
    /*   public static void main(String[] args) {
     * try {
     * String clef = "mahdi";
     * String pass = "iit2016";
     * String encoded = encode(clef, pass);
     * System.out.println(encoded);
     * String decoded = decode(clef, encoded);
     * System.out.println(decoded);
     * if (pass.equals(decoded)) {
     * System.out.println("OK");
     * } else {
     * System.out.println("NOT OK");
     * }
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }*/

    public String getClef() {
        return clef;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    private static String SHA1(final String text) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.reset();
        byte[] hashCode = md.digest(text.getBytes());
        return new String(hashCode);
    }
}
