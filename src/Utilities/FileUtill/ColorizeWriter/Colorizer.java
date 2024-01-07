package Utilities.FileUtill.ColorizeWriter;

public class Colorizer {
    public static class ColorizeWriter {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";

        public static void printlnRed(String str) {
            System.out.println(ANSI_RED + str + ANSI_RESET);
        }

        public static void printlnGreen(String str) {
            System.out.println(ANSI_GREEN + str + ANSI_RESET);
        }

        public static void printLnYellow(String str) {
            System.out.println(ANSI_YELLOW + str + ANSI_RESET);
        }

        public static void printlnBlue(String str) {
            System.out.println(ANSI_BLUE + str + ANSI_RESET);
        }

        public static void printlnPurple(String str) {
            System.out.println(ANSI_PURPLE + str + ANSI_RESET);
        }

        public static void printlnCyan(String str) {
            System.out.println(ANSI_CYAN + str + ANSI_RESET);
        }
    }

}
