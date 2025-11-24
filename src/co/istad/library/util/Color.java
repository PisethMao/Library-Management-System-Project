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
    public static final String WHITE = "\033[0;37m";
    public static final String PURPLE = "\033[0;35m";
    public static final String BLACK = "\033[0;30m";
    public static final String GRAY = "\033[0;90m";
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";
    public static final String BOLD_CYAN = BOLD + CYAN;
    public static final String BOLD_RED = BOLD + RED;
    public static final String BOLD_GREEN = BOLD + GREEN;
    public static final String BOLD_BLUE = BOLD + BLUE;
    public static final String BOLD_YELLOW = BOLD + YELLOW;
    public static final String BOLD_MAGENTA = BOLD + MAGENTA;
    public static final String BOLD_WHITE = BOLD + WHITE;
    public static final String BOLD_PURPLE = BOLD + PURPLE;
    public static final String BOLD_BLACK = BOLD + BLACK;
    public static final String BOLD_GRAY = BOLD + GRAY;
    public static final String BOLD_UNDERLINE = BOLD + UNDERLINE;
}
