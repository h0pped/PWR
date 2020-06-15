import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Scanner scan;

    public static void loadDocument(String name) {
        try {
            FileReader fileReader = new FileReader(name);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            String eodRegex = "(.*)([Ee][Oo][Dd])";
            String linkRegex = "[Ll][Ii][Nn][Kk]=([a-zA-Z]+[1-9]*[a-zA-z1-9]*)";

            Pattern linkpat = Pattern.compile(linkRegex);
            Pattern eodpat = Pattern.compile(eodRegex);

            Matcher linkmatcher;
            Matcher eodMatcher;
            boolean is_eod = false;
            while ((line = bufferedReader.readLine()) != null) {
                eodMatcher = eodpat.matcher(line);
                is_eod = eodMatcher.find();

                if (is_eod == true) {
                    linkmatcher = linkpat.matcher(eodMatcher.group());
                    while (linkmatcher.find()) {
                        System.out.println(linkmatcher.group(1).toLowerCase());

                    }
                    break;
                } else {
                    linkpat = Pattern.compile(linkRegex);
                    linkmatcher = linkpat.matcher(line);
                    while (linkmatcher.find()) {
                        System.out.println(linkmatcher.group(1).toLowerCase());
                    }
                }


            }

            bufferedReader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean correctLink(String link) {
        return true;
    }

    private static void drawLine(int n, char ch) {
        for (int k = 1; k <= (2 * n - 1); k++) {
            System.out.print(ch);
        }
    }

    private static void drawPyramid(int value) {
        for (int i = 1; i <= value; i++) {
            for (int j = i; j < value; j++) {
                System.out.print(" ");
            }
            drawLine(i, 'X');
            System.out.println();
        }
    }

    private static void drawPyramid(int value, int count) {
        for (int i = 1; i <= value; i++) {
            for (int j = i; j < count; j++) {
                System.out.print(" ");
            }
            drawLine(i, 'X');
            System.out.println();
        }
    }

    private static void drawPyramid(int max_val, int pyramides, int current_pyramid) {

    }

    private static void drawChristmassTree(int n) {
        for (int i = 1; i <= n; i++) {
            drawPyramid(i, n);
        }
    }


    public static void main(String[] args) {
        System.out.println("START");
        scan = new Scanner(System.in);

        boolean halt = false;
        while (!halt) {
            String line = scan.nextLine();
            // empty line and comment line - read next line
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!" + line);
            String word[] = line.split(" ");
            if (word[0].equalsIgnoreCase("py") && word.length == 2) {
                int value = Integer.parseInt(word[1]);
                drawPyramid(value);
                continue;
            }
            if (word[0].equalsIgnoreCase("ct") && word.length == 2) {
                int value = Integer.parseInt(word[1]);
                drawChristmassTree(value);
                continue;
            }
            // ld documentName
            if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                loadDocument(word[1]);
                continue;
            }
            // ha
            if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                halt = true;
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");

        scan.close();

    }


}
