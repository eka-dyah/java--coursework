package NGrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
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
        printHashMapInfo();
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<>();
        for (WordGram wg : map.keySet()) {
            if (wg.equals(kGram)) {
                kGram = wg;
                follows = map.get(kGram);
                break;
            }
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

    private void buildMap() {
        map = new HashMap<WordGram, ArrayList<String>>();
        for (int i=0; i < myText.length - myOrder + 1; i++) {
            ArrayList<String> list;
            WordGram wordGram = new WordGram(myText, i, myOrder);
            int indexNext = indexOf(myText, wordGram, i);

            list = new ArrayList<>();
            for (WordGram wg : map.keySet()) {
                if (wg.equals(wordGram)) {
                    wordGram = wg;
                    list = map.get(wg);
                    break;
                }
            }

            if (indexNext != 1 && indexNext < myText.length) {
                list.add(myText[indexNext]);
            }
            map.put(wordGram, list);
        }
    }

    public void testIndexOf() {
        String[] words = "this is just a test yes this is just a simple test".split("\\s+");
        WordGram test = new WordGram(words, 0, 4);
        System.out.println(test);
        int index = indexOf(words, test, 0);
        System.out.println(index);
    }

    public void printHashMapInfo() {
        System.out.println("Map: " + map);
        System.out.println("Map size: " + map.size());
        int max = 0;
        WordGram wordGram = null;
        for (WordGram wg : map.keySet()) {
            if (map.get(wg).size() > max) {
                wordGram = wg;
                max = map.get(wg).size();
            }
        }
        System.out.println("Max size : " + max);
        System.out.println("Max size : " + wordGram);
    }
}
