package CodonCount;

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> countsCodon;

    public CodonCount() {
        countsCodon = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        countsCodon.clear();
        int i = start;
        while (i < dna.length() - 3) {
            String codon = dna.substring(i, i + 3);
            if (countsCodon.containsKey(codon)) {
                int counts = countsCodon.get(codon);
                countsCodon.put(codon, counts + 1);
            } else {
                countsCodon.put(codon, 1);
            }
            i += 3;
        }
    }

    public String getMostCommonCodon() {
        int max = 0;
        String codon = "";
        for (String key : countsCodon.keySet()) {
            if (countsCodon.get(key) > max) {
                max = countsCodon.get(key);
                codon = key;
            }
        }
        System.out.println("The most occurrences: " + max);
        return codon;
    }

    public void printCodonCounts(int start, int end) {
        if (start <= end) {
            for (String key : countsCodon.keySet()) {
                if (countsCodon.get(key) >= start && countsCodon.get(key) <= end) {
                    System.out.println(key + " " + countsCodon.get(key));
                }
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        buildCodonMap(0, fr.asString());
        System.out.println(countsCodon.size());
        System.out.println("The most common codon is " + getMostCommonCodon());
        printCodonCounts(7, 7);
        System.out.println("-------");
        buildCodonMap(1, fr.asString());
        System.out.println(countsCodon.size());
        System.out.println("The most common codon is " + getMostCommonCodon());
        printCodonCounts(7, 7);
        System.out.println("-------");
        buildCodonMap(2, fr.asString());
        System.out.println(countsCodon.size());
        System.out.println("The most common codon is " + getMostCommonCodon());
        printCodonCounts(7, 7);
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
