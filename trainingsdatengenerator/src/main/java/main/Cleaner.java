package main;

/**
 * Created by samuelerb on 17.01.19.
 * Matr_nr: s0556350
 * Package: main
 */
public class Cleaner {

    private static String removeCommas(String s) {
        return s.replace(",", "");
    }

    private static String removePeriod(String s) {
        return s.replace(".", "");
    }

    private static String removeDoubleSpace(String s) {
        return s.replace(" +", " ");
    }

    public static String cleanString(String s) {
        return removePeriod(removeDoubleSpace(removeCommas(s)));
    }



}
