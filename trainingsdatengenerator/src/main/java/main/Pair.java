package main;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by samuelerb on 17.01.19.
 * Matr_nr: s0556350
 * Package: main
 */
public class Pair {
    private String exampleText;
    private String wikiText;
    private int label;
    private String hash;
    private double cosinus;
    private double jaccard;

    public Pair(String exampleText, String wikiText, int label) {
        this.exampleText = exampleText;
        this.wikiText = wikiText;
        this.label = label;
        this.hash = Util.sha256((exampleText + wikiText));

    }

    public Pair(String exampleText, String wikiText) {
        this.exampleText = exampleText;
        this.wikiText = wikiText;
        this.hash = Util.sha256((exampleText + wikiText));
    }

    public String getExampleText() {
        return exampleText;
    }

    public String getWikiText() {
        return wikiText;
    }

    public int getLabel() {
        return label;
    }

    public String getHash() {
        return hash;
    }

    public double getCosinus() {
        return cosinus;
    }

    public void setCosinus(double cosinus) {
        this.cosinus = cosinus;
    }

    public double getJaccard() {
        return jaccard;
    }

    public void setJaccard(double jaccard) {
        this.jaccard = jaccard;
    }

    @Override
    public String toString() {
        return "Pair{" +
                ", wikiText='" + wikiText + '\'' +
                "exampleText='" + exampleText + '\'' +
                ", label=" + label +
                ", hash=" + hash +
                '}';
    }
}
