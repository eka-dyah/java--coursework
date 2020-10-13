package SecondStringAssignments;

public class Part2 {
    public int howMany(String stringa, String stringb) {
        int startIndex = stringb.indexOf(stringa);
        int count = 0;
        while (true) {
            if (startIndex == -1) {
                break;
            }
            count = count + 1;
            startIndex = stringb.indexOf(stringa, startIndex + stringa.length());
        }
        return count;
    }

    public void testHowMany() {
        System.out.println("GAA in ATGAACGAATTGAATC : " + howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println("AA in ATAAAA : " + howMany("AA", "ATAAAA"));
    }

    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}
