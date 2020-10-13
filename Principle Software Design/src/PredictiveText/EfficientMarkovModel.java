package PredictiveText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private final int numChara;
    private final HashMap<String, ArrayList<Character>> map;

    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        numChara = n;
        map = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
    }

    public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();

        if (myText == null) {
            return "";
        }

        int index = myRandom.nextInt(myText.length() - numChara);
        String key = myText.substring(index, index + numChara);
        sb.append(key);

        for (int k = 0; k < numChars - numChara; k++) {
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
        return "Efficient MarkovModel of order " + numChara;
    }

    private void buildMap() {
        if (myText == null) {
            return;
        }

        for (int k = 0; k < myText.length() - numChara + 1; k++) {
            ArrayList<Character> charList;
            String key = myText.substring(k, k + numChara);
            if (map.containsKey(key)) {
                charList = map.get(key);
            } else {
                charList = new ArrayList<>();
            }
            if (k + numChara < myText.length()) {
                charList.add(myText.charAt(k + numChara));
            }
            map.put(key, charList);
        }
    }

    @Override
    public ArrayList<Character> getFollows(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return new ArrayList<>();
    }

    public void printHashMapInfo() {
        System.out.println("Map size is " + map.size());
        int maxIndex = 0;
        String keys = "";
        for (String key : map.keySet()) {
            if(map.get(key).size() > maxIndex) {
                maxIndex = map.get(key).size();
                keys = key;
            }
        }

        System.out.println(keys + " " + maxIndex);
    }
}
