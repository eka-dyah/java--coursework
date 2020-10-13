package SecondStringAssignments;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index = dna.indexOf(stopCodon, startIndex + 3);
        if (index == -1) {
            return dna.length();
        }
        return index;
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("atg");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "taa");
        int tagIndex = findStopCodon(dna, startIndex, "tag");
        int tgaIndex = findStopCodon(dna, startIndex, "tga");
        int minIndex = Math.min(taaIndex, tagIndex);
        minIndex = Math.min(minIndex, tgaIndex);

        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);

    }

    public void testFindStopCodon() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtcttaaataataatag";
        System.out.println("This string " + a + " has gene " + findStopCodon(a, a.indexOf("atg"), "taa"));
        System.out.println("This string " + ap + " has gene " + findStopCodon(ap, ap.indexOf("atg"), "tga"));
    }

    public void testFindGene() {
        String s1 = "atgaaattttaattt";
        String s2 = "atgaaattttaaa";
        String s3 = "aaattttaatagaaa";
        String s4 = "atgaaattttagtaaaaa";
        String s5 = "atgaaattttgatagtaaaaa";
        System.out.println(s1 + " : " + findGene(s1));
        System.out.println(s2 + " : " + findGene(s2));
        System.out.println(s3 + " : " + findGene(s3));
        System.out.println(s4 + " : " + findGene(s4));
        System.out.println(s5 + " : " + findGene(s5));
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testFindStopCodon();
        p1.testFindGene();
    }
}
