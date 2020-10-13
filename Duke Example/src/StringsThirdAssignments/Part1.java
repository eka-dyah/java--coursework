package StringsThirdAssignments;
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int start = startIndex;
        int stop = dna.toLowerCase().indexOf(stopCodon, start + 3);
        while(true) {
            if (stop == -1) {
                return -1;
            }
            if ((stop - startIndex) % 3 == 0) {
                return stop;
            }
            start = stop + 3;
            stop = dna.toLowerCase().indexOf(stopCodon, start);
        }
    }

    public String findGene(String dna, int startIndex) {
        int taaIndex = findStopCodon(dna.toLowerCase(), startIndex, "taa");
        int tagIndex = findStopCodon(dna.toLowerCase(), startIndex, "tag");
        int tgaIndex = findStopCodon(dna.toLowerCase(), startIndex, "tga");

        int minIndex;
        if (taaIndex == -1 || (tagIndex != -1 && taaIndex > tagIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }
        if (minIndex == -1) {
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
            System.out.println(result);
            if (result.isEmpty()) {
                break;
            }
            count = count + 1;

            startIndex = dna.toLowerCase().indexOf("atg", startIndex + result.length());
        }
        return count;
    }

    public StorageResource getAllGenes(String dna) {
        int startIndex = dna.toLowerCase().indexOf("atg");
        int count = 0;
        StorageResource sr = new StorageResource();
        String result = "";
        while(true) {
            if (startIndex == -1) {
                break;
            }
            result = findGene(dna.toLowerCase(), startIndex);
            if (result.isEmpty()) {
                break;
            }

            sr.add(result);
            count = count + 1;

            startIndex = dna.toLowerCase().indexOf("atg", startIndex + result.length());
        }
        return sr;
    }

    public double cgRatio(String dna) {
        int cIndex = dna.toLowerCase().indexOf("c");
        int gIndex = dna.toLowerCase().indexOf("g");
        double ratio = 0;
        int count = 0;
        while (cIndex != -1) {
            count = count + 1;
            cIndex = dna.toLowerCase().indexOf("c", cIndex + 1);
        }
        while (gIndex != -1) {
            count = count + 1;
            gIndex = dna.toLowerCase().indexOf("g", gIndex + 1);
        }
        ratio = (float)count / dna.length();
        return ratio;
    }

    public void processGenes(StorageResource sr) {
        int maxLength = 0;
        String longestDna = "";
        int count60 = 0;
        int countCg = 0;
        for (String s : sr.data()) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                longestDna = s;
            }
            if (s.length() > 60) {
                count60++;
                System.out.println("Longer than 60 : " + s + " have length " + s.length());
            }
            if (cgRatio(s) > 0.35) {
                countCg++;
                System.out.println("Have cg ratio higher than 0.35 : " + s + " that is " + cgRatio(s));
            }
        }
        System.out.println("The longest dna: " + longestDna);
        System.out.println("The longest dna length: " + maxLength);
        System.out.println("Length more than 60: " + count60);
        System.out.println("CG more than 0.35: " + countCg);
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

    public void testCountGenes(String dna) {
        System.out.println("Total genes: " + countGenes(dna));
    }

    public void testProcessGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        testCountGenes(dna);
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
//        p3.testFindStopCodon();
//        p3.testFindGene();
//        p1.testCountGenes();
        p1.testProcessGenes();
    }
}
