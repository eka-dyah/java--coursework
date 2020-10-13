package PredictiveText;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    private final int numChara;

    public MarkovModel(int n) {
        myRandom = new Random();
        numChara = n;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();

        if (myText == null){
            return "";
        }

        int index = myRandom.nextInt(myText.length()-numChara);
        String key = myText.substring(index, index+numChara);
        sb.append(key);

        for(int k=0; k < numChars-numChara; k++){
            ArrayList<Character> chars = getFollows(key);
            if (chars.size() == 0) {
                break;
            }
            index = myRandom.nextInt(chars.size());
            String next = String.valueOf(chars.get(index));
            sb.append(chars.get(index));
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public String toString() {
        return "MarkovModel of order " + numChara;
    }
}
