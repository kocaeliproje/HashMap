public class TerminalBox {
    private static final String HORIZONTAL = "═";
    private static final String VERTICAL = "║";
    private static final String TOP_LEFT = "╔";
    private static final String TOP_RIGHT = "╗";
    private static final String BOTTOM_LEFT = "╚";
    private static final String BOTTOM_RIGHT = "╝";


    public static void printBoxedMessage(String... lines) {
        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }

        // Top border
        System.out.print(TOP_LEFT);
        System.out.print(HORIZONTAL.repeat(maxLength + 2));
        System.out.println(TOP_RIGHT);

        // Content
        for (String line : lines) {
            System.out.print(VERTICAL + " " + line);
            System.out.print(" ".repeat(maxLength - line.length() + 1));
            System.out.println(VERTICAL);
        }

        // Bottom border
        System.out.print(BOTTOM_LEFT);
        System.out.print(HORIZONTAL.repeat(maxLength + 2));
        System.out.println(BOTTOM_RIGHT);
    }
}