package GladLibMap;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> lists;
    private ArrayList<String> trackedList;

    private final Random myRandom;

    private static final String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static final String dataSourceDirectory = "GladLib/data";

    public GladLibMap() {
        trackedList = new ArrayList<>();
        lists = new HashMap<>();
        String[] nameList = new String[]{"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String name : nameList) {
            lists.put(name, new ArrayList<String>());
        }
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source) {
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        for (String list : lists.keySet()) {
            ArrayList<String> words = readIt(source + "/" + list + ".txt");
            lists.put(list, words);
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (lists.containsKey(label)) {
            int index = trackedList.indexOf(label);
            if (index == -1) {
                trackedList.add(label);
            }
            return randomFrom(lists.get(label));
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("GladLib/data/madtemplate.txt");
        printOut(story, 60);
    }

    public int totalWordsInMap() {
        int total = 0;
        for (String words : lists.keySet()) {
            total += lists.get(words).size();
        }
        return total;
    }

    public int totalWordsConsidered() {
        int total = 0;
        for (String word : trackedList) {
            total += lists.get(word).size();
        }
        return total;
    }

    public static void main(String[] args) {
        GladLibMap gl = new GladLibMap();
        gl.makeStory();
        System.out.println("Total words in map " + gl.totalWordsInMap());
        System.out.println("Total words in category that has been used " + gl.totalWordsConsidered());
    }

}
