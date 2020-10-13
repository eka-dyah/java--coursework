package SecondStringAssignments;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index = dna.indexOf(stopCodon, startIndex + 3);
        if (index == -1) {
            return dna.length();
        }
        return index;
    }

    public String findGene(String dna, int start) {
        int startIndex = dna.indexOf("atg", start);
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

    public int countGenes(String dna) {
        int startIndex = dna.toLowerCase().indexOf("atg");
        int count = 0;
        String result = "";
        while(true) {
            if (startIndex == -1) {
                break;
            }
            result = findGene(dna.toLowerCase(), startIndex);
            if (result.isEmpty()) {
                break;
            }
            count = count + 1;

            startIndex = dna.toLowerCase().indexOf("atg", startIndex + result.length());
        }
        return count;
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
        System.out.println(s1 + " : " + findGene(s1, 0));
        System.out.println(s2 + " : " + findGene(s2, 0));
        System.out.println(s3 + " : " + findGene(s3, 0));
        System.out.println(s4 + " : " + findGene(s4, 0));
        System.out.println(s5 + " : " + findGene(s5, 0));
    }

    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }

    public static void main(String[] args) {
        Part3 p3 = new Part3();
//        p3.testFindStopCodon();
//        p3.testFindGene();
        p3.testCountGenes();
    }
}
