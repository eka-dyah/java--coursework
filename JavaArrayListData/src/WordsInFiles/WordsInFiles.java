package WordsInFiles;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> countWords;

    public WordsInFiles() {
        countWords = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsInFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (countWords.containsKey(word)) {
                ArrayList<String> filenames = countWords.get(word);
                int index = filenames.indexOf(f.getName());
                if (index == -1) {
                    filenames.add(f.getName());
                    countWords.put(word, filenames);
                }
            } else {
                ArrayList<String> fname = new ArrayList<String>();
                fname.add(f.getName());
                countWords.put(word, fname);

            }
        }
    }

    public void buildWordFileMap() {
        countWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsInFile(f);
        }
    }

    public int maxNumber() {
        int max = 0;
        for (String word : countWords.keySet()) {
            int size = countWords.get(word).size();
            if (size > max) {
                max = size;
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : countWords.keySet()) {
            int size = countWords.get(word).size();
            if (size == number) {
                words.add(word);
            }
        }
        return words;
    }

    public void printFilesIn(String word) {
        ArrayList<String> files = countWords.get(word);
        for (String file : files) {
            System.out.println(file);
        }
    }

    public void tester() {
        buildWordFileMap();
        System.out.println("Size map: " + countWords.size());
        System.out.println(maxNumber());
        ArrayList<String> num = wordsInNumFiles(4);
        System.out.println(num.size());
        ArrayList<String> num7 = wordsInNumFiles(7);
        System.out.println(num7.size());
        printFilesIn("sea");
        System.out.println("----------");
        printFilesIn("tree");

//        for (String word : countWords.keySet()) {
//            String files = countWords.get(word).toString();
//            System.out.println(word + " " + files);
//        }
    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}
