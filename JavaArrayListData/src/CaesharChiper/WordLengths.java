package CaesharChiper;
import edu.duke.*;

public class WordLengths {
    public void countWordLength(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int k = 0;
            for (int i=0; i <word.length(); i++) {
                if (i == 0 && Character.isLetter(word.charAt(i)) && word.length()!=1) {
                    k+=1;
                }
                if (i == word.length()-1 && Character.isLetter(word.charAt(i)) && word.length()!=1) {
                    k+=1;
                }
                if ((i != 0 && i != word.length()-1) || word.length()==1) {
                    k+=1;
                }
            }
            System.out.println("index " + k + " has " + word);
            counts[k] = counts[k] + 1;
        }
    }

    public int indexOfMax(int[] values) {
        int index = 0;
        for (int i=0; i< values.length; i++) {
            System.out.println("index " + i + " has " + values[i]);
            if (values[i] > values[index]) {
                index = i;
            }
        }
        return index;
    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource("CaesharChiper/errors.txt");
        int[] counts = new int[30];
        countWordLength(fr, counts);
        for (int val : counts) {
            System.out.println(val);
        }
        System.out.println(indexOfMax(counts));
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}
