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
        if (args.length != 3) {
            System.out.println("Usage:\n" +
                    "java -jar trainingsdatengenerator.jar <labelFile> <processedFile> <outputFile>");
            System.exit(0);
        }
        Long start = System.currentTimeMillis();
//        A CSV-file containing user validated plag's
        tableFile = args[0];
//        A CSV-file containing the 13-Grams from the first file with their cosinus and jaccard similarity
//        userngram;potngram;kosinus;jaccard
        processedFile = args[1];
//        label 1:<cosinus> 2:<jaccard>
        outputFile = args[2];


        Scanner sc = getScanner(tableFile);
        if (sc != null) {
            String[] header = sc.nextLine().split(";");

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(";");
                for (int i = 0; i < header.length; i++) {
                    Pair p = new Pair(
                            Util.cleanString(header[i]),
                            Util.cleanString(line[0]),
                            Integer.parseInt(line[i + 1])
                    );
                    labelList.add(p);
                }
            }
            sc.close();
        } else System.out.println("File not found: " + tableFile);

        sc = getScanner(processedFile);
        if (sc != null) {
            while (sc.hasNextLine()) {
                try {
                    String line = sc.nextLine();
                    String[] lineArr = line.split(";");
                    String userngram = Util.cleanString(lineArr[0]);
                    String potngram = Util.cleanString(lineArr[1]);
                    Double kosinus = Double.parseDouble(lineArr[2]);
                    Double jaccard = Double.parseDouble(lineArr[3]);
                    Pair p = new Pair(userngram, potngram);
                    p.setCosinus(kosinus);
                    p.setJaccard(jaccard);

                    labelList.parallelStream()
                            .forEach(t -> {
                                if (p.getHash().equals(t.getHash())) {
                                    System.out.println(t);
                                    System.out.println(p);
                                    System.out.println();
                                    System.out.println(p);
                                    System.out.println(t);
                                    writeOutput(t.getLabel() + " 1:" + p.getCosinus() + " 2:" + p.getJaccard());
                                }
                            });
                } catch (ArrayIndexOutOfBoundsException e) {
                    Long stop = System.currentTimeMillis();

                    Long deltaTime = stop - start;
                    System.out.println("Needed " + deltaTime / 1000 / 60 + " minutes.");
                }
            }
            sc.close();
        } else System.out.println("File not found: " + processedFile);



        Long stop = System.currentTimeMillis();
        Long deltaTime = stop - start;
        System.out.println("Needed " + deltaTime / 1000 / 60 + " minutes.");
    }

    public static Scanner getScanner(String file) {
        try {
            return new Scanner(new File(file), "UTF-8");
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    public static void writeOutput(String out) {
        try (Writer output = new BufferedWriter(new FileWriter(outputFile, true))) {
            output.write(out + "\n");
            System.out.println("Match found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
