package co.istad.library.util;

public class ViewUtil {
    public static void printBanner() {
        System.out.println(Color.BOLD_CYAN + """
                 ___ ____ _____  _    ____    _     ___ ____  ____      _    ______   __
                |_ _/ ___|_   _|/ \\  |  _ \\  | |   |_ _| __ )|  _ \\    / \\  |  _ \\ \\ / /
                 | |\\___ \\ | | / _ \\ | | | | | |    | ||  _ \\| |_) |  / _ \\ | |_) \\ V /\s
                 | | ___) || |/ ___ \\| |_| | | |___ | || |_) |  _ <  / ___ \\|  _ < | | \s
                |___|____/ |_/_/   \\_\\____/  |_____|___|____/|_| \\_\\/_/   \\_\\_| \\_\\|_| \s""" + Color.RESET);
    }
}
