package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by samuelerb on 17.01.19.
 * Matr_nr: s0556350
 * Package: main
 */
public class App {
    private static List<Pair> labelList = new ArrayList<>();
    private static String outputFile;
    private static String tableFile;
    private static String processedFile;

    public static void main(String[] args) throws IOException {

        tableFile = "label.csv";
        processedFile = "output.txt";
        outputFile = "output.txt";

//        tableFile = args[0];
//        processedFile = args[1];
//        outputFile = args[2];


        Scanner sc = getScanner(tableFile);
        if (sc != null) {
            String[] header = sc.nextLine().split(";");

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(";");
                for (int i = 1; i < header.length; i++) {
                    Pair p = new Pair(
                            Cleaner.cleanString(header[i]),
                            Cleaner.cleanString(line[0]),
                            Integer.parseInt(line[i])
                    );
                    labelList.add(p);
                }
            }
            sc.close();
        } else System.out.println("File not found: " + tableFile);


        sc = getScanner(processedFile);
        if (sc != null) {
            while (sc.hasNextLine()) {
                String[] lineArr = sc.nextLine().split(";");
                String userngram = lineArr[0];
                String potngram = lineArr[1];
                Double kosinus = Double.parseDouble(lineArr[2]);
                Double jaccard = Double.parseDouble(lineArr[3]);
                Pair p = new Pair(userngram, potngram);
                p.setCosinus(kosinus);
                p.setJaccard(jaccard);

                labelList.forEach(t -> {
                    if (p.getHash() == t.getHash()) {
                        writeOutput(p.getExampleText() + " " + p.getWikiText() + " " + p.getCosinus() + " " + p.getJaccard() + " " + t.getLabel());
                    }
                });

            }
            sc.close();
        } else System.out.println("File not found: " + processedFile);

//        input von rene: userngram | potngram | kosinus | jaccard

//        output: userngram | potngram | kosinus | jaccard | label
    }

    public static Scanner getScanner(String file) {
        try {
            Scanner scanner = new Scanner(new File(file), "UTF-8");
            return scanner;
        } catch (FileNotFoundException e) {}
        return null;
    }

    public static void writeOutput(String out) {
        try(Writer output = new BufferedWriter(new FileWriter(outputFile))) {
            output.append(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    userngram | potngram | kosinus | jaccard


}
