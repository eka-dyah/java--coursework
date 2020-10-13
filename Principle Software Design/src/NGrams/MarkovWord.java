package NGrams;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram keys = new WordGram(myText, index, myOrder);
        sb.append(keys);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(keys);

            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keys = keys.shiftAdd(next);
        }

//        ArrayList<String> follows = getFollows(keys);
//        System.out.println(follows);
//        System.out.println(follows.size());

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, kGram, 0);
        while (index != -1) {
            if (index + 1 >= myText.length) {
                break;
            }
            follows.add(myText[index]);
            index = indexOf(myText, kGram, index+1);
        }
        return follows;
    }

    private int indexOf(String[] words, WordGram target, int start) {
        String word = "";
        for (int i=start; i < words.length; i++) {
            word += words[i];
            word += " ";
            int index = word.indexOf(target.toString());
            if (index != -1) {
                return i + 1;
            }
        }
        return -1;
    }

    public void testIndexOf() {
        String[] words = "this is just a test yes this is just a simple test".split("\\s+");
        WordGram test = new WordGram(words, 0, 4);
        System.out.println(test);
        int index = indexOf(words, test, 0);
        System.out.println(index);
    }
}
