package ru.vsu.cs.task11.console;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> text = readFileAndSetList();
        String[] textArray = changeLitter(text);
        printResult(textArray);
        writeFile(textArray);
    }

    public static String symbolicTranslator(char symbol) {
        switch (symbol) {
            case 'а':
                return "a";
            case 'б':
                return "b";
            case 'в':
                return "v";
            case 'г':
                return "g";
            case 'д':
                return "d";
            case 'е':
            case 'ё':
            case 'э':
                return "e";
            case 'ж':
                return "zh";
            case 'з':
                return "z";
            case 'и':
                return "i";
            case 'й':
                return "i";
            case 'к':
                return "k";
            case 'л':
                return "l";
            case 'м':
                return "m";
            case 'н':
                return "n";
            case 'о':
                return "o";
            case 'п':
                return "p";
            case 'р':
                return "r";
            case 'с':
                return "s";
            case 'т':
                return "t";
            case 'у':
                return "u";
            case 'ф':
                return "f";
            case 'х':
                return "kh";
            case 'ц':
                return "ts";
            case 'ч':
                return "ch";
            case 'ш':
                return "sh";
            case 'щ':
                return "shch";
            case 'ы':
                return "y";
            case 'ъ':
            case 'ь':
                return "ie";
            case 'ю':
                return "iu";
            case 'я':
                return "ia";
            default:
                return String.valueOf(symbol);
        }
    }

    public static List<String> readFileAndSetList() throws IOException {
        int n = 0;
        List<String> text = new ArrayList<>();
        FileReader reader = new FileReader(readSomething("Enter file name: "));
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String number = scanner.nextLine();
            if (number.equals("")) break;
            text.add(n, number);
            n++;
        }
        return text;
    }

    public static boolean checkCapitalLetter(String text, int i) {
        String textSmallLetters = text.toLowerCase();
        char smallLetter = textSmallLetters.charAt(i);
        char inputLetter = text.charAt(i);
        int difference = smallLetter - inputLetter;
        return difference == 32;
    }

    public static String[] changeLitter(List<String> list) {
        String[] textArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String litter, textSmallLetters, inputLetter;
            StringBuilder letterArray = null;
            inputLetter = list.get(i);
            textSmallLetters = inputLetter.toLowerCase();
            textSmallLetters = textSmallLetters.replace(" ", "`");
            for (int j = 0; j < textSmallLetters.length(); j++) {
                litter = symbolicTranslator(textSmallLetters.charAt(j));
                if (checkCapitalLetter(inputLetter, j)) litter = litter.toUpperCase();
                letterArray = (letterArray == null ? new StringBuilder("null") : letterArray).append(litter);
            }
            textArray[i] = (letterArray == null ? null : letterArray.toString());
        }
        return textArray;
    }

    public static void printResult(String[] textArray) {
        for (String line : textArray) {
            String text = line;
            text = text.replace("null", "");
            text = text.replace("`", " ");
            System.out.println(text);
        }
    }

    public static void writeFile(String[] textArray) throws Exception {
        FileWriter writer = new FileWriter("answer");
        for (String line : textArray) {
            String text = line;
            text = text.replace("null", "");
            text = text.replace("`", " ");
            writer.write(text + "\n");
        }
        writer.close();
    }

    public static String readSomething(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(name);
        return scanner.nextLine();
    }
}
