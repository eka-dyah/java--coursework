package PredictiveText;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {

    public MarkovOne() {
        myRandom = new Random();
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

        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);

        for(int k=0; k < numChars; k++){
            ArrayList<Character> chars = getFollows(key);
            if (chars.size() == 0) {
                break;
            }
            index = myRandom.nextInt(chars.size());
            String next = String.valueOf(chars.get(index));
            sb.append(chars.get(index));
            key = next;
        }

        return sb.toString();
    }

    public String toString() {
        return "MarkovModel of order 1";
    }
}
