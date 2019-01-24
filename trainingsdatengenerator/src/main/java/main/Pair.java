package main;

/**
 * Created by samuelerb on 17.01.19.
 * Matr_nr: s0556350
 * Package: main
 */
public class Pair {
    private String exampleText;
    private String wikiText;
    private int label;
    private int hash;
    private double cosinus;
    private double jaccard;

    public Pair(String exampleText, String wikiText, int label) {
        this.exampleText = exampleText.toLowerCase();
        this.wikiText = wikiText.toLowerCase();
        this.label = label;
        this.hash = (exampleText + wikiText).hashCode();
    }

    public Pair(String exampleText, String wikiText) {
        this.exampleText = exampleText;
        this.wikiText = wikiText;
        this.hash = (exampleText + wikiText).hashCode();
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

    public int getHash() {
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
