package PredictiveText;

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<Character> getFollows(String key) {
        ArrayList<Character> answer = new ArrayList<>();
        int i = myText.indexOf(key);
        while (i != -1) {
            if (i + 1 == myText.length()) {
                break;
            }
            answer.add(myText.charAt(i+key.length()));
            i = myText.indexOf(key, i+key.length());
        }
        return answer;
    }

}
