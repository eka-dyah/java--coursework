package StringsFirstAssignments;

public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
        int startIndex = dna.toUpperCase().indexOf(startCodon.toUpperCase());
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        if ((startIndex - (stopIndex + 3)) % 3 == 0) {
            return dna.toUpperCase().substring(startIndex, stopIndex + 3);
        } else {
            return "";
        }
    }
    public void testSimpleGene() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("This string " + a.toUpperCase() + " has gene " + findSimpleGene(a, "atg", "aaa"));
        System.out.println("This string " + ap.toUpperCase() + " has gene " + findSimpleGene(ap, "atg", "taa"));
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testSimpleGene();
    }
}
