import java.util.Arrays; // Use only for testing
import java.util.Random; // Use only for testing

/*
  Add two large int represented as array (or strings).
 */
public class Chapter7_ProgrammingProject1 {

    private static final String EMPTY_STRING = "";
    private static final char MINUS = '-';
    private static final int ASCII_ZERO = (int) '0';
    private static final int BASE =  10;


    /**
     * Adds two large integers represented as strings. Consider the two
     * strings below:
     *
     *   String a = "1234567"
     *   String b =    "2345"
     *
     * The method assesses their overlapping length -- in this example 4 ---
     * and starts adding pairwise digits from the back of the strings moving
     * to the front. In the example above, the first pairwise addition will be
     * between digits 7 and 5. Their addition is split into two parts: a single
     * digit sum and the carry; in this example the addition is 7+5=12. Its
     * single digit sum is 2 and the carry is 1. The single digit sum is placed
     * in the output string of the method, and the carry is added to the next
     * pairwise addition.
     *
     * When we are done with the pairwise elements, we pad the output string
     * with the remaining digits of the longest of the two input strings.
     * This is equivalent to taking the shortest string, in this case "2345",
     * and add enough zeros to its front, to make its length the same as the
     * other string's:
     *
     *   String a = "1234567"
     *   String b = "0002345"
     *
     * @param first int first of the two numbers to be added
     * @param second int second of the two numbers to be added
     * @return String with sum of the two input numbers.
     */
    public static String addLargeInt(String first, String second) {
        // Sum and carry variables for the pairwise additions
        int sum, carry = 0;
        // The pairwise digits
        int digitL, digitS;
        // Output string, initialized to empty
        String sumString = EMPTY_STRING;
        // Placeholders for long and short strings
        String longest, shortest;
        // If not, make the appropriate changes
        if (first.length() > second.length()) {
            longest = first;
            shortest = second;
        } else {
            longest = second;
            shortest = first;
        }
        // Traverse the length of the longest string
        for (int i = 0; i < longest.length(); i++) {
            // Obtain a digit from the longest string, from the back
            // moving forward.
            digitL = ((int) longest.charAt(longest.length()-1-i))-ASCII_ZERO;
            // Check to see if we are within range of the shortest string
            if (i < shortest.length()) {
                // Obtain a digit from shortest string, also from the back
                // and moving forward.
                digitS = ((int) shortest.charAt(shortest.length()-1-i))-ASCII_ZERO;
            } else {
                // We are out of range for the shortest string, assume it has
                // a zero at this position, e.g, "123" same as "000123".
                digitS = 0;
            }
            // Compute a single-digit pairwise sum, including the carry from
            // a previous addition.
            sum = (digitL+digitS+carry) % BASE;
            // Compute the carry
            carry = (digitL+digitS+carry) / BASE;
            // Place the sum digit at the beginning of the output string.
            sumString = (char) (ASCII_ZERO+sum) + sumString;
            //System.out.printf("\ni=%d\n\tL=%d\n\tS=%d\n\t\tsum=%d, carry=%d",
            //        i,digitL,digitS, sum, carry);
        }
        // If there is a carry after we are done, place it to the front of the
        // output string.
        if (carry > 0)
            sumString = "1" + sumString;
        return sumString;
    }  // method addLargeInt


    public static int[] addLargeInt(int[] first, int[] second) {
        // Output array has a +1 space for overflow.
        int[] sumArray = new int[1+Math.max(first.length, second.length)];
        int carry = 0, sum, digitF, digitS;
        for (int i = 0; i < Math.max(first.length, second.length); i++) {
            digitF = (i < first.length) ? first[first.length-1-i] : 0;
            digitS = (i < second.length) ? second[second.length-1-i] : 0;
            sum = (digitF+digitS+carry) % BASE;
            carry = (digitF+digitS+carry) / BASE;
            sumArray[sumArray.length-1-i] = sum;
        }
        if (carry > 0)
            sumArray[0] = 1;
        return sumArray;
    }


    public static void main(String[] args) {
        System.out.printf("\n\nTesting ...");
        Random rng = new Random();
        for (int i = 0; i < 10; i++) {
            long a = Math.abs(rng.nextLong()) / 2L;
            long b = Math.abs(rng.nextLong()) / 2L;
            long c = a + b;

            String first = String.valueOf(a);
            String second = String.valueOf(b);
            String longSum = addLargeInt(first, second);
            System.out.printf("\n\tAdding: %d (%s)\n\t   and: %d (%s)", a, first, b, second);
            System.out.printf("\n\tNumeric sum: %d", c);
            System.out.printf("\n\t String sum: %s", longSum);
            if (longSum.equals(String.valueOf(c)))
                System.out.printf("\nTest: pass!\n");
            else
                System.out.printf("\nTest: f a i l\n");
        }
        System.out.printf("\n\n");
        int[] x = {9,9};
        int[] y = {2};
        int[] xy = addLargeInt(x,y);
        System.out.printf("%s", Arrays.toString(xy));
    }
}
