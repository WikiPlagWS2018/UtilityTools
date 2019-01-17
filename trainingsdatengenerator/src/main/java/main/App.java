package main;

import java.io.FileInputStream;
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
        String filename = "label.csv";
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(filename);
            sc = new Scanner(inputStream, "UTF-8");

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
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

//        input von rene: userngram | potngram | kosinus | jaccard

//        output: userngram | potngram | kosinus | jaccard | label

        labelList.forEach(System.out::println);
    }
}
