package StringsFirstAssignments;

public class Part3 {
    public Boolean twoOccurrences(String stringa, String stringb) {
        int start1 = stringb.toLowerCase().indexOf(stringa.toLowerCase());
        if (start1 == -1) {
            return false;
        }
        int start2 = stringb.toLowerCase().indexOf(stringa.toLowerCase(), start1 + stringa.length());
        if (start2 == -1) {
            return false;
        }
        return true;
    }

    public void testing() {
        System.out.println(twoOccurrences("by", "A story Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }

    public String lastPart(String stringa, String stringb) {
        int start = stringb.indexOf(stringa);
        if (start == -1) {
            return stringb;
        }
        return stringb.substring(start, start + stringa.length() + 1);

    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }
}
