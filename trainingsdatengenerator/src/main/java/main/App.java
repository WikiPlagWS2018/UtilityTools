package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by samuelerb on 17.01.19.
 * Matr_nr: s0556350
 * Package: main
 */
public class App {
    public static List<Pair> labelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String tableFile = "label.csv";
        String processedFile = tableFile;


        Scanner sc = getScanner(tableFile);
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

        sc = getScanner(processedFile);
        while (sc.hasNextLine()) {
            String[] lineArr = sc.nextLine().split(";");
            String userngram = lineArr[0];
            String potngram = lineArr[1];
            Double kosinus = Double.parseDouble(lineArr[2]);
            Double jaccard = Double.parseDouble(lineArr[3]);
        }

//        input von rene: userngram | potngram | kosinus | jaccard

//        output: userngram | potngram | kosinus | jaccard | label

        labelList.forEach(System.out::println);
    }

    public static Scanner getScanner(String file) {
        try {
            Scanner scanner = new Scanner(new File(file), "UTF-8");
            return scanner;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
