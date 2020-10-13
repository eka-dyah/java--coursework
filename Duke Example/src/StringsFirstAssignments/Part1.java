package StringsFirstAssignments;

public class Part1 {
    public String findSimpleGene (String dna) {
        int startIndex = dna.indexOf("atg");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("taa", startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        if ((startIndex - (stopIndex + 3)) % 3 == 0) {
            return dna.substring(startIndex, stopIndex + 3);
        } else {
            return "";
        }
    }
    public void testSimpleGene() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtcttaaataataatag";
        System.out.println("This string " + a + " has gene " + findSimpleGene(a));
        System.out.println("This string " + ap + " has gene " + findSimpleGene(ap));
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}
