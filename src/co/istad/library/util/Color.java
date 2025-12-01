package co.istad.library.util;

public class Color {
    // I use final because these values must never change during program execution.
    // It the same as constants in other programming.
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";
    public static final String YELLOW = "\033[0;33m";
    public static final String CYAN = "\033[0;36m";
    public static final String MAGENTA = "\033[0;35m";
    public static final String PURPLE = "\033[0;35m";
    public static final String BLACK = "\033[0;30m";
    public static final String BOLD = "\033[1m";
    public static final String BOLD_CYAN = BOLD + CYAN;
    public static final String BOLD_RED = BOLD + RED;
    public static final String BOLD_GREEN = BOLD + GREEN;
    public static final String BOLD_BLUE = BOLD + BLUE;
    public static final String BOLD_YELLOW = BOLD + YELLOW;
    public static final String BOLD_PURPLE = BOLD + PURPLE;
}