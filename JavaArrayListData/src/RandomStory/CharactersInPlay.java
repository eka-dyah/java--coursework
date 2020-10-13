package RandomStory;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> listsChara;
    private ArrayList<Integer> countsChara;

    public CharactersInPlay() {
        listsChara = new ArrayList<String>();
        countsChara = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = listsChara.indexOf(person);
        if (index == -1) {
            listsChara.add(person);
            countsChara.add(1);
        } else {
            int counts = countsChara.get(index);
            countsChara.set(index, counts + 1);
        }
    }

    public void findAllCharacters() {
        FileResource fr = new FileResource();
        String name;
        for (String line : fr.lines()) {
            int dot = line.indexOf('.');
            if (dot != -1) {
                name = line.substring(0, dot);
                update(name.strip());
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        if (num1 <= num2) {
            System.out.println("Print name that have occurrences between " + num1 + " and " + num2);
            for (int i=0; i<listsChara.size(); i++) {
                if (countsChara.get(i) >= num1 && countsChara.get(i) <= num2) {
                    System.out.println(listsChara.get(i) + " " + countsChara.get(i));
                }
            }
        }
    }

    public int findIndexMax(){
        int maxValue = countsChara.get(0);
        int maxIndex = 0;
        for (int i=0; i< countsChara.size(); i++) {
            if (countsChara.get(i) > maxValue) {
                maxValue = countsChara.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void tester() {
        findAllCharacters();
        System.out.println(findIndexMax());

        int i = findIndexMax();
        System.out.println(listsChara.get(i) + " " + countsChara.get(i));
        charactersWithNumParts(70, 103);
        charactersWithNumParts(10, 15);
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }

}
