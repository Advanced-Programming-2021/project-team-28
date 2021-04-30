package model;

public class Utilities {

    public static int compareAlphabetical(String first, String second) {
        int loopLength = Math.min(first.length(), second.length());
        for (int i = 0; i < loopLength; i++) {
            if (first.charAt(i) == second.charAt(i)) {
                if (i == loopLength - 1) {
                    return first.length() - second.length();
                }
            } else if (Character.toLowerCase(first.charAt(i)) == Character.toLowerCase(second.charAt(i))) {
                return Character.compare(first.charAt(i), second.charAt(i));
            } else {
                return Character.compare(Character.toLowerCase(first.charAt(i)), Character.toLowerCase(second.charAt(i)));
            }
        }
        return 0;
    }
}
