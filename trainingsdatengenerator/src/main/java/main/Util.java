package main;

import java.security.MessageDigest;

/**
 * Created by samuelerb on 17.01.19.
 * Matr_nr: s0556350
 * Package: main
 */
public class Util {

    private static String removeCommas(String s) {
        return s.replace(",", "");
    }

    private static String removePeriod(String s) {
        return s.replace(".", "");
    }

    private static String removeDoubleSpace(String s) {
        return s.replace(" +", " ");
    }

    private static String removeQuotes(String s) {
        return s.replace("„", " ")
                .replace("“", " ")
                .replace("\"", "")
                .replace("'", "");
    }



    public static String cleanString(String s) {
        if (s.charAt(0) == ' ' || s.charAt(0) == '\uFEFF') {
            s = s.substring(1);
        } else if (s.charAt(s.length() - 1) == ' ' || s.charAt(s.length() - 1) == '\n') {
            s = s.substring(0, s.length() - 2);
        }
        return removePeriod(removeDoubleSpace(removeCommas(removeQuotes(s)))).toLowerCase().trim();
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }



}
