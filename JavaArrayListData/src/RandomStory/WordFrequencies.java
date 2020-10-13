package RandomStory;

import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource resource = new FileResource();
        
        for (String s : resource.words()) {
            int index = myWords.indexOf(s.toLowerCase());
            if (index == -1) {
                myWords.add(s.toLowerCase());
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        int index = findIndexMax();
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
        for (int i=0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
    }

    public int findIndexMax(){
        int maxValue = myFreqs.get(0);
        int maxIndex = 0;
        for (int i=0; i< myFreqs.size(); i++) {
            if (myFreqs.get(i) > maxValue) {
                maxValue = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
